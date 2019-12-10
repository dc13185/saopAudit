package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 3G产品激活状态稽核 参考存过：crm_center.dep_audit.depprodactivationaudit
 * 
 */
@Component("ElectronicChannelActiveTimeAudit")
public class ElectronicChannelActiveTimeAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(ElectronicChannelActiveTimeAuditSmoImpl.class);
	
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		LOG.debug("3G产品激活状态稽核 数据抽取开始");
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("3G产品激活状态稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		// 解析报文，获取产品激活状态相关信息
		// 获取原报文如： :getProdInstIdByAccNbr(8320100,025,18012986365,12)
		String prodInstId = doc
				.selectSingleNode("/ContractRoot/SvcCont/SOO[@type=\"SAVE_PROD_INST_REQ_TYPE\"]/PROD_INST/PROD_INST_ID")
				.getText();

		String tempStr = null;
		String phoneNumer = null;
		String lanId = null;
		String provinceId = PROVINCE_CODE;
		String areaCode = null;
		// 先按"("切割成： [":getProdInstIdByAccNbr","8320100,025,18012986365,12)"]
		if (StringUtils.isNotBlank(prodInstId) && prodInstId.split("\\(").length == 2) {
			tempStr = prodInstId.split("\\(")[1];// "8320100,025,18012986365,12)"
		}
		// 再按","切割成： ["8320100","025","18012986365","12)"]
		if (StringUtils.isNotBlank(tempStr) && tempStr.split(",").length == 4) {
			phoneNumer = tempStr.split(",")[2];
			lanId = tempStr.split(",")[0];
			areaCode = tempStr.split(",")[1];
		}
		LOG.debug("lanId:" + lanId);
		String activeTime = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_PROD_INST_REQ_TYPE\"]/PROD_INST/BEGIN_RENT_TIME")
				.getText();
		String dealDt = super.processAuditTime(auditInfo.getCreateTime());
		try {
			DateUtils.parseDate(activeTime, "yyyyMMddHHmmss");
		} catch (Exception ex) {
			throw new Exception("激活时间格式错误，正确格式为：yyyyMMddHHmmss");
		}
		Map<String, String> insertParam = new HashMap<String, String>();
		insertParam.put("v_PHONENUMBER", phoneNumer);
		insertParam.put("v_PROVINCEID", provinceId);
		insertParam.put("v_LANID", lanId);
		insertParam.put("v_AREACODE", areaCode);
		insertParam.put("v_ACTIVETIME", activeTime);
		insertParam.put("v_DEALTYPE", DEAL_TYPE);
		insertParam.put("v_DEALDT", dealDt);
		try {
			// 保存到3G产品激活状态稽核表
			saopAuditDao.insertActiveAudit(insertParam);
			LOG.debug("保存到3G产品激活状态稽核表成功 param=" + insertParam);
		} catch (Exception e) {
			throw new Exception("保存到3G产品激活状态稽核表异常：" + e.getMessage());
		}

		LOG.debug("3G产品激活状态稽核 数据抽取结束");

	}

}
