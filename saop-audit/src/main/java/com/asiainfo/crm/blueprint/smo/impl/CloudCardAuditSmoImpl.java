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
 * 云卡稽核参考存过：crm_center.DEP_AUDIT.depCloudCardAudit
 * 
 * @author chenchao
 *
 */
@Component("cloudCardAudit")
public class CloudCardAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(CProdAuditSmoImpl.class);

	// @Autowired
	// IOrderQuerySmo orderQuerySmo;

	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("云卡稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
//		String transactionId = doc.selectSingleNode("/ContractRoot/TcpCont/TransactionID").getText();
		String statusDateTime = doc
				.selectSingleNode("/ContractRoot/SvcCont/BusinessEvent/CustOrder/CustOrderLine/StatusDateTime")
				.getText();
		LOG.debug("statusDateTime=" + statusDateTime);
		String phoneNbr = doc.selectSingleNode("/ContractRoot/SvcCont/BusinessEvent/CustOrder/CustOrderLine/ProductNbr")
				.getText();
//		String orderNbr = doc.selectSingleNode("/ContractRoot/SvcCont/BusinessEvent/CustOrder/CustOrderLine/OrderNbr")
//				.getText();
		// 调用受理中心获取lanId
		// QryCustOrderReqVo reqVo = new QryCustOrderReqVo();
		// reqVo.setCustOrderNbr(orderNbr);
		// CustomerOrder customerOrder =
		// orderQuerySmo.qryCustOrderByCoIdOrCoNbr(reqVo);
		// String lanId = String.valueOf(customerOrder.getAcceptLanId());
		String lanId = "";
		String provinceId = PROVINCE_CODE;

		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", phoneNbr);
		params.put("lanId", lanId);
		params.put("provinceId", provinceId);
		params.put("activeTime", statusDateTime);
		params.put("dealDt", auditInfo.getCreateTime());
		String oldTransactionIdDay = saopAuditDao.queryCntCloudcardDay(phoneNbr);
		try {
			if (StringUtils.isEmpty(oldTransactionIdDay)) {
				saopAuditDao.insertDepCloudDayAudit(params);
			}
		} catch (Exception e) {
			LOG.error("保存云卡日稽核表异常：" + e.getMessage());
			throw new Exception("保存云卡日稽核表异常：" + e.getMessage());
		}

		String oldTransactionId = saopAuditDao.queryCntCloudcardMonth(phoneNbr);
		try {
			if (StringUtils.isEmpty(oldTransactionId)) {
				saopAuditDao.insertDepCloudMonthAudit(params);
			}
		} catch (Exception e) {
			LOG.error("保存云卡月稽核表异常：" + e.getMessage());
			throw new Exception("保存云卡月稽核表异常：" + e.getMessage());
		}
	}

}
