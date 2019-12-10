package com.asiainfo.crm.blueprint.smo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.audit.vo.ScopeInfo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferInstDetaiListlReqVo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferInstDetailListRspVo;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstListMutilRsp;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstVoExtend;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailReq;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailRsp;
import com.asiainfo.crm.audit.vo.prodinst.QryFuncProdInstDetailListReqVo;
import com.asiainfo.crm.audit.vo.prodinst.QryFuncProdInstDetailListRspVo;
import com.asiainfo.crm.audit.vo.prodinst.QueryProdInstMutilReqVo;
import com.asiainfo.crm.blueprint.common.AuditConstant;
import com.asiainfo.crm.blueprint.common.DateUtil;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.intf.IInstIntfQuerySmo;
import com.asiainfo.crm.blueprint.intf.IQueryProdInstMutilSmo;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.model.ProductEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 接入产品实例信息日稽核 接入产品实例信息月稽核 接入销售品实例信息日稽核 接入销售品实例信息月稽核
 * 参考存过：dep_audit.depNew4Audits_for4G
 * 
 * @author chenchao
 *
 */
@Component("depNewAudits4G")
public class LteDepNewAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(LteDepNewAuditSmoImpl.class);

	private static String auditOriginDataId;

	@Autowired
	IQueryProdInstMutilSmo queryProdInstMutilSmo;

	@Autowired
	IInstIntfQuerySmo instIntfQuerySmo;

	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		LOG.debug("4G新增4项稽核数据抽取入库开始...");
		String inXml = auditInfo.getDownXml();
		String bjXml = auditInfo.getReportXml();
		String createTime = auditInfo.getCreateTime();
		auditOriginDataId = auditInfo.getAuditOriginDataId();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("4G新增4项稽核,集团下发报文为空。");
		}
		Document doc = XmlUtil.parseXml(inXml);
		Node rootNode = doc.selectSingleNode("/ContractRoot");
		String transactionID = rootNode.selectSingleNode("./TcpCont/TransactionID").getText();
		LOG.debug("transactionID:" + transactionID);
		String serviceCode = rootNode.selectSingleNode("./TcpCont/ServiceCode").getText();
		LOG.debug("serviceCode" + serviceCode);
		// 处理集团下发业务 存过1438行
		if ("SVC80003,SVC80037,SVC80032,SVC80074".contains(serviceCode)) {
			Document reportCompletedDoc = null;
			if (StringUtils.isNotBlank(bjXml)) {
				// 报竣报文
				reportCompletedDoc = XmlUtil.parseXml(bjXml);
			}

			// 异地补换卡业务
			boolean offsitePcCardFlag = false;
			List<Node> pcCodeNode = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[SERVICE_OFFER_ID='4040600001']");
			// 补换卡业务
			List<Node> bhkCodeNode = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[SERVICE_OFFER_ID='4040600000']");
			List<Node> prodResInsts = rootNode
					.selectNodes("./SvcCont/SOO[@type=\"ADD_PROD_RES_INST_REL_REQ_TYPE\"]/PROD_RES_INST_REL");
			if (((pcCodeNode != null && pcCodeNode.size() > 0) || (bhkCodeNode != null && bhkCodeNode.size() > 0))
					&& prodResInsts != null && prodResInsts.size() > 0) {
				offsitePcCardFlag = true;
				offsitePatchChangeCard(rootNode, reportCompletedDoc, createTime, prodResInsts, transactionID);
			}

			// 新增接入产品
			boolean addProdInstFlag = false;
			List<Node> prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"ADD_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (prodInsts != null && prodInsts.size() > 0) {
				addProdInstFlag = true;
				addAccPrd(rootNode, reportCompletedDoc, createTime, prodInsts, transactionID);
			}
			
			// 新增EXT_PROD_INST_VOLTE_ID
			Node volteProdInst = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_PROD_INST_REQ_TYPE\"]/PROD_INST[EXT_PROD_ID='13410485']");
			if (volteProdInst != null) {
				addVoltePrd(rootNode, reportCompletedDoc, createTime, volteProdInst, transactionID);
			}

			// 单独开4G上网
			Node extProdInstServIdNode = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_PROD_INST_REQ_TYPE\"]/PROD_INST[EXT_PROD_ID='280000020']");
			if (offsitePcCardFlag == false && addProdInstFlag == false && extProdInstServIdNode != null) {
				addAccPrdFor4G(rootNode, reportCompletedDoc, createTime, extProdInstServIdNode, transactionID);
			}
			// 保持接入产品
			prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"KIP_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (offsitePcCardFlag == false && prodInsts != null && prodInsts.size() > 0) {
				keepAccPrd(rootNode, reportCompletedDoc, createTime, prodInsts, transactionID);
			}
			// 接入产品拆机
			prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"DEL_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (prodInsts != null && prodInsts.size() > 0) {
				delAccPrd(rootNode, reportCompletedDoc, createTime, prodInsts, transactionID);
			}
			// MOD产品（包含改状态、改属性）
			prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"MOD_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (prodInsts != null && prodInsts.size() > 0) {
				modProduct(rootNode, reportCompletedDoc, createTime, prodInsts, transactionID);
			}
			// 新增销售品
			List<Node> prodOfferInsts = rootNode
					.selectNodes("./SvcCont/SOO[@type=\"ADD_PROD_OFFER_INST_REQ_TYPE\"]/PROD_OFFER_INST");
			if (prodOfferInsts != null && prodOfferInsts.size() > 0) {
				addProdOffer(rootNode, transactionID, createTime, prodOfferInsts);
			}
			// 退订销售品
			prodOfferInsts = rootNode
					.selectNodes("./SvcCont/SOO[@type=\"DEL_PROD_OFFER_INST_REQ_TYPE\"]/PROD_OFFER_INST");
			if (prodOfferInsts != null && prodOfferInsts.size() > 0) {
				delProdOffer(rootNode, transactionID, createTime, prodOfferInsts);
			}
			// 变更销售品（稽核表没啥要改，没有的时候新增下）
			prodOfferInsts = rootNode
					.selectNodes("./SvcCont/SOO[@type=\"MOD_PROD_OFFER_INST_REQ_TYPE\"]/PROD_OFFER_INST");
			if (prodOfferInsts != null && prodOfferInsts.size() > 0) {
				modProdOffer(rootNode, transactionID, createTime, prodOfferInsts);
			}
			// 处理省内上传，不处理销售品的 存过2501行
		} else if ("SVC80017,SVC80035".contains(serviceCode)) {
			// 状态变更（停复机、活卡激活），上传时一个报文只会有一个变更
			List<Node> prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"MOD_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (prodInsts != null && prodInsts.size() > 0) {
				modStatus(rootNode, createTime, prodInsts, transactionID);
			}

			// 欠费拆机
			prodInsts = rootNode.selectNodes(
					"./SvcCont/SOO[@type=\"DEL_PROD_INST_REQ_TYPE\"]/PROD_INST[ACC_PROD_INST_ID=PROD_INST_ID]");
			if (prodInsts != null && prodInsts.size() > 0) {
				removeByArrears(rootNode, createTime, prodInsts, transactionID);
			}
		}
		LOG.debug("4G新增4项稽核数据抽取入库结束...");
	}

	// 有VoLTE功能产品实例则填18位实例编码，无则填0
	private void addVoltePrd(Node rootNode, Document reportCompletedDoc, String createTime, Node volteProdInst,
			String transactionID) {
		String extAccProdInstId = volteProdInst.selectSingleNode("./EXT_ACC_PROD_INST_ID").getText();
		String extProdInstId = volteProdInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
		// update产品月稽核表
		if(StringUtils.isNotBlank(extProdInstId) && StringUtils.isNotBlank(extAccProdInstId)){
			Map<String, String> paras = new HashMap<String, String>();
			paras.put("extAccProdInstId", extAccProdInstId);
			paras.put("extProdInstId", extProdInstId);
			saopAuditDao.updateProdMonVotel(paras);
		}
		
	}

	// 异地补换卡
	private void offsitePatchChangeCard(Node rootNode, Document reportCompletedDoc, String createTime,
			List<Node> prodInsts, String transactionID) throws Exception {
		for (Node prodInst : prodInsts) {
			String extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			String prodInstId = prodInst.selectSingleNode("./PROD_INST_ID").getText();
			QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
			req.setProdInstId(Long.valueOf(prodInstId));
			// 远程调用
			QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(req);
			ProdInstVoExtend prodInstVoExtend;
			if (rsp != null && rsp.getProdInstDetail() != null) {
				prodInstVoExtend = rsp.getProdInstDetail();
			} else {
				LOG.debug("查询接入类产品实例详情为空");
				QueryProdInstMutilReqVo reqVo = new QueryProdInstMutilReqVo();
				reqVo.setProdInstId(Long.valueOf(prodInstId));
				// 远程调用
				ProdInstListMutilRsp rspVo = queryProdInstMutilSmo.qryHisAccProdInstListLocal(reqVo);
				if (rspVo == null || rspVo.getAccProdInsts() == null || rspVo.getAccProdInsts().size() == 0) {
					LOG.debug("查询历史接入类产品实例详情为空");
					throw new Exception("productInstId:" + prodInstId + "查询历史接入类产品实例详情为空");
				}
				prodInstVoExtend = rspVo.getAccProdInsts().get(0);
			}
			ProductEntity productEntity = new ProductEntity();
			productEntity.setProdInstId(prodInstId);
			productEntity.setPhoneNumber(prodInstVoExtend.getAccNum());
			productEntity.setExtProdInstId(extProdInstId);
			productEntity.setLanId(String.valueOf(prodInstVoExtend.getLanId()));
			String provinceId = this.getProvinceId(String.valueOf(prodInstVoExtend.getLanId()));
			productEntity.setProvinceId(provinceId);
			productEntity.setStatusCd(prodInstVoExtend.getStatusCd());
			productEntity.setAccProductId("235010000");
			productEntity.setAccProductName(prodInstVoExtend.getProdName());
			productEntity.setBeginRentTime(getFormatDate(prodInstVoExtend.getBeginRentDate()));
			productEntity.setFinishTime(getFormatDate(prodInstVoExtend.getFirstFinishDate()));
			productEntity.setFinishTime(getFormatDate(prodInstVoExtend.getStopRentDate()));
			productEntity.setAuditTime(createTime);
			productEntity.setDealTime(createTime);
			Map<String, String> params = new HashMap<String, String>();
			params.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
			params.put("v_EXT_PROD_INST_ID", extProdInstId);
			params.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			String oldTransactionIDDaily = saopAuditDao.queryProdDailyAudit(params);
			String oldTransactionIDMonth = saopAuditDao.queryProdMonthAudit(params);
			params.put("transactionID", transactionID);
			// 日稽核
			if (StringUtils.isEmpty(oldTransactionIDDaily)) {
				saveProdDailyAudit(transactionID, productEntity);
			} else {
				updateProdDailyAudit(transactionID, oldTransactionIDMonth, params);
			}
			// 月稽核
			if (StringUtils.isEmpty(oldTransactionIDMonth)) {
				saveProdMonthAudit(transactionID, productEntity);
			} else {
				params.put("v_LANDID", productEntity.getLanId());
				params.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
				params.put("v_FINISH_TIME", productEntity.getFinishTime());
				params.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldTransactionIDMonth, params);
			}
		}

	}

	// 单独开4G上网
	private void addAccPrdFor4G(Node rootNode, Document reportCompletedDoc, String createTime, Node prodInsts,
			String transactionID) throws Exception {
		String lanId = prodInsts.selectSingleNode("./LAN_ID").getText();
		ProductEntity productEntity = new ProductEntity();
		String extAccProdInstId = prodInsts.selectSingleNode("./EXT_ACC_PROD_INST_ID").getText();
		String accProdInstId = prodInsts.selectSingleNode("./ACC_PROD_INST_ID").getText();
		if (StringUtils.isBlank(accProdInstId) || accProdInstId.contains("$")) {
			// 通过extProdInstId查询中心服务得到prodInstId。
			accProdInstId = instIntfQuerySmo.getProdInstIdByExtId(lanId, "", extAccProdInstId, "");
		}
		if (StringUtils.isBlank(accProdInstId)) {
			throw new Exception("通过extProdInstId查询中心服务得到prodInstId的为空！");
		}
		productEntity.setProdInstId(accProdInstId);
		productEntity.setExtProdInstId(extAccProdInstId);
		productEntity.setAuditTime(createTime);
		productEntity.setDealTime(createTime);
		QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
		req.setProdInstId(Long.valueOf(accProdInstId));
		// 远程调用
		QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(req);
		ProdInstVoExtend prodInstVoExtend;
		if (rsp != null && rsp.getProdInstDetail() != null) {
			prodInstVoExtend = rsp.getProdInstDetail();
		} else {
			LOG.debug("查询接入类产品实例详情为空");
			QueryProdInstMutilReqVo reqVo = new QueryProdInstMutilReqVo();
			reqVo.setProdInstId(Long.valueOf(accProdInstId));
			// 远程调用
			ProdInstListMutilRsp rspVo = queryProdInstMutilSmo.qryHisAccProdInstListLocal(reqVo);
			if (rspVo == null || rspVo.getAccProdInsts() == null || rspVo.getAccProdInsts().size() == 0) {
				LOG.debug("查询历史接入类产品实例详情为空");
				throw new Exception("productInstId:" + accProdInstId + "查询历史接入类产品实例详情为空");
			}
			prodInstVoExtend = rspVo.getAccProdInsts().get(0);
		}
		if (null != prodInstVoExtend) {
			productEntity.setPhoneNumber(prodInstVoExtend.getAccNum());
			productEntity.setLanId(String.valueOf(lanId));
			String provinceId = this.getProvinceId(lanId);
			productEntity.setProvinceId(provinceId);
			productEntity.setStatusCd(prodInstVoExtend.getStatusCd());
			productEntity.setAccProductId("235010000");
			productEntity.setAccProductName("手机");
			// 起租时间
			if (null != prodInstVoExtend.getBeginRentDate()) {
				productEntity.setBeginRentTime(getFormatDate(prodInstVoExtend.getBeginRentDate()));
			} else {
				throw new Exception("prodInstId" + accProdInstId + "产品实例的起租时间为空！");
			}
			// 竣工时间
			if (null != prodInstVoExtend.getFirstFinishDate()) {
				productEntity.setFinishTime(getFormatDate(prodInstVoExtend.getFirstFinishDate()));
			} else {
				productEntity.setFinishTime(getFormatDate(prodInstVoExtend.getBeginRentDate()));
			}
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
		params.put("v_EXT_PROD_INST_ID", extAccProdInstId);
		params.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
		Node extProdInstServIdNode = rootNode.selectSingleNode(
				"./SvcCont/SOO[@type=\"ADD_PROD_INST_REQ_TYPE\"]/PROD_INST[EXT_PROD_ID='280000020']/EXT_PROD_INST_ID");
		String extProdInstServId = extProdInstServIdNode == null ? "0" : extProdInstServIdNode.getText();
		productEntity.setExtProdInstServId(extProdInstServId);
		String oldTransactionIDDaily = saopAuditDao.queryProdDailyAudit(params);
		String oldTransactionIDMonth = saopAuditDao.queryProdMonthAudit(params);
		params.put("transactionID", transactionID);
		params.put("v_EXT_PROD_INST_SERV_ID", extProdInstServId);
		// 日稽核
		if (StringUtils.isEmpty(oldTransactionIDDaily)) {
			saveProdDailyAudit(transactionID, productEntity);
		} else {
			updateProdDailyAudit(transactionID, oldTransactionIDMonth, params);
		}
		// 月稽核
		if (StringUtils.isEmpty(oldTransactionIDMonth)) {
			saveProdMonthAudit(transactionID, productEntity);
		} else {
			params.put("v_LANDID", productEntity.getLanId());
			params.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
			params.put("v_FINISH_TIME", productEntity.getFinishTime());
			params.put("v_DEAL_TIME", productEntity.getDealTime());
			updateProdMonthAudit(transactionID, oldTransactionIDMonth, params);
		}
	}

	// 新增接入产品
	private void addAccPrd(Node rootNode, Document reportCompletedDoc, String createTime, List<Node> prodInsts,
			String transactionID) throws Exception {
		String phoneNumber = null;
		String extProdInstId = null;
		String lanId = null;
		String provinceId = null;
		String statusCd = null;
		String accProductId = null;
		String accProductName = null;
		String itemOrderId = null;
		String groupId = null;
		String finishTime = null;
		String extMktResInstId = null;
		String beginRentTime = null;
		ProductEntity productEntity = new ProductEntity();
		for (Node prodInst : prodInsts) {
			phoneNumber = prodInst.selectSingleNode("./ACC_NBR").getText();
			extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			lanId = prodInst.selectSingleNode("./LAN_ID").getText();
			provinceId = this.getProvinceId(lanId);
			statusCd = prodInst.selectSingleNode("./STATUS_CD").getText();
			// 未竣工状态报竣后应改成在用状态
			statusCd = "130000".equals(statusCd) ? "100000" : statusCd;
			accProductId = prodInst.selectSingleNode("./EXT_PROD_ID").getText();
			accProductName = prodInst.selectSingleNode("./PRODUCT_NAME").getText();
			String prodInstId = prodInst.selectSingleNode("./PROD_INST_ID").getText();
			// 通过extProdInstId查询中心服务得到prodInstId。
			prodInstId = instIntfQuerySmo.getProdInstIdByExtId(lanId, "", extProdInstId, "");
			if (StringUtils.isBlank(prodInstId)) {
				throw new Exception("通过extProdInstId查询中心服务得到prodInstId的为空！");
			}
			// 在联调过程中出现一个extProdInstId对应多个prodInstId，暂时取第一个。
			if (prodInstId != null && prodInstId.split(",").length > 1) {
				prodInstId = prodInstId.split(",")[0];
			}
			productEntity.setProdInstId(prodInstId);
			productEntity.setPhoneNumber(phoneNumber);
			productEntity.setExtProdInstId(extProdInstId);
			productEntity.setLanId(lanId);
			productEntity.setProvinceId(provinceId);
			productEntity.setStatusCd(statusCd);
			productEntity.setAccProductId(accProductId);
			productEntity.setAccProductName(accProductName);
			productEntity.setAuditTime(createTime);
			productEntity.setDealTime(createTime);
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				getProductNameID(productEntity);
			}
			itemOrderId = prodInst.selectSingleNode("./../ORDER_REQ/ORDER_ITEM_ID").getText();
			if (null != reportCompletedDoc) {
				Node statusDateNode = reportCompletedDoc
						.selectSingleNode("/ContractRoot/SvcCont/SOO[STATUS_CD=300000 and ORDER_ITEM/ORDER_ITEM_ID='"
								+ itemOrderId + "']/STATUS_DATE");
				if (statusDateNode != null) {
					productEntity.setBeginRentTime(statusDateNode.getText());
				}
				if (StringUtils.isBlank(productEntity.getBeginRentTime())) {
					Node groupIdNode = rootNode.selectSingleNode(
							"./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[ORDER_ITEM_ID='" + itemOrderId
									+ "']/ORDER_ITEM_GROUP_ID");
					groupId = groupIdNode == null ? "" : groupIdNode.getText();
					statusDateNode = reportCompletedDoc
							.selectSingleNode("/ContractRoot/SvcCont/SOO[STATUS_CD=300000 and ORDER_ITEM_GROUP_ID='"
									+ groupId + "']/STATUS_DATE");
					if (statusDateNode != null) {
						productEntity.setBeginRentTime(statusDateNode.getText());
					}
				}
			}
			// 如果是提前报竣的会取不到值
			if (StringUtils.isBlank(beginRentTime)) {
				getBeginRentTime(productEntity);
			}
			beginRentTime = productEntity.getBeginRentTime();
			finishTime = productEntity.getFinishTime();
			finishTime = StringUtils.isBlank(finishTime) ? beginRentTime : finishTime;
			productEntity.setFinishTime(finishTime);
			Node extMktResInstIdNode = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_PROD_RES_INST_REL_REQ_TYPE\"]/PROD_RES_INST_REL[EXT_PROD_INST_ID='"
							+ extProdInstId + "' and MKT_RES_TYPE=103006000]/EXT_MKT_RES_INST_ID");
			extMktResInstId = extMktResInstIdNode == null ? "" : extMktResInstIdNode.getText();
			Node resInst = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_MKT_RES_INST_REQ_TYPE\"]/MKT_RES_INST[EXT_MKT_RES_INST_ID='"
							+ extMktResInstId + "' and MKT_RES_TYPE=103006000]");
			// 卡资源
			setCardResource(productEntity, resInst);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", phoneNumber);
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			Node extProdInstServIdNode = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_PROD_INST_REQ_TYPE\"]/PROD_INST[EXT_PROD_ID='280000020']/EXT_PROD_INST_ID");
			String extProdInstServId = extProdInstServIdNode == null ? "0" : extProdInstServIdNode.getText();
			productEntity.setExtProdInstServId(extProdInstServId);
			String oldTransactionIDDaily = saopAuditDao.queryProdDailyAudit(queryParam);
			String oldTransactionIDMonth = saopAuditDao.queryProdMonthAudit(queryParam);
			// 日稽核
			if (StringUtils.isEmpty(oldTransactionIDDaily)) {
				saveProdDailyAudit(transactionID, productEntity);
			}
			// 月稽核
			if (StringUtils.isEmpty(oldTransactionIDMonth)) {
				saveProdMonthAudit(transactionID, productEntity);
			}
		}
	}

	// 保持接入产品
	private void keepAccPrd(Node rootNode, Document reportCompletedDoc, String createTime, List<Node> prodInsts,
			String transactionID) throws Exception {
		int isChanged = 0;
		ProductEntity productEntity = new ProductEntity();
		for (Node prodInst : prodInsts) {
			String phoneNumber = prodInst.selectSingleNode("./ACC_NBR").getText();
			productEntity.setPhoneNumber(phoneNumber);
			String extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			String prodInstId = prodInst.selectSingleNode("./PROD_INST_ID").getText();
			// 如果extProdInstId为空查资产中心
			if (StringUtils.isBlank(extProdInstId)) {
				extProdInstId = queryExtProdInstIdByCenter(prodInstId);
				if (StringUtils.isBlank(extProdInstId)) {
					throw new Exception("通过prodInstId：" + prodInstId + "查询资产中心获得extProdInstId为空。");
				}
			}
			productEntity.setExtProdInstId(extProdInstId);
			String lanId = prodInst.selectSingleNode("./LAN_ID").getText();
			productEntity.setLanId(lanId);
			productEntity.setProvinceId(this.getProvinceId(lanId));
			Node statusNode = prodInst.selectSingleNode("./STATUS_CD");
			if (statusNode != null) {
				productEntity.setStatusCd(statusNode.getText());
			}
			// statusCd为空
			if (StringUtils.isBlank(productEntity.getStatusCd())) {
				QryAccProdInstDetailReq reqVo = new QryAccProdInstDetailReq();
				if (null != prodInstId && prodInstId.contains(":")) {
					reqVo.setAccNum(phoneNumber);
				} else {
					reqVo.setProdInstId(Long.valueOf(prodInstId));
				}
				QryAccProdInstDetailRsp rspVo = queryProdInstMutilSmo.qryAccProdInstDetail(reqVo);
				if (rspVo == null || rspVo.getProdInstDetail() == null) {
					LOG.debug("查询接入类产品实例详情为空");
					// 查历史表
					QueryProdInstMutilReqVo req = new QueryProdInstMutilReqVo();
					req.setProdInstId(Long.valueOf(prodInstId));
					// 远程调用
					ProdInstListMutilRsp rsp = queryProdInstMutilSmo.qryHisAccProdInstListLocal(req);
					if (rsp == null || rsp.getAccProdInsts() == null || rsp.getAccProdInsts().size() == 0) {
						LOG.debug("查询历史接入类产品实例详情为空");
						throw new Exception("通过prodInstId:" + prodInstId + "查询接入类产品实例详情为空，所以取不到statusCd。");
					}
					productEntity.setStatusCd(rsp.getAccProdInsts().get(0).getStatusCd());
				} else {
					productEntity.setStatusCd(rspVo.getProdInstDetail().getStatusCd());
				}

			}
			productEntity.setAccProductId(prodInst.selectSingleNode("./EXT_PROD_ID").getText());
			productEntity.setAccProductName(prodInst.selectSingleNode("./PRODUCT_NAME").getText());
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				getProductNameID(productEntity);
			}
			productEntity.setProdInstId(prodInst.selectSingleNode("./PROD_INST_ID").getText());
			// 从全量表或本地库取起租时间
			getBeginRentTime(productEntity);
			productEntity.setStopRentTime("");
			Node resInst = null;
			Node extMktResInstNode = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"ADD_PROD_RES_INST_REL_REQ_TYPE\"]/PROD_RES_INST_REL[EXT_PROD_INST_ID='"
							+ extProdInstId + "' and MKT_RES_TYPE='103006000']/EXT_MKT_RES_INST_ID");
			String extMktResInstId = extMktResInstNode != null ? extMktResInstNode.getText() : null;
			// 如果extMktResInstId为空
			if (StringUtils.isBlank(extMktResInstId)) {
				extMktResInstNode = rootNode.selectSingleNode(
						"./SvcCont/SOO[@type=\"KIP_PROD_RES_INST_REL_REQ_TYPE\"]/PROD_RES_INST_REL[EXT_PROD_INST_ID='"
								+ extProdInstId + "' and MKT_RES_TYPE='103006000']/EXT_MKT_RES_INST_ID");
				extMktResInstId = extMktResInstNode != null ? extMktResInstNode.getText() : null;
			} else {
				isChanged = 1;
			}
			productEntity.setExtMktResInstId(extMktResInstId);
			if (StringUtils.isNotBlank(extMktResInstId)) {
				resInst = rootNode.selectSingleNode(
						"./SvcCont/SOO[@type=\"ADD_MKT_RES_INST_REQ_TYPE\"]/MKT_RES_INST[EXT_MKT_RES_INST_ID='"
								+ extMktResInstId + "' and MKT_RES_TYPE='103006000']");
				if (null == resInst) {
					resInst = rootNode.selectSingleNode(
							"./SvcCont/SOO[@type=\"KIP_MKT_RES_INST_REQ_TYPE\"]/MKT_RES_INST[EXT_MKT_RES_INST_ID='"
									+ extMktResInstId + "' and MKT_RES_TYPE='103006000']");
				} else {
					isChanged = 1;
				}
			}
			if (null == resInst) {
				// 从稽核全量表查
				getProdResInfo(productEntity);
			} else {
				// 不为空将报文中set到productEntity
				setCardResource(productEntity, resInst);
			}
			productEntity.setAuditTime(createTime);
			productEntity.setDealTime(createTime);
			Node extProdInstServIdNodeKip = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"KIP_PROD_INST_REQ_TYPE\"]/PROD_INST[EXT_PROD_ID='280000020']/EXT_PROD_INST_ID");
			String extProdInstServId = "";
			if (null != extProdInstServIdNodeKip) {
				extProdInstServId = extProdInstServIdNodeKip.getText();
			} else {
				extProdInstServId = "0";
			}
			productEntity.setExtProdInstServId(extProdInstServId);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", phoneNumber);
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			// 日稽核
			String oldTransactionIdDaily = saopAuditDao.queryProdDailyAudit(queryParam);
			Map<String, String> updateParam = new HashMap<String, String>();
			if (StringUtils.isEmpty(oldTransactionIdDaily)) {
				saveProdDailyAudit(transactionID, productEntity);
			} else if (isChanged == 1) {
				// UpdateParam塞值
				setKeepAccPrdUpdateParam(productEntity, phoneNumber, extProdInstId, updateParam);
				updateProdDailyAudit(transactionID, oldTransactionIdDaily, updateParam);
			}
			// 月稽核
			String oldMonthTransactionId = saopAuditDao.queryProdMonthAudit(queryParam);
			if (StringUtils.isEmpty(oldMonthTransactionId)) {
				saveProdMonthAudit(transactionID, productEntity);
			} else if (isChanged == 1) {
				// UpdateParam塞值
				setKeepAccPrdUpdateParam(productEntity, phoneNumber, extProdInstId, updateParam);
				updateParam.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldMonthTransactionId, updateParam);
			}
		}

	}

	// UpdateParam塞值
	private void setKeepAccPrdUpdateParam(ProductEntity productEntity, String phoneNumber, String extProdInstId,
			Map<String, String> updateParam) {
		updateParam.put("v_ICCID", StringUtils.isEmpty(productEntity.getIccid()) ? "" : productEntity.getIccid());
		updateParam.put("v_CIMSI", StringUtils.isEmpty(productEntity.getCimsi()) ? "" : productEntity.getCimsi());
		updateParam.put("v_GIMSI", StringUtils.isEmpty(productEntity.getGimsi()) ? "" : productEntity.getGimsi());
		updateParam.put("v_LIMSI", StringUtils.isEmpty(productEntity.getLimsi()) ? "" : productEntity.getLimsi());
		updateParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
		updateParam.put("v_PHONE_NUMBER", phoneNumber);
		updateParam.put("v_LANDID", productEntity.getLanId());
		updateParam.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
		updateParam.put("v_FINISH_TIME", productEntity.getFinishTime());
		updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
	}

	// 接入产品拆机
	private void delAccPrd(Node rootNode, Document reportCompletedDoc, String createTime, List<Node> prodInsts,
			String transactionID) throws Exception {
		String phoneNumber = null;
		String extProdInstId = null;
		String lanId = null;
		String provinceId = null;
		String itemOrderId = null;
		String groupId = null;
		String stopRentTime = null;
		ProductEntity productEntity = new ProductEntity();
		for (Node prodInst : prodInsts) {
			phoneNumber = prodInst.selectSingleNode("./ACC_NBR").getText();
			productEntity.setPhoneNumber(phoneNumber);
			extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			productEntity.setExtProdInstId(extProdInstId);
			lanId = prodInst.selectSingleNode("./LAN_ID").getText();
			productEntity.setLanId(lanId);
			provinceId = this.getProvinceId(lanId);
			productEntity.setProvinceId(provinceId);
			// 拆机业务，可以默认110000
			productEntity.setStatusCd("110000");
			productEntity.setAccProductId(prodInst.selectSingleNode("./EXT_PROD_ID").getText());
			productEntity.setAccProductName(prodInst.selectSingleNode("./PRODUCT_NAME").getText());
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				getProductNameID(productEntity);
			}
			// 从全量表或本地库取起租时间
			productEntity.setProdInstId(prodInst.selectSingleNode("./PROD_INST_ID").getText());
			getBeginRentTime(productEntity);
			// 拆机时间先取报竣报文中的失效时间，其次从实例表取，再次取报文中的报竣时间
			itemOrderId = prodInst.selectSingleNode("./../ORDER_REQ/ORDER_ITEM_ID").getText();
			Node nodeStatus = reportCompletedDoc
					.selectSingleNode("/ContractRoot/SvcCont/SOO[STATUS_CD='300000' and ORDER_ITEM/ORDER_ITEM_ID='"
							+ itemOrderId + "']/STATUS_DATE");
			stopRentTime = nodeStatus != null ? nodeStatus.getText() : "";
			if (StringUtils.isBlank(stopRentTime)) {
				Node groupIdNode = rootNode
						.selectSingleNode("./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[ORDER_ITEM_ID='"
								+ itemOrderId + "']/ORDER_ITEM_GROUP_ID");
				groupId = groupIdNode == null ? "" : groupIdNode.getText();
				nodeStatus = reportCompletedDoc
						.selectSingleNode("/ContractRoot/SvcCont/SOO[STATUS_CD='300000' and ORDER_ITEM_GROUP_ID='"
								+ groupId + "']/STATUS_DATE");
				stopRentTime = nodeStatus != null ? nodeStatus.getText() : "";
			}
			// 如果是提前报竣的会取不到值
			if (StringUtils.isBlank(stopRentTime)) {
				// 调用中心服务获取拆机时间
				getStopRentTime(productEntity);
			}
			// 取卡资源，拆机报文中没有 全量表查
			getProdResInfo(productEntity);
			productEntity.setAuditTime(createTime);
			productEntity.setDealTime(createTime);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", phoneNumber);
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			// 日稽核
			String oldDailyTransactionId = saopAuditDao.queryProdDailyAudit(queryParam);
			Map<String, String> updateParam = new HashMap<String, String>();
			if (StringUtils.isEmpty(oldDailyTransactionId)) {
				saveProdDailyAudit(transactionID, productEntity);
			} else {
				// UpdateParam塞值
				setDelAccPrdUpdateParam(phoneNumber, extProdInstId, stopRentTime, productEntity, updateParam);
				updateProdDailyAudit(transactionID, oldDailyTransactionId, updateParam);
			}
			// 月稽核
			String oldMonthTransactionId = saopAuditDao.queryProdMonthAudit(queryParam);
			if (StringUtils.isEmpty(oldMonthTransactionId)) {
				saveProdMonthAudit(transactionID, productEntity);
			} else {
				// UpdateParam塞值
				setDelAccPrdUpdateParam(phoneNumber, extProdInstId, stopRentTime, productEntity, updateParam);
				updateParam.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldMonthTransactionId, updateParam);
			}
		}
	}

	// UpdateParam塞值
	private void setDelAccPrdUpdateParam(String phoneNumber, String extProdInstId, String stopRentTime,
			ProductEntity productEntity, Map<String, String> updateParam) {
		updateParam.put("v_STATUS_CD", productEntity.getStatusCd());
		updateParam.put("v_STOP_RENT_TIME", stopRentTime);
		updateParam.put("v_PHONE_NUMBER", phoneNumber);
		updateParam.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
		updateParam.put("v_FINISH_TIME", productEntity.getFinishTime());
		updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam.put("v_LANDID", productEntity.getLanId());
		updateParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// MOD产品（包含改状态、改属性）
	private void modProduct(Node rootNode, Document reportCompletedDoc, String createTime, List<Node> prodInsts,
			String transactionID) throws Exception {
		String extProdInstId = null;
		String lanId = null;
		String extMktResInstId = null;
		String beginRentTime = null;
		String avcCode = null;
		String itemOrderId = null;
		String serviceOfferId = null;
		ProductEntity productEntity = new ProductEntity();
		for (Node prodInst : prodInsts) {
			itemOrderId = prodInst.selectSingleNode("./../ORDER_REQ/ORDER_ITEM_ID").getText();
			Node avcCodeNode = rootNode
					.selectSingleNode("./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[ORDER_ITEM_ID='"
							+ itemOrderId + "']/AVC_CODE/text()");
			avcCode = avcCodeNode == null ? "" : avcCodeNode.getText();
			Node serviceOfferIdNode = rootNode
					.selectSingleNode("./SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[ORDER_ITEM_ID='"
							+ itemOrderId + "']/SERVICE_OFFER_ID");
			serviceOfferId = serviceOfferIdNode == null ? "" : serviceOfferIdNode.getText();
			productEntity.setPhoneNumber(prodInst.selectSingleNode("./ACC_NBR").getText());
			extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			productEntity.setExtProdInstId(extProdInstId);
			lanId = prodInst.selectSingleNode("./LAN_ID").getText();
			productEntity.setProvinceId(getProvinceId(lanId));
			productEntity.setLanId(lanId);
			Node statusCd = prodInst.selectSingleNode("./STATUS_CD");
			productEntity.setStatusCd(statusCd != null ? statusCd.getText() : "");
			if (StringUtils.isBlank(productEntity.getStatusCd())) {
				QryAccProdInstDetailReq reqVo = new QryAccProdInstDetailReq();
				reqVo.setAccNum(productEntity.getPhoneNumber());
				QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(reqVo);
				if (rsp == null || rsp.getProdInstDetail() == null) {
					LOG.debug("查询资产中心接入类产品实例详情为空");
					throw new Exception(
							"PhoneNumber:" + productEntity.getPhoneNumber() + "查询资产中心接入类产品实例详情为空，所以取不到statusCd。");
				}
				productEntity.setStatusCd(rsp.getProdInstDetail().getStatusCd());
			}
			productEntity.setAccProductId(prodInst.selectSingleNode("./EXT_PROD_ID").getText());
			productEntity.setAccProductName(prodInst.selectSingleNode("./PRODUCT_NAME").getText());
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				getProductNameID(productEntity);
			}
			// 从全量表或本地库取起租时间
			productEntity.setProdInstId(prodInst.selectSingleNode("./PROD_INST_ID").getText());
			getBeginRentTime(productEntity);
			productEntity.setStopRentTime("");
			// 如果是活卡激活，从报文取值
			if ("4041600000".equals(serviceOfferId)) {
				Node beginRentTimeNode = rootNode.selectSingleNode(
						"./SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/ACCEPT_TIME");
				beginRentTime = beginRentTimeNode == null ? "" : beginRentTimeNode.getText();
			}
			// 取卡资源
			Node resInst = null;
			Node extMktResInstIdNode = rootNode.selectSingleNode(
					"./SvcCont/SOO[@type=\"KIP_PROD_RES_INST_REL_REQ_TYPE\"]/PROD_RES_INST_REL[EXT_PROD_INST_ID='"
							+ extProdInstId + "' and MKT_RES_TYPE=103006000]/EXT_MKT_RES_INST_ID");
			extMktResInstId = extMktResInstIdNode == null ? "" : extMktResInstIdNode.getText();
			if (StringUtils.isNotBlank(extMktResInstId)) {
				resInst = rootNode.selectSingleNode(
						"./SvcCont/SOO[@type=\"KIP_MKT_RES_INST_REQ_TYPE\"]/MKT_RES_INST[EXT_MKT_RES_INST_ID='"
								+ extMktResInstId + "' and MKT_RES_TYPE=103006000]");
			}
			if (null == resInst) {
				// 从本地网查
				getProdResInfo(productEntity);
			} else {
				// 不为空将报文中set到productEntity
				setCardResource(productEntity, resInst);
			}
			productEntity.setAuditTime(createTime);
			productEntity.setDealTime(createTime);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			// 日稽核
			String oldDailyTransactionId = saopAuditDao.queryProdDailyAudit(queryParam);
			Map<String, String> updateParam = new HashMap<String, String>();
			Map<String, String> updateParam4AVC = new HashMap<String, String>();
			if (StringUtils.isEmpty(oldDailyTransactionId)) {
				saveProdDailyAudit(transactionID, productEntity);
			} else if ("4041600000".equals(serviceOfferId)) {// 活卡激活
				// UpdateParam塞值
				setModProductUpdateParam(extProdInstId, beginRentTime, productEntity, updateParam);
				updateProdDailyAudit(transactionID, oldDailyTransactionId, updateParam);
			} else if ("AVC20011".equals(avcCode)) {// 修改产品状态
				// updateParam4AVC塞值
				setModProductUpdateParam4AVC(extProdInstId, productEntity, updateParam4AVC);
				updateProdDailyAudit(transactionID, oldDailyTransactionId, updateParam4AVC);
			}
			// 月稽核
			String oldMonthTransactionId = saopAuditDao.queryProdMonthAudit(queryParam);
			if (StringUtils.isEmpty(oldMonthTransactionId)) {
				saveProdMonthAudit(transactionID, productEntity);
			} else if ("4041600000".equals(serviceOfferId)) {// 活卡激活
				// UpdateParam塞值
				setModProductUpdateParam(extProdInstId, beginRentTime, productEntity, updateParam);
				updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
				updateParam.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldMonthTransactionId, updateParam);
			} else if ("AVC20011".equals(avcCode)) {// 修改产品状态
				// updateParam4AVC塞值
				setModProductUpdateParam4AVC(extProdInstId, productEntity, updateParam4AVC);
				updateParam4AVC.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldMonthTransactionId, updateParam4AVC);
			}
		}
	}

	// UpdateParam塞值
	private void setModProductUpdateParam4AVC(String extProdInstId, ProductEntity productEntity,
			Map<String, String> updateParam4AVC) {
		updateParam4AVC.put("v_STATUS_CD", productEntity.getStatusCd());
		updateParam4AVC.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
		updateParam4AVC.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam4AVC.put("v_LANDID", productEntity.getLanId());
		updateParam4AVC.put("v_EXT_PROD_INST_SERV_ID", productEntity.getExtProdInstServId());
		updateParam4AVC.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// updateParam4AVC塞值
	private void setModProductUpdateParam(String extProdInstId, String beginRentTime, ProductEntity productEntity,
			Map<String, String> updateParam) {
		updateParam.put("v_STATUS_CD", productEntity.getStatusCd());
		updateParam.put("v_BEGIN_RENT_TIME", beginRentTime);
		updateParam.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
		updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam.put("v_LANDID", productEntity.getLanId());
		updateParam.put("v_EXT_PROD_INST_SERV_ID", productEntity.getExtProdInstServId());
		updateParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// 新增销售品
	private void addProdOffer(Node rootNode, String transactionID, String createTime, List<Node> prodOfferInsts)
			throws Exception {
		for (Node node : prodOfferInsts) {
			String extOfferInstId = node.selectSingleNode("./EXT_PROD_OFFER_INST_ID").getText();
			String lanId = node.selectSingleNode("./LAN_ID").getText();
			String provinceId = getProvinceId(lanId);
			String custId = node.selectSingleNode("./CUST_ID").getText();
			// 如果报文中客户id是虚拟值，从库中获取
			if (custId != null && custId.contains("$")) {
				// 调用中心服务，通过extOfferInstId查OfferInstId
				String OfferInstId = instIntfQuerySmo.getProdOfferInstIdByExtId(lanId, "", extOfferInstId, "");
				QryOfferInstDetaiListlReqVo reqVo = new QryOfferInstDetaiListlReqVo();
				// 暂时取第一个，确认为脏数据（资产：陈超，saop：蒿廷）
				if (OfferInstId != null && OfferInstId.split(",").length > 0) {
					reqVo.setOfferInstId(OfferInstId.split(",")[0]);
				}
				// 调用中心服务，通过OfferInstId查询销售品实例详情
				QryOfferInstDetailListRspVo rspVo = queryProdInstMutilSmo.qryOfferInstDetail(reqVo);
				if (rspVo == null || rspVo.getOfferInstDetail() == null) {
					LOG.debug("通过OfferInstId查询销售品实例详情,中心服务返回的结果为空");
					throw new Exception("通过OfferInstId查询销售品实例详情,中心服务返回的结果为空");
				}
				custId = String.valueOf(rspVo.getOfferInstDetail().getOwnerCustId());
			}
			String offerInstStatusCd = "1000";
			String extProdOfferId = node.selectSingleNode("./EXT_PROD_OFFER_ID").getText();
			// 没有外部规格编码（短编码）不稽核
			if (StringUtils.isBlank(extProdOfferId)) {
				return;
			}
			String prodOfferName = node.selectSingleNode("./PROD_OFFER_NAME").getText();
			String effDate = node.selectSingleNode("./EFF_DATE").getText();
			String expDate = node.selectSingleNode("./EXP_DATE").getText();
			if (StringUtils.isBlank(effDate) || StringUtils.isBlank(expDate)) {
				Map<String, String> dateMap = saopAuditDao.queryEffDateByExtOfferInstId(extOfferInstId);
				String effDateDB = dateMap.get("effDate");
				String expDateDB = dateMap.get("expDate");
				effDate = StringUtils.isBlank(effDate) ? effDateDB : effDate;
				expDate = StringUtils.isBlank(expDate) ? expDateDB : expDate;
				effDate = StringUtils.isBlank(effDate) ? node.selectSingleNode("./EFF_DATE").getText() : effDate;
				expDate = StringUtils.isBlank(expDate) ? node.selectSingleNode("./EXP_DATE").getText() : expDate;
			}
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			queryParams.put("v_AUDIT_DT", processAuditTime(createTime));
			String oldTransactionDaily = saopAuditDao.queryDepOfferDailyAudit4G(queryParams);
			String oldTransactionMonth = saopAuditDao.queryDepOfferMonthAudit4G(queryParams);
			Map<String, String> insertParam = new LinkedHashMap<String, String>();
			insertParam.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			insertParam.put("v_PROVINCEID", provinceId);
			insertParam.put("v_OFFER_INST_LAN_ID", lanId);
			insertParam.put("v_CUST_ID", custId);
			insertParam.put("v_OFFER_INST_STATUS_CD", offerInstStatusCd);
			insertParam.put("v_EXT_PROD_OFFER_ID", extProdOfferId);
			insertParam.put("v_PROD_OFFER_NAME", prodOfferName);
			insertParam.put("v_EFF_DATE", effDate);
			insertParam.put("v_EXP_DATE", expDate);
			insertParam.put("v_DEAL_TIME", createTime);
			insertParam.put("v_AUDIT_DT", processAuditTime(createTime));
			insertParam.put("v_DEAL_FLAG", DEAL_TYPE);
			insertParam.put("transactionID", transactionID);
			try {
				// 日稽核
				if (StringUtils.isEmpty(oldTransactionDaily)) {
					saopAuditDao.insertDepOfferDailyAudit(insertParam);
				}
				// 月稽核
				if (StringUtils.isEmpty(oldTransactionMonth)) {
					saopAuditDao.insertDepOfferMonthAudit(insertParam);
				}
			} catch (Exception e) {
				insertAuditErrLog(transactionID, insertParam, e);
				throw new Exception("新增销售品异常：" + e.getMessage());
			}
		}
	}

	// 退订销售品
	private void delProdOffer(Node rootNode, String transactionID, String createTime, List<Node> prodOfferInsts)
			throws Exception {
		for (Node node : prodOfferInsts) {
			String extOfferInstId = node.selectSingleNode("./EXT_PROD_OFFER_INST_ID").getText();
			String lanId = node.selectSingleNode("./LAN_ID").getText();
			String provinceId = getProvinceId(lanId);
			String custId = node.selectSingleNode("./CUST_ID").getText();
			String offerInstStatusCd = "1100";
			String extProdOfferId = node.selectSingleNode("./EXT_PROD_OFFER_ID").getText();
			// 没有外部规格编码（短编码）不稽核
			if (StringUtils.isBlank(extProdOfferId)) {
				return;
			}
			String prodOfferName = node.selectSingleNode("./PROD_OFFER_NAME").getText();
			String effDate = node.selectSingleNode("./EFF_DATE").getText();
			String expDate = node.selectSingleNode("./EXP_DATE").getText();
			if (StringUtils.isBlank(effDate) || StringUtils.isBlank(expDate)) {
				Map<String, String> dateMap = saopAuditDao.queryEffDateByExtOfferInstId(extOfferInstId);
				String effDateDB = "";
				String expDateDB = "";
				if (dateMap != null) {
					effDateDB = dateMap.get("effDate");
					expDateDB = dateMap.get("expDate");
				}
				effDate = StringUtils.isBlank(effDate) ? effDateDB : effDate;
				expDate = StringUtils.isBlank(expDate) ? expDateDB : expDate;
				effDate = StringUtils.isBlank(effDate) ? node.selectSingleNode("./EFF_DATE").getText() : effDate;
				expDate = StringUtils.isBlank(expDate) ? node.selectSingleNode("./EXP_DATE").getText() : expDate;
			}
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			queryParams.put("v_AUDIT_DT", processAuditTime(createTime));
			String oldTransactionDaily = saopAuditDao.queryDepOfferDailyAudit4G(queryParams);
			String oldTransactionMonth = saopAuditDao.queryDepOfferMonthAudit4G(queryParams);
			Map<String, String> insertParam = new LinkedHashMap<String, String>();
			insertParam.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			insertParam.put("v_PROVINCEID", provinceId);
			insertParam.put("v_OFFER_INST_LAN_ID", lanId);
			insertParam.put("v_CUST_ID", custId);
			insertParam.put("v_OFFER_INST_STATUS_CD", offerInstStatusCd);
			insertParam.put("v_EXT_PROD_OFFER_ID", extProdOfferId);
			insertParam.put("v_PROD_OFFER_NAME", prodOfferName);
			insertParam.put("v_EFF_DATE",
					StringUtils.isBlank(effDate) ? DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT) : effDate);
			insertParam.put("v_EXP_DATE", expDate);
			insertParam.put("v_DEAL_TIME", createTime);
			insertParam.put("v_AUDIT_DT", processAuditTime(createTime));
			insertParam.put("v_DEAL_FLAG", DEAL_TYPE);
			insertParam.put("transactionID", transactionID);
			try {
				// 日稽核
				if (StringUtils.isEmpty(oldTransactionDaily)) {
					LOG.debug("销售品日稽核保存paras:" + insertParam);
					saopAuditDao.insertDepOfferDailyAudit(insertParam);
				} else if (StringUtils.isNotEmpty(oldTransactionDaily)) {
					LOG.debug("销售品日稽核更新paras:" + insertParam);
					saopAuditDao.updateDepOfferDailyAudit(insertParam);
				}
				// 月稽核
				if (StringUtils.isEmpty(oldTransactionMonth)) {
					LOG.debug("销售品月稽核保存paras:" + insertParam);
					saopAuditDao.insertDepOfferMonthAudit(insertParam);
				} else if (StringUtils.isNotEmpty(oldTransactionMonth)) {
					LOG.debug("销售品月稽核更新paras:" + insertParam);
					saopAuditDao.updateDepOfferMonthAudit(insertParam);
				}
			} catch (Exception e) {
				insertAuditErrLog(transactionID, insertParam, e);
				throw new Exception("退订销售品异常：" + e.getMessage());
			}
		}
	}

	// 变更销售品（稽核表没啥要改，没有的时候新增下）
	private void modProdOffer(Node rootNode, String transactionID, String createTime, List<Node> prodOfferInsts)
			throws Exception {
		for (Node node : prodOfferInsts) {
			String extOfferInstId = node.selectSingleNode("./EXT_PROD_OFFER_INST_ID").getText();
			String lanId = node.selectSingleNode("./LAN_ID").getText();
			String provinceId = getProvinceId(lanId);
			String custId = node.selectSingleNode("./CUST_ID").getText();
			String offerInstStatusCd = "1000";
			String extProdOfferId = node.selectSingleNode("./EXT_PROD_OFFER_ID").getText();
			// 没有外部规格编码（短编码）不稽核
			if (StringUtils.isBlank(extProdOfferId)) {
				return;
			}
			String prodOfferName = node.selectSingleNode("./PROD_OFFER_NAME").getText();
			String effDate = node.selectSingleNode("./EFF_DATE").getText();
			String expDate = node.selectSingleNode("./EXP_DATE").getText();
			if (StringUtils.isBlank(effDate) || StringUtils.isBlank(expDate)) {
				Map<String, String> dateMap = saopAuditDao.queryEffDateByExtOfferInstId(extOfferInstId);
				String effDateDB = dateMap.get("effDate");
				String expDateDB = dateMap.get("expDate");
				effDate = StringUtils.isBlank(effDate) ? effDateDB : effDate;
				expDate = StringUtils.isBlank(expDate) ? expDateDB : expDate;
				effDate = StringUtils.isBlank(effDate) ? node.selectSingleNode("./EFF_DATE").getText() : effDate;
				expDate = StringUtils.isBlank(expDate) ? node.selectSingleNode("./EXP_DATE").getText() : expDate;
			}
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			queryParams.put("v_AUDIT_DT", processAuditTime(createTime));
			String oldTransactionDaily = saopAuditDao.queryDepOfferDailyAudit4G(queryParams);
			String oldTransactionMonth = saopAuditDao.queryDepOfferMonthAudit4G(queryParams);
			Map<String, String> insertParam = new LinkedHashMap<String, String>();
			insertParam.put("v_EXT_OFFER_INST_ID", extOfferInstId);
			insertParam.put("v_PROVINCEID", provinceId);
			insertParam.put("v_OFFER_INST_LAN_ID", lanId);
			insertParam.put("v_CUST_ID", custId);
			insertParam.put("v_OFFER_INST_STATUS_CD", offerInstStatusCd);
			insertParam.put("v_EXT_PROD_OFFER_ID", extProdOfferId);
			insertParam.put("v_PROD_OFFER_NAME", prodOfferName);
			insertParam.put("v_EFF_DATE", effDate);
			insertParam.put("v_EXP_DATE", expDate);
			insertParam.put("v_DEAL_TIME", createTime);
			insertParam.put("v_AUDIT_DT", processAuditTime(createTime));
			insertParam.put("v_DEAL_FLAG", DEAL_TYPE);
			insertParam.put("transactionID", transactionID);
			try {
				// 日稽核
				if (StringUtils.isEmpty(oldTransactionDaily)) {
					saopAuditDao.insertDepOfferDailyAudit(insertParam);
				}
				// 月稽核
				if (StringUtils.isEmpty(oldTransactionMonth)) {
					saopAuditDao.insertDepOfferMonthAudit(insertParam);
				}
			} catch (Exception e) {
				insertAuditErrLog(transactionID, insertParam, e);
				throw new Exception("变更销售品异常：" + e.getMessage());
			}
		}
	}

	// 状态变更（停复机、活卡激活），上传时一个报文只会有一个变更
	private void modStatus(Node rootNode, String createTime, List<Node> prodInsts, String transactionID)
			throws Exception {
		ProductEntity productEntity = new ProductEntity();
		for (Node node : prodInsts) {
			// 取其订单类型
			String itemOrderId = node.selectSingleNode("./../ORDER_REQ/ORDER_ITEM_ID").getText();
			String serviceOfferId = node
					.selectSingleNode("//SvcCont/SOO[@type=\"ADD_ORDER_ITEM_REQ_TYPE\"]/ORDER_ITEM[ORDER_ITEM_ID='"
							+ itemOrderId + "']/SERVICE_OFFER_ID")
					.getText();
			String phoneNumber = node.selectSingleNode("./ACC_NBR").getText();
			productEntity.setPhoneNumber(phoneNumber);
			String extProdInstId = node.selectSingleNode("./EXT_PROD_INST_ID").getText();
			String prodInstId = node.selectSingleNode("./PROD_INST_ID").getText();
			// 如果extProdInstId为空查资产中心
			if (StringUtils.isBlank(extProdInstId)) {
				extProdInstId = queryExtProdInstIdByCenter(prodInstId);
			}
			if (StringUtils.isBlank(extProdInstId)) {
				throw new Exception("通过prodInstId:" + prodInstId + ",查询资产中心产品实例详情，extProdInstId为空");
			}
			productEntity.setExtProdInstId(extProdInstId);
			String lanId = node.selectSingleNode("./LAN_ID").getText();
			productEntity.setLanId(lanId);
			String provinceId = getProvinceId(lanId);
			productEntity.setProvinceId(provinceId);
			String statusCd = node.selectSingleNode("./STATUS_CD").getText();
			productEntity.setStatusCd(statusCd);
			String oldStatus = node.selectSingleNode("./STATUS_CD/@OLDVAL").getText();
			String accProductId = node.selectSingleNode("./EXT_PROD_ID").getText();
			productEntity.setAccProductId(accProductId);
			String accProductName = node.selectSingleNode("./PRODUCT_NAME") == null ? ""
					: node.selectSingleNode("./PRODUCT_NAME").getText();
			productEntity.setAccProductName(accProductName);
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				// 编码映射表
				getProductNameID(productEntity);
			}
			// 从全量表或本地库取起租时间
			productEntity.setProdInstId(prodInstId);
			getBeginRentTime(productEntity);
			productEntity.setStopRentTime("");
			// 如果是活卡激活，从报文取值
			if ("4041600000".equals(serviceOfferId)) {
				String beginRentTime = node
						.selectSingleNode(
								"//SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/ACCEPT_TIME")
						.getText();
				productEntity.setBeginRentTime(beginRentTime);
			}
			// 取卡资源
			getProdResInfo(productEntity);
			productEntity.setDealTime(createTime);
			productEntity.setAuditTime(createTime);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", phoneNumber);
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			// 日稽核
			String oldTransactionDaily = saopAuditDao.queryProdDailyAudit(queryParam);
			Map<String, String> updateParam = new HashMap<String, String>();
			Map<String, String> updateParam4AVC = new HashMap<String, String>();
			if (StringUtils.isEmpty(oldTransactionDaily)) {
				saveProdDailyAudit(transactionID, productEntity);
			} else if ("4041600000".equals(serviceOfferId)) {// 活卡激活
				// UpdParam塞值
				setModStatusUpdParam(productEntity, phoneNumber, extProdInstId, statusCd, updateParam);
				updateProdDailyAudit(transactionID, oldTransactionDaily, updateParam);
			} else if (!statusCd.equals(oldStatus)) {// 停复机
				// UpdParam4AVC塞值
				setModStatusUpdParam4AVC(productEntity, phoneNumber, extProdInstId, statusCd, updateParam4AVC);
				updateProdDailyAudit(transactionID, oldTransactionDaily, updateParam4AVC);
			}
			// 月稽核
			String oldTransactionMonth = saopAuditDao.queryProdMonthAudit(queryParam);
			if (StringUtils.isEmpty(oldTransactionMonth)) {
				saveProdMonthAudit(transactionID, productEntity);
			} else if ("4041600000".equals(serviceOfferId)) {// 活卡激活
				// UpdParam塞值
				setModStatusUpdParam(productEntity, phoneNumber, extProdInstId, statusCd, updateParam);
				updateParam.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldTransactionMonth, updateParam);
			} else if (!statusCd.equals(oldStatus)) {// 停复机
				// UpdParam4AVC塞值
				setModStatusUpdParam4AVC(productEntity, phoneNumber, extProdInstId, statusCd, updateParam4AVC);
				updateParam4AVC.put("v_DEAL_TIME", productEntity.getDealTime());
				updateProdMonthAudit(transactionID, oldTransactionMonth, updateParam4AVC);
			}
		}
	}

	// UpdParam4AVC塞值
	private void setModStatusUpdParam4AVC(ProductEntity productEntity, String phoneNumber, String extProdInstId,
			String statusCd, Map<String, String> updateParam4AVC) {
		updateParam4AVC.put("v_STATUS_CD", statusCd);
		updateParam4AVC.put("v_PHONE_NUMBER", phoneNumber);
		updateParam4AVC.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam4AVC.put("v_LANDID", productEntity.getLanId());
		updateParam4AVC.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
		updateParam4AVC.put("v_FINISH_TIME", productEntity.getFinishTime());
		updateParam4AVC.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// UpdParam塞值
	private void setModStatusUpdParam(ProductEntity productEntity, String phoneNumber, String extProdInstId,
			String statusCd, Map<String, String> updateParam) {
		updateParam.put("v_STATUS_CD", statusCd);
		updateParam.put("v_PHONE_NUMBER", phoneNumber);
		updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam.put("v_LANDID", productEntity.getLanId());
		updateParam.put("v_BEGIN_RENT_TIME", productEntity.getBeginRentTime());
		updateParam.put("v_FINISH_TIME", productEntity.getFinishTime());
		updateParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// 欠费拆机
	private void removeByArrears(Node rootNode, String createTime, List<Node> prodInsts, String transactionID)
			throws Exception {
		String phoneNumber = null;
		String extProdInstId = null;
		String lanId = null;
		String provinceId = null;
		String statusCd = null;
		String accProductId = null;
		String accProductName = null;
		ProductEntity productEntity = new ProductEntity();
		for (Node prodInst : prodInsts) {
			phoneNumber = prodInst.selectSingleNode("./ACC_NBR").getText();
			productEntity.setPhoneNumber(phoneNumber);
			extProdInstId = prodInst.selectSingleNode("./EXT_PROD_INST_ID").getText();
			String prodInstId = prodInst.selectSingleNode("./PROD_INST_ID").getText();
			productEntity.setExtProdInstId(extProdInstId);
			lanId = prodInst.selectSingleNode("./LAN_ID").getText();
			productEntity.setLanId(lanId);
			provinceId = this.getProvinceId(lanId);
			productEntity.setProvinceId(provinceId);
			statusCd = prodInst.selectSingleNode("./STATUS_CD").getText();
			productEntity.setStatusCd(statusCd);
			// 拆机业务为空，可以默认110000
			statusCd = StringUtils.isBlank(statusCd) ? "110000" : statusCd;
			accProductId = prodInst.selectSingleNode("./EXT_PROD_ID").getText();
			productEntity.setAccProductId(accProductId);
			accProductName = prodInst.selectSingleNode("./PRODUCT_NAME").getText();
			productEntity.setAccProductName(accProductName);
			// 集团有时候不下发该字段
			if (StringUtils.isBlank(productEntity.getAccProductName())) {
				getProductNameID(productEntity);
			}
			// 从全量表或本地库取起租时间
			productEntity.setProdInstId(prodInstId);
			getBeginRentTime(productEntity);
			// 调用中心服务取拆机时间
			getStopRentTime(productEntity);
			// 取卡资源，拆机报文中没有
			getProdResInfo(productEntity);
			productEntity.setDealTime(createTime);
			productEntity.setAuditTime(createTime);
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("v_PHONE_NUMBER", phoneNumber);
			queryParam.put("v_EXT_PROD_INST_ID", extProdInstId);
			queryParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
			Map<String, String> updateParam = new HashMap<String, String>();
			// 月稽核
			// UpdParam塞值
			setRemoveByArrearsUpdParam(phoneNumber, extProdInstId, statusCd, productEntity, updateParam);
			updateParam.put("v_DEAL_TIME", productEntity.getDealTime());
			updateProdMonthAudit(transactionID, "0", updateParam);
		}
	}

	// UpdParam塞值
	private void setRemoveByArrearsUpdParam(String phoneNumber, String extProdInstId, String statusCd,
			ProductEntity productEntity, Map<String, String> updateParam) {
		updateParam.put("v_STATUS_CD", statusCd);
		updateParam.put("v_STOP_RENT_TIME", productEntity.getStopRentTime());
		updateParam.put("v_PHONE_NUMBER", phoneNumber);
		updateParam.put("v_EXT_PROD_INST_ID", extProdInstId);
		updateParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
	}

	// 卡资源
	private void setCardResource(ProductEntity productEntity, Node resInst) {
		if (resInst != null) {
			String iccid = resInst.selectSingleNode("./ATTR[@CD=\"60020005\"]/@VAL") != null
					? resInst.selectSingleNode("./ATTR[@CD=\"60020005\"]/@VAL").getText() : null;
			String cimsi = resInst.selectSingleNode("./ATTR[@CD=\"60020002\"]/@VAL") != null
					? resInst.selectSingleNode("./ATTR[@CD=\"60020002\"]/@VAL").getText() : null;
			String gimsi = resInst.selectSingleNode("./ATTR[@CD=\"60020003\"]/@VAL") != null
					? resInst.selectSingleNode("./ATTR[@CD=\"60020003\"]/@VAL").getText() : null;
			String limsi = resInst.selectSingleNode("./ATTR[@CD=\"60020004\"]/@VAL") != null
					? resInst.selectSingleNode("./ATTR[@CD=\"60020004\"]/@VAL").getText() : null;
			productEntity.setIccid(iccid);
			productEntity.setCimsi(cimsi);
			productEntity.setGimsi(gimsi);
			productEntity.setLimsi(limsi);
		}
	}

	// 查资产取起租时间
	private void getBeginRentTime(ProductEntity productEntity) throws Exception {
		String productInstId = productEntity.getProdInstId();
		if (StringUtils.isNotBlank(productInstId) && !productInstId.startsWith(":")) {
			QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
			req.setProdInstId(Long.valueOf(productInstId));
			// 远程调用
			QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(req);
			// 查历史表
			if (rsp == null || rsp.getProdInstDetail() == null) {
				LOG.debug("查询接入类产品实例详情为空");
				QueryProdInstMutilReqVo reqVo = new QueryProdInstMutilReqVo();
				reqVo.setProdInstId(Long.valueOf(productInstId));
				// 远程调用
				ProdInstListMutilRsp rspVo = queryProdInstMutilSmo.qryHisAccProdInstListLocal(reqVo);
				if (rspVo == null || rspVo.getAccProdInsts() == null || rspVo.getAccProdInsts().size() == 0) {
					LOG.debug("查询历史接入类产品实例详情为空");
					throw new Exception("productInstId:" + productInstId + "查询历史接入类产品实例详情为空");
				}
				// 起租时间
				if (null != rspVo.getAccProdInsts().get(0).getBeginRentDate()) {
					productEntity.setBeginRentTime(getFormatDate(rspVo.getAccProdInsts().get(0).getBeginRentDate()));
				} else {
					throw new Exception("prodInstId" + productInstId + "产品实例的起租时间为空！");
				}
				// 竣工时间
				if (null != rspVo.getAccProdInsts().get(0).getFirstFinishDate()) {
					productEntity.setFinishTime(getFormatDate(rspVo.getAccProdInsts().get(0).getFirstFinishDate()));
				} else {
					productEntity.setFinishTime(getFormatDate(rspVo.getAccProdInsts().get(0).getBeginRentDate()));
				}
			} else {
				// 起租时间
				if (null != rsp.getProdInstDetail().getBeginRentDate()) {
					productEntity.setBeginRentTime(getFormatDate(rsp.getProdInstDetail().getBeginRentDate()));
				} else {
					throw new Exception("prodInstId" + productInstId + "产品实例的起租时间为空！");
				}
				// 竣工时间
				if (null != rsp.getProdInstDetail().getFirstFinishDate()) {
					productEntity.setFinishTime(getFormatDate(rsp.getProdInstDetail().getFirstFinishDate()));
				} else {
					productEntity.setFinishTime(getFormatDate(rsp.getProdInstDetail().getBeginRentDate()));
				}
			}
		}
	}

	// 调用中心服务获取拆机时间
	private void getStopRentTime(ProductEntity productEntity) throws Exception {
		QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
		req.setProdInstId(Long.valueOf(productEntity.getProdInstId()));
		// 远程调用
		QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(req);
		// 查历史表
		if (rsp == null || rsp.getProdInstDetail() == null) {
			LOG.debug("查询接入类产品实例详情为空");
			QueryProdInstMutilReqVo reqVo = new QueryProdInstMutilReqVo();
			reqVo.setProdInstId(Long.valueOf(productEntity.getProdInstId()));
			// 远程调用
			ProdInstListMutilRsp rspVo = queryProdInstMutilSmo.qryHisAccProdInstListLocal(reqVo);
			if (rspVo == null || rspVo.getAccProdInsts() == null || rspVo.getAccProdInsts().size() == 0) {
				LOG.debug("查询历史接入类产品实例详情为空");
				throw new Exception("productInstId:" + productEntity.getProdInstId() + "查询历史接入类产品实例详情为空");
			}
			if (null != rspVo.getAccProdInsts().get(0).getStopRentDate()) {
				// 拆机时间
				productEntity.setStopRentTime(getFormatDate(rspVo.getAccProdInsts().get(0).getStopRentDate()));
			} else {
				throw new Exception("prodInstId" + productEntity.getProdInstId() + "获取产品实例的拆机时间为空！");
			}
		} else {
			if (null != rsp.getProdInstDetail().getStopRentDate()) {
				// 拆机时间
				productEntity.setStopRentTime(getFormatDate(rsp.getProdInstDetail().getStopRentDate()));
			} else {
				throw new Exception("prodInstId" + productEntity.getProdInstId() + "获取产品实例的拆机时间为空！");
			}
		}
	}

	// 调用中心服务获取extProdInstId
	private String queryExtProdInstIdByCenter(String prodInstId) throws Exception {
		String extProdInstId;
		QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
		req.setProdInstId(Long.valueOf(prodInstId));
		// 远程调用
		QryAccProdInstDetailRsp rsp = queryProdInstMutilSmo.qryAccProdInstDetail(req);
		if (rsp != null && rsp.getProdInstDetail() != null) {
			extProdInstId = rsp.getProdInstDetail().getExtProdInstId();
		} else {
			LOG.debug("查询接入类产品实例详情为空");
			QueryProdInstMutilReqVo reqVo = new QueryProdInstMutilReqVo();
			reqVo.setProdInstId(Long.valueOf(prodInstId));
			// 远程调用
			ProdInstListMutilRsp rspVo = queryProdInstMutilSmo.qryHisAccProdInstListLocal(reqVo);
			if (rspVo == null || rspVo.getAccProdInsts() == null || rspVo.getAccProdInsts().size() == 0) {
				LOG.debug("查询历史接入类产品实例详情为空");
				throw new Exception("productInstId:" + prodInstId + "查询历史接入类产品实例详情为空");
			}
			extProdInstId = rspVo.getAccProdInsts().get(0).getExtProdInstId();
		}
		return extProdInstId;
	}

	// 集团有时候不下发该字段,从本地映射表里查
	private void getProductNameID(ProductEntity productEntity) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("v_ACC_PRODUCT_ID", productEntity.getAccProductId());
		Map<String, String> codeMap = saopAuditDao.queryAccProductName(params);
		if (codeMap == null) {
			productEntity.setAccProductName("");
		} else {
			productEntity.setAccProductName(codeMap.get("ACC_PRODUCT_NAME"));
		}
	}

	// 保存日稽核表
	private void saveProdDailyAudit(String transactionID, ProductEntity productEntity) throws Exception {
		// 如果4g功能产品ext为空，查中心服务
		String extProdInstServId = productEntity.getExtProdInstServId();
		if (StringUtils.isBlank(extProdInstServId) || "0".equals(extProdInstServId)) {
			if (StringUtils.isNotBlank(getLteExtProdInstId(productEntity))) {
				productEntity.setExtProdInstServId(getLteExtProdInstId(productEntity));
			}
		}
		Map<String, String> insertParam = getInsertParam(productEntity);
		insertParam.put("transactionID", transactionID);
		LOG.debug("保存产品日稽核表paras" + insertParam);
		try {
			saopAuditDao.insertDepProdDailyAudit(insertParam);
		} catch (Exception e) {
			// 保存稽核错误日志表
			insertAuditErrLog(transactionID, insertParam, e);
			LOG.error("保存产品日稽核表异常" + e.getMessage());
			throw new Exception("保存产品日稽核表异常" + e.getMessage());
		}
	}

	// 保存产品月稽核表
	private void saveProdMonthAudit(String transactionID, ProductEntity productEntity) throws Exception {
		// 如果4g功能产品ext为空，查中心服务
		String extProdInstServId = productEntity.getExtProdInstServId();
		if (StringUtils.isBlank(extProdInstServId) || "0".equals(extProdInstServId)) {
			if (StringUtils.isNotBlank(getLteExtProdInstId(productEntity))) {
				productEntity.setExtProdInstServId(getLteExtProdInstId(productEntity));
			}
		}
		Map<String, String> insertParam = getInsertParam(productEntity);
		insertParam.put("transactionID", transactionID);
		LOG.debug("保存产品月稽核表paras" + insertParam);
		try {
			saopAuditDao.insertDepProdMonthAudit(insertParam);
		} catch (Exception e) {
			// 保存稽核错误日志表
			insertAuditErrLog(transactionID, insertParam, e);
			LOG.error("保存产品月稽核表异常" + e.getMessage());
			throw new Exception("保存产品月稽核表异常" + e.getMessage());
		}
	}

	// 更新产品日稽核表
	private void updateProdDailyAudit(String transactionID, String oldTransactionId, Map<String, String> updateParam)
			throws Exception {
		if (transactionID.compareTo(oldTransactionId) >= 1) {
			LOG.debug("更新产品日稽核表paras" + updateParam);
			try {
				updateParam.put("transactionId", transactionID);
				saopAuditDao.updateProdDailyAudit(updateParam);
			} catch (Exception e) {
				// 保存稽核错误日志表
				insertAuditErrLog(transactionID, updateParam, e);
				LOG.error("更新产品日稽核表异常" + e.getMessage());
				throw new Exception("更新产品日稽核表异常" + e.getMessage());
			}
		} else {
			super.insertAuditFailLog(auditOriginDataId, AuditConstant.NOT_UPDATE_DATA);
		}
	}

	// 更新月稽核表
	private void updateProdMonthAudit(String transactionID, String oldTransactionId, Map<String, String> updateParam)
			throws Exception {
		if (transactionID.compareTo(oldTransactionId) >= 1) {
			LOG.debug("更新产品月稽核表paras" + updateParam);
			try {
				updateParam.put("transactionId", transactionID);
				saopAuditDao.updateProdMonthAudit(updateParam);
			} catch (Exception e) {
				// 保存稽核错误日志表
				insertAuditErrLog(transactionID, updateParam, e);
				LOG.error("更新产品月稽核表异常" + e.getMessage());
				throw new Exception("更新产品月核表异常" + e.getMessage());
			}
		} else {
			super.insertAuditFailLog(auditOriginDataId, AuditConstant.NOT_UPDATE_DATA);
		}

	}

	// 拼装插表的param
	private Map<String, String> getInsertParam(ProductEntity productEntity) throws Exception {
		Map<String, String> insertParam = new HashMap<String, String>();
		if (StringUtils.isBlank(productEntity.getPhoneNumber())) {
			throw new Exception("phoneNumber不能为空");
		}
		if (StringUtils.isBlank(productEntity.getExtProdInstId())) {
			throw new Exception("extProdInstId不能为空");
		}
		if (StringUtils.isBlank(productEntity.getProvinceId())) {
			throw new Exception("provinceId不能为空");
		}
		if (StringUtils.isBlank(productEntity.getLanId())) {
			throw new Exception("lanID不能为空");
		}
		if (StringUtils.isBlank(productEntity.getStatusCd())) {
			throw new Exception("statusCd不能为空");
		}
		if (StringUtils.isBlank(productEntity.getAccProductId())) {
			throw new Exception("accProductId不能为空");
		}
		if (StringUtils.isBlank(productEntity.getAccProductName())) {
			throw new Exception("accProductName不能为空");
		}
		if (StringUtils.isBlank(productEntity.getBeginRentTime())) {
			throw new Exception("beginRentTime不能为空");
		}
		if (StringUtils.isBlank(productEntity.getFinishTime())) {
			throw new Exception("finishTime不能为空");
		}
		insertParam.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
		insertParam.put("v_EXT_PROD_INST_ID", productEntity.getExtProdInstId());
		insertParam.put("v_PROVINCEID", productEntity.getProvinceId());
		insertParam.put("v_LANID", productEntity.getLanId());
		insertParam.put("v_STATUS_CD", productEntity.getStatusCd());
		insertParam.put("v_ICCID", StringUtils.isEmpty(productEntity.getIccid()) ? "" : productEntity.getIccid());
		insertParam.put("v_CIMSI", StringUtils.isEmpty(productEntity.getCimsi()) ? "" : productEntity.getCimsi());
		insertParam.put("v_GIMSI", StringUtils.isEmpty(productEntity.getGimsi()) ? "" : productEntity.getGimsi());
		insertParam.put("v_LIMSI", StringUtils.isEmpty(productEntity.getLimsi()) ? "" : productEntity.getLimsi());
		insertParam.put("v_ACC_PRODUCT_ID", productEntity.getAccProductId());
		insertParam.put("v_ACC_PRODUCT_NAME", productEntity.getAccProductName());
		insertParam.put("v_BEGIN_RENT_TIME", removeMillisecond(productEntity.getBeginRentTime()));
		insertParam.put("v_FINISH_TIME", removeMillisecond(productEntity.getFinishTime()));
		insertParam.put("v_STOP_RENT_TIME", StringUtils.isEmpty(productEntity.getStopRentTime()) ? ""
				: removeMillisecond(productEntity.getStopRentTime()));
		insertParam.put("v_DEAL_TIME", productEntity.getDealTime());
		insertParam.put("v_AUDIT_DT", processAuditTime(productEntity.getAuditTime()));
		insertParam.put("v_EXT_PROD_INST_SERV_ID", productEntity.getExtProdInstServId());
		return insertParam;
	}

	// 从稽核全量表查获取产品信息
	private void getProdResInfo(ProductEntity productEntity) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("v_PHONE_NUMBER", productEntity.getPhoneNumber());
		params.put("v_EXT_PROD_INST_ID", productEntity.getExtProdInstId());
		Map<String, String> prodResInfoMap = saopAuditDao.getProdResInfo(params);
		if (prodResInfoMap != null) {
			productEntity.setIccid(prodResInfoMap.get("iccid"));
			productEntity.setCimsi(prodResInfoMap.get("cimsi"));
			productEntity.setGimsi(prodResInfoMap.get("gimsi"));
			productEntity.setLimsi(prodResInfoMap.get("limsi"));
		}
	}

	// 获取4G功能产品extProdInstId
	private String getLteExtProdInstId(ProductEntity productEntity) {
		QryFuncProdInstDetailListReqVo reqVo = new QryFuncProdInstDetailListReqVo();
		reqVo.setRegionId(Long.valueOf(productEntity.getLanId()));
		reqVo.setProdInstId(Long.valueOf(productEntity.getProdInstId()));
		List<Long> funcProdIds = new ArrayList<Long>();
		funcProdIds.add(200000000641L);
		reqVo.setFuncProdIds(funcProdIds);
		List<ScopeInfo> scopeInfos = new ArrayList<ScopeInfo>();
		ScopeInfo scopeInfo = new ScopeInfo();
		scopeInfo.setScope("prodInst");
		scopeInfos.add(scopeInfo);
		reqVo.setScopeInfos(scopeInfos);
		QryFuncProdInstDetailListRspVo rspVo = queryProdInstMutilSmo.qryFuncProdInstDetailList(reqVo);

		if (rspVo == null || rspVo.getProdInstDetailList() == null || rspVo.getProdInstDetailList().size() == 0) {
			return "0";
		} else {
			ProdInstVoExtend prodInstVoExtend = rspVo.getProdInstDetailList().get(0).getFuncProdInstDetail();
			return prodInstVoExtend.getExtProdInstId();
		}
	}
}
