package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

@Component("terminalSubsidyAllAudit")
public class TerminalSubsidyAllAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo{

	private static final Logger LOG = LoggerFactory.getLogger(TerminalSubsidyAllAuditSmoImpl.class);

	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {

		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("终端补贴全量稽核报文为空");
		}

		Document doc = XmlUtil.parseXml(inXml);
		// 稽核所需字段
		// ProvinceCode|#$|TermKey|#$|TermModel|#$|Provider|#$|InSource|#$|NationalSharing|#$|SaleState|#$|SubState
		String termKey = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/TerminalInfo/TermKey").getText();
		String provinceCode = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/BelongInfo/ProvinceCode").getText();
		String termModel = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/TerminalInfo/TermModel").getText();
		String provider = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/Agentname").getText();
		Map<String, String> param = new HashMap<String, String>();
		param.put("termKey", termKey);
		param.put("provinceCode", provinceCode);
		param.put("TermModel", termModel);
		param.put("Provider", provider);
		param.put("inSource", "2");
		param.put("nationalSharing", "0");
		param.put("saleState", "1");
		param.put("subState", "2");
		try{
			//插入cep_terminal_all_audit稽核表
			saopAuditDao.insertCepAuditTerminalAll(param);
			LOG.debug("成功插入cep_terminal_all_audit稽核表：", param);
		}catch(Exception e){
			LOG.error("插入cep_terminal_all_audit稽核表失败：", e.getMessage());
			throw new Exception("插入cep_terminal_all_audit稽核表失败："+e.getMessage());
		}
	}

}
