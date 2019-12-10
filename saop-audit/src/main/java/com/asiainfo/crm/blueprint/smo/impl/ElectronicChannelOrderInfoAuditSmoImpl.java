package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.crm.bcomm.exception.BError;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 3G集团级统一门户订单信息稽核 参考存过crm_center.dep_audit.deporderinfoaudit
 * 
 * @author chenchao
 *
 */
@Component("electronicChannelOrderInfoAudit")
@Transactional(rollbackFor=Exception.class)
public class ElectronicChannelOrderInfoAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(ElectronicChannelOrderInfoAuditSmoImpl.class);

	/**
	 * 3G集团级统一门户订单信息稽核 参考存过crm_center.dep_audit.deporderinfoaudit
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		LOG.debug("3G集团级统一门户订单信息稽核 数据抽取开始");
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("集团级统一门户订单信息稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		// 解析报文，获取订单信息相关信息
		Node orderIdNode = doc.selectSingleNode(
				"/ContractRoot/SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/EXT_CUST_ORDER_ID");
		String orderId = orderIdNode == null ? "" : orderIdNode.getText();
		Node channelNode = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/CHANNEL_ID");
		String channel = channelNode == null ? "" : channelNode.getText();
		// 若CHANNEL为空，则设为默认值100000001
		channel = StringUtils.isEmpty(channel) ? "100000001" : channel;
		String dealDt = super.processAuditTime(auditInfo.getCreateTime());
		List<Node> nodeList = doc.selectNodes("/ContractRoot/SvcCont/SOO[@type=\"ADD_ORDER_ITEM_GROUP_REQ_TYPE\"]");
		for (Node no : nodeList) {
			String serviceOfferId = no.selectSingleNode("./ORDER_ITEM_GROUP/SERVICE_OFFER_ID").getText();
			String groupId = no.selectSingleNode("./ORDER_ITEM_GROUP/ORDER_ITEM_GROUP_ID").getText();
			String lanId = null;
			String accNbr = null;
			String imsi = null;
			String gimsi = null;
			String mktId = null;
			String mktNbr = null;
			String provinceId = PROVINCE_CODE;
			// 如果订单项分组中没有LAN_ID字段需要从订单信息节点获取
			if (no.selectSingleNode("./ORDER_ITEM_GROUP/LAN_ID") != null) {
				lanId = no.selectSingleNode("./ORDER_ITEM_GROUP/LAN_ID").getText();
			} else {
				Node lanIdNode = no
						.selectSingleNode("./../SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/LAN_ID");
				lanId = lanIdNode == null ? "" : lanIdNode.getText();
			}
			LOG.debug("lanId:" + lanId);
			// 若SERVICE_OFFER_ID为100/4010100000，需要获取更多的数据
			if ("4010100000".equals(serviceOfferId) || "100".equals(serviceOfferId)) {
				Node accNbrNode = doc.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_PROD_INST_REQ_TYPE\"]/PROD_INST/ACC_NBR");
				accNbr = accNbrNode == null ? "" : accNbrNode.getText();
				List<Element> mktNodeList = doc
						.selectNodes("/ContractRoot/SvcCont/SOO[@type=\"SAVE_MKT_RES_INST_REQ_TYPE\"]/MKT_RES_INST");
				for (Element mktNode : mktNodeList) {
					String mktResType = mktNode.selectSingleNode("./MKT_RES_TYPE").getText();
					// 103005/103006000 时取IMSI和GIMSI
					if ("103006000".equals(mktResType) || "103005".equals(mktResType)) {
						List<Element> attrList = mktNode.selectNodes("./ATTR");
						for (Element attr : attrList) {
							if ("21011203".equals(attr.attribute("CD").getText())) {
								imsi = attr.attribute("VAL").getText();
							}
							if ("20002010".equals(attr.attribute("CD").getText())) {
								gimsi = attr.attribute("VAL").getText();
							}
						}
						// 101000/101001000 时取MKT_NBR和MKT_ID
					} else if ("101001000".equals(mktResType) || "101000".equals(mktResType)) {
						mktId = mktNode.selectSingleNode("./MKT_RES_CD").getText();
						mktNbr = mktNode.selectSingleNode("./MKT_RES_INST_CODE").getText();
					}
				}
			}

			// 保存订单信息稽核表param
			Map<String, String> insertParam = new HashMap<String, String>();
			insertParam.put("v_ORDERID", orderId);
			insertParam.put("v_GROUPID", groupId);
			insertParam.put("v_CHANNEL", channel);
			insertParam.put("v_PROVINCEID", provinceId);
			insertParam.put("v_LANID", lanId);
			insertParam.put("v_ACCNBR", accNbr);
			insertParam.put("v_IMSI", imsi);
			insertParam.put("v_GIMSI", gimsi);
			insertParam.put("v_MKTNBR", mktNbr);
			insertParam.put("v_MKTID", mktId);
			insertParam.put("v_DEALTYPE", DEAL_TYPE);
			insertParam.put("v_DEALDT", dealDt);
			//查询是否存在
			int count = saopAuditDao.queryOrderInfoAudit(insertParam);
			try {
				if(count == 0){
					// 将报文解析的数据保存到订单信息稽核表
					saopAuditDao.insertOrderInfoAudit(insertParam);
				}
				LOG.debug("保存到订单信息稽核表成功 param=" + insertParam);
			} catch (Exception e) {
				LOG.error("保存到订单信息稽核表异常：" + e.getMessage());
				throw new BError("保存到订单信息稽核表异常：" + e.getMessage());
			}
		}

		LOG.debug("3G集团级统一门户订单信息稽核 数据抽取结束");

	}

}
