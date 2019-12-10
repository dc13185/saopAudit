package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 集约互联网业务两级数据稽核（政企稽核）参考存过：DEP_INTERNET_BUSINESS_AUDIT.pck
 * 
 * @author chenchao
 *
 */
@Component("governmentEnterpriseAudit")
public class GovernmentEnterpriseAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(GovernmentEnterpriseAuditSmoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInputEntity) throws Exception {
		String inXml = auditInputEntity.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("政企稽核：报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		Node preHandleFlagNode = doc.selectSingleNode("/ContractRoot/SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/PRE_HANDLE_FLAG");
		String preHandleFlag = preHandleFlagNode != null ? preHandleFlagNode.getText() : "";
		// 不是正式单抛异常
		if(!"T".equals(preHandleFlag)){
			throw new Exception("政企稽核：不是正式单");
		}
		// 动作类型
		Node serviceOfferIdNode = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"ADD_ORDER_ITEM_GROUP_REQ_TYPE\"]/ORDER_ITEM_GROUP[SERVICE_OFFER_ID=\"4010100000\" or SERVICE_OFFER_ID=\"4020100000\"]/SERVICE_OFFER_ID");
		String serviceOfferId = serviceOfferIdNode != null ? serviceOfferIdNode.getText() : "";
		// serviceOfferId为空返回
		if(StringUtils.isEmpty(serviceOfferId)){
			return;
		}
		// 客户ID
		String custId = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/CUST_ID")
				.getText();
		// 报文中格式:getCustIdByExtId(8511300,,1000026018940000,$-1$)
		if (custId != null && custId.split(",").length >= 3) {
			custId = custId.split(",")[2];
		}
		// 客户id为空抛异常
		if(StringUtils.isEmpty(serviceOfferId)){
			throw new Exception("政企稽核：custId为空");
		}
		String statusCd = "";
		String accessNbr = "";
		String extProdInstId = "";
		String productNbr = "";
		String oldTransactionId = "";
		List<Node> prodInstNodes;
		String statusDt = doc.selectSingleNode("/ContractRoot/TcpCont/ReqTime").getText();
		String transactionId = doc.selectSingleNode("/ContractRoot/TcpCont/TransactionID").getText();
		Map<String, String> params = new HashMap<String, String>();
		params.put("proviceCode", PROVINCE_CODE);
		params.put("statusDt", statusDt);
		params.put("transactionId", transactionId);
		try {
			if ("4010100000".equals(serviceOfferId)) {// 新装
				prodInstNodes = doc.selectNodes(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_PROD_INST_REQ_TYPE\"]/PROD_INST[PROD_INST_ID=ACC_PROD_INST_ID]");
				statusCd = "100000";
				if (prodInstNodes != null) {
					for(Node prodInstNode : prodInstNodes){
						accessNbr = prodInstNode.selectSingleNode("./ACC_NBR").getText();
						extProdInstId = prodInstNode.selectSingleNode("./EXT_PROD_INST_ID").getText();
						productNbr = prodInstNode.selectSingleNode("./PRODUCT_NBR").getText();
						params.put("statusCd", statusCd);
						params.put("accessNbr", accessNbr);
						params.put("extProdInstId", extProdInstId);
						params.put("productNbr", productNbr);
						params.put("dealFlag", DEAL_TYPE);
						params.put("proviceCode", PROVINCE_CODE);
						params.put("custId", custId);
						oldTransactionId = saopAuditDao.queryInternetBusProdAudit(extProdInstId);
						if (StringUtils.isEmpty(oldTransactionId)) {
							saopAuditDao.saveInternetBusProdAudit(params);
						}
					}
				}
			} else if ("4020100000".equals(serviceOfferId)) {// 拆机
				statusCd = "110000";
				prodInstNodes = doc.selectNodes(
						"/ContractRoot/SvcCont/SOO[@type=\"DEL_PROD_INST_REQ_TYPE\"]/PROD_INST[PROD_INST_ID=ACC_PROD_INST_ID]");
				if(prodInstNodes != null){
					for(Node prodInstNode : prodInstNodes){
						extProdInstId = prodInstNode.selectSingleNode("./PROD_INST_ID").getText();
						if (extProdInstId != null && extProdInstId.split(",").length >= 3) {
							extProdInstId = extProdInstId.split(",")[2];
						}
						params.put("statusCd", statusCd);
						params.put("extProdInstId", extProdInstId);
						saopAuditDao.updateInternetBusProdAudit(params);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("政企稽核异常：", e.getMessage());
			throw new Exception("政企稽核数据库操作异常：" + e.getMessage());
		}
	}
}
