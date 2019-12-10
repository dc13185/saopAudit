package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
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
 * 4G产品激活状态稽核 参考存过：dep_audit.depprodactivationaudit_for4G
 * 
 */
@Component("lteActiveTimeAudit")
public class LteActiveTimeAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(LteActiveTimeAuditSmoImpl.class);

	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		LOG.debug("4G产品激活状态稽核 数据抽取开始");
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("4G产品激活状态稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		// 解析报文,获取产品激活状态相关信息
		Node phoneNumerNode = doc
				.selectSingleNode("/ContractRoot/SvcCont/SOO[@type=\"MOD_PROD_INST_REQ_TYPE\"]/PROD_INST/ACC_NBR");
		if (phoneNumerNode == null) {
			return;
		}
		String phoneNumer = phoneNumerNode.getText();
		Node lanIdNode = doc.selectSingleNode(
				"/ContractRoot/SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/LAN_ID");
		String lanId = lanIdNode != null ? lanIdNode.getText() : "";
		LOG.debug("lanId:" + lanId);
		String provinceId = getProvinceId(lanId);
		String areaCode = lanId2AreaCodeMap.get(lanId);
		Node activeTimeNode = doc.selectSingleNode(
				"/ContractRoot/SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/ACCEPT_TIME");
		String activeTime = activeTimeNode != null ? activeTimeNode.getText() : "";
		Map<String, String> insertParam = new HashMap<String, String>();
		insertParam.put("v_PHONENUMBER", phoneNumer);
		insertParam.put("v_PROVINCEID", provinceId);
		insertParam.put("v_LANID", lanId);
		insertParam.put("v_AREACODE", areaCode);
		insertParam.put("v_ACTIVETIME", super.removeMillisecond(activeTime));
		insertParam.put("v_DEALTYPE", DEAL_TYPE);
		insertParam.put("v_DEALDT", super.processAuditTime(auditInfo.getCreateTime()));
		int count = saopAuditDao.queryActiveAudit4G(phoneNumer);
		try {
			if (count == 0) {
				// 保存到稽核表
				saopAuditDao.insertActiveAudit4G(insertParam);
			}
			LOG.debug("保存4G产品激活状态稽核表成功 param=" + insertParam);
		} catch (Exception e) {
			LOG.error("保存4G产品激活状态稽核表异常：" + e.getMessage());
			throw new Exception("保存4G产品激活状态稽核表异常：" + e.getMessage());
		}
		LOG.debug("4G产品激活状态稽核 数据抽取结束");
	}
}
