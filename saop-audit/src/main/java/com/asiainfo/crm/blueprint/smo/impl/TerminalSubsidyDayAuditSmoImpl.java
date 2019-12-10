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

/**
 * 终端补贴日增稽核
 * @author chenchao
 *
 */
@Component("terminalSubsidyDayAudit")
public class TerminalSubsidyDayAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(TerminalSubsidyDayAuditSmoImpl.class);

	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {

		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("终端补贴日增上传稽核报文为空");
		}

		Document doc = XmlUtil.parseXml(inXml);
		// 稽核所需字段
//		String transactionID = doc.selectSingleNode("/ContractRoot/TcpCont/TransactionID").getText();
		String orderNo = doc.selectSingleNode("/ContractRoot/SvcCont/OrderNo").getText();
		String agentCode = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/AgentCode").getText();
		String salesDate = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/SalesDate").getText();
		String actionTypeCd = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/ActionCD").getText();
		String termKey = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/TerminalInfo/TermKey").getText();
		String provinceCode = doc.selectSingleNode("/ContractRoot/SvcCont/SalesInfo/BelongInfo/ProvinceCode").getText();
		Map<String, String> param = new HashMap<String, String>();
		param.put("auditDt", super.processAuditTime(auditInfo.getCreateTime()));
		param.put("orderNo", orderNo);
		param.put("agentCode", agentCode);
		param.put("actionTypeCd", actionTypeCd);//'10', '13'
		param.put("specId", termKey);
		param.put("provinceCode", provinceCode);
		param.put("salesDate", salesDate);
		try{
			//插入cep_terminal_day_audit稽核表
			saopAuditDao.insertCepAuditTerminalDay(param);
			LOG.debug("成功插入cep_terminal_day_audit稽核表：", param);
		}catch(Exception e){
			LOG.error("插入cep_terminal_day_audit稽核表失败：", e.getMessage());
			throw new Exception("插入cep_terminal_day_audit稽核表失败："+e.getMessage());
		}
	}
}
