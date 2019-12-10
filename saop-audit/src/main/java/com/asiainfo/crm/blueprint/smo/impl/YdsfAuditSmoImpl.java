package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.List;

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
 * @author 周勇 解析一点收费报文，参考存过crm_center.yisf_audit.pck
 */
@Component("ydsfAudit")
public class YdsfAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(YdsfAuditSmoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInputEntity) throws Exception {
		String inXml = auditInputEntity.getDownXml();
		if (StringUtils.isEmpty(inXml)) {
			throw new Exception("获取一点收费XML为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		String xpath = "/ContractRoot/SvcCont/SOO[@type=\"ADD_ORDER_ITEM_GROUP_REQ_TYPE\"]";
		List<Node> nodeList = doc.selectNodes(xpath);
		if (nodeList == null || nodeList.size() == 0) {
			LOG.debug("不匹配数据不处理");
			return;
		}
		for (Node node : nodeList) {
			String orderItemGroupId = node.selectSingleNode("./ORDER_ITEM_GROUP/ORDER_ITEM_GROUP_ID").getText();
			String hCode = node.selectSingleNode("./ORDER_ITEM_GROUP/LAN_ID").getText();
			String areaCode = lanId2AreaCodeMap.get(hCode) == null ? "" : lanId2AreaCodeMap.get(hCode);
			String serviceOfferId = node.selectSingleNode("./ORDER_ITEM_GROUP/SERVICE_OFFER_ID").getText();
			String chargeState = "";
			if ("4040100029".equals(serviceOfferId)) {
				chargeState = "212";
			} else if ("4040100030".equals(serviceOfferId)) {
				chargeState = "213";
			} else {
				chargeState = "";
			}
			Node extCustOrderIdNode = doc
					.selectSingleNode("/ContractRoot/SvcCont/SOO/CUSTOMER_ORDER/EXT_CUST_ORDER_ID");
			String coNbr = extCustOrderIdNode == null ? "" : extCustOrderIdNode.getText();
			String tranId = doc.selectSingleNode("/ContractRoot/TcpCont/TransactionID").getText();
			xpath = "/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='" + orderItemGroupId
					+ "']/PROD_INST/ACC_NBR";
			Node prodNbrNode = doc.selectSingleNode(xpath);
			String prodNbr = (prodNbrNode == null) ? null : prodNbrNode.getText();
			if (prodNbr == null) {
				xpath = "/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='" + orderItemGroupId
						+ "']/PROD_INST_ACCT/PROD_INST_ID";
				prodNbrNode = doc.selectSingleNode(xpath);
				prodNbr = prodNbrNode.getText();// :getProdInstIdByAccNbr(8320200,0510,81189677,10)
												// 类似这种
				String[] arr = prodNbr.split(",");
				prodNbr = arr[2];
			}
			Node servIdNode = doc.selectSingleNode("/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='"
					+ orderItemGroupId + "']/PROD_INST/ATTR[@CD=\"101400001\"]/@VAL");
			String servId = servIdNode == null ? "" : servIdNode.getText();
			String custId = doc.selectSingleNode("/ContractRoot/SvcCont/SOO/CUSTOMER_ORDER/CUST_ID").getText();// <CUST_ID>:getCustIdByExtId(8320200,0510,1000020021550000,$100$)</CUST_ID>
			String[] arr = custId.split(",");
			custId = arr[2];
			Node prodCodeNode = doc.selectSingleNode("/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='"
					+ orderItemGroupId + "']/PROD_INST/PRODUCT_NBR");
			String prodCode = (prodCodeNode == null) ? "" : prodCodeNode.getText();
			Node acctIdNode = doc.selectSingleNode("/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='"
					+ orderItemGroupId + "']/PROD_INST_ACCT/ACCOUNT_ID");
			String acctId = (acctIdNode == null) ? null : acctIdNode.getText();// <ACCOUNT_ID>:getAccountIdByExtId(8320200,0510,5102220055,$-318700011$)</ACCOUNT_ID>
			if (acctId != null) {
				arr = acctId.split(",");
				acctId = arr[2];
			}
			xpath = "/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='" + orderItemGroupId
					+ "']/PROD_INST_ACCT/ATTR[@CD=\"101400002\"]/@VAL";
			Node payPointNode = doc.selectSingleNode(xpath);
			String payPointType = payPointNode == null ? "" : payPointNode.getText();
			xpath = "/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='" + orderItemGroupId
					+ "']/PROD_INST_ACCT/ACCT_ITEM_TYPE_GROUP_ID";
			Node prodInstAcctNode = doc.selectSingleNode(xpath);
			String V_ACCT_ITEM_TYPE_GROUP_ID = prodInstAcctNode == null ? "" : prodInstAcctNode.getText();

			// 取时间，参照存过
			String xml2 = auditInputEntity.getReportXml();
			Document doc2 = XmlUtil.parseXml(xml2);
			Node statusTimeNode = doc2.selectSingleNode("/ContractRoot/SvcCont/SOO/STATUS_DATE");
			String v_STATUSDATETIME = (statusTimeNode == null) ? "" : statusTimeNode.getText();

			xpath = "/ContractRoot/SvcCont/SOO[./ORDER_REQ/ORDER_ITEM_GROUP_ID='" + orderItemGroupId
					+ "']/PROD_INST_ACCT";
			List<Node> accNodeList = doc.selectNodes(xpath);
			String centlimit = "";
			String updateWholePPSD = "";
			for (Node n : accNodeList) {
				centlimit = n.selectSingleNode("./PAYMENT_LIMIT") == null ? ""
						: n.selectSingleNode("./PAYMENT_LIMIT").getText();
				updateWholePPSD = n.selectSingleNode("./ATTR[@CD=\"101400004\"]/@VAL") == null ? ""
						: n.selectSingleNode("./ATTR[@CD=\"101400004\"]/@VAL").getText();
			}
			HashMap<String, String> param = new HashMap<String, String>();
			param.clear();
			param.put("V_PROVINCECODE", PROVINCE_CODE);
			param.put("V_CITYCODE", areaCode);
			param.put("V_TRANSACTIONID", tranId);
			param.put("V_CONBR", coNbr);
			param.put("V_ORDER_ITEM_GROUP_ID", orderItemGroupId);
			param.put("V_PRODUCTNBR", prodNbr);
			param.put("V_SERVID", servId);
			param.put("V_PRODCODE", prodCode);
			param.put("V_PAYPOINTTYPE", payPointType);
			param.put("V_UPDATEWHOLEPPSD", updateWholePPSD);
			param.put("V_STATUSDATETIME", v_STATUSDATETIME);
			param.put("V_PRICEPLANSTARTDATE", v_STATUSDATETIME);
			param.put("V_ACCID", acctId);
			param.put("V_CUSTID", custId);
			param.put("V_PRICEPLANCODE", "");
			param.put("V_CNETLIMIT", centlimit);
			param.put("V_CHARGESTATE", chargeState);
			param.put("V_ACCT_ITEM_TYPE_GROUP_ID", V_ACCT_ITEM_TYPE_GROUP_ID);
			LOG.debug("====param={}", param);
			if ("212".equals(chargeState)) {
				this.saopAuditDao.saveYdsfWholeAudit(param);
				LOG.debug("saveYdsfWholeAudit success");
			} else if ("213".equals(chargeState)) {
				LOG.debug("一点收费退出");
				param.put("V_CHARGESTATE", "213");
				this.saopAuditDao.updateYdsfWholeAudit(param);
			} else {
				LOG.debug("一点收费其他业务");
				this.saopAuditDao.updateYdsfWholeAudit(param);
			}
		}
		LOG.debug("OVER");

	}
}
