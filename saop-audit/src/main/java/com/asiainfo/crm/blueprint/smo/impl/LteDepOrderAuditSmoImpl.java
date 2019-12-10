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

import com.asiainfo.crm.bcomm.exception.BError;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 4G订单信息稽核及订单费用稽核 参考存过：dep_audit.depOrderAudits_for4G
 * 
 */
@Component("depOrderAudit4G")
public class LteDepOrderAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo{

	private static final Logger LOG = LoggerFactory.getLogger(LteDepOrderAuditSmoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInfo)  throws Exception{
		LOG.debug("4G订单信息稽核及订单费用稽核 数据抽取开始");
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new BError("4G订单信息稽核及订单费用稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);

		// 从报文中获取订单信息
		String orderId = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/EXT_CUST_ORDER_ID")
				.getText();
		String lanId = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"ADD_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/LAN_ID")
				.getText();
		LOG.debug("lanId:" + lanId);
		String provinceId = getProvinceId(lanId);
		String dealDt = super.processAuditTime(auditInfo.getCreateTime());
		// 查询稽核表数据是否存在
		int count = saopAuditDao.queryOrderInfoCount4G(orderId);
		if(count == 0){
			Map<String, String> insertParam = new HashMap<String, String>();
			insertParam.put("v_PROVINCEID", provinceId);
			insertParam.put("v_DEALTYPE", DEAL_TYPE);
			insertParam.put("v_ORDERID", orderId);
			insertParam.put("v_LANID", lanId);
			insertParam.put("v_DEALDT", dealDt);
			try {
				// 保存到订单信息稽核表
				saopAuditDao.insertOrderInfoAudit4G(insertParam);
				LOG.debug("保存4G订单信息稽核表成功 param=" + insertParam);
			} catch (Exception e) {
				LOG.error("保存4G订单信息稽核表异常：" + e.getMessage());
				throw new BError("保存4G订单信息稽核表异常:" + e.getMessage());
			}
		}

		// 订单费用稽核
		List<Node> nodes = doc.selectNodes("/ContractRoot/SvcCont/SOO[@type=\"ADD_ACCT_ITEM_REQ_TYPE\"]/ACCT_ITEM");
		for (Node node : nodes) {
			String acctType = node.selectSingleNode("./ACCT_ITEM_TYPE_ID").getText();
			String amount = node.selectSingleNode("./AMOUNT").getText();
			// 查询稽核表数据是否存在
			Map<String, String> qCountParam = new HashMap<String, String>();
			qCountParam.put("v_ORDERID", orderId);
			qCountParam.put("v_ACCTTYPE", acctType);
			count = saopAuditDao.queryOrderChargeCount4G(qCountParam);
			// 如果不存在，新增
			if (0 == count && null != acctType) {
				Map<String, String> insertOderChgParam = new HashMap<String, String>();
				insertOderChgParam.put("v_ORDERID", orderId);
				insertOderChgParam.put("v_ACCTTYPE", acctType);
				insertOderChgParam.put("v_AMOUNT", amount);
				insertOderChgParam.put("v_PROVINCEID", provinceId);
				insertOderChgParam.put("v_LANID", lanId);
				insertOderChgParam.put("v_DEALTYPE", DEAL_TYPE);
				insertOderChgParam.put("v_REQTIME", dealDt);
				try {
					// 保存到订单费用稽核表
					saopAuditDao.insertOrderCharge4G(insertOderChgParam);
					LOG.debug("保存4G订单费用稽核表成功 param=" + insertOderChgParam);
				} catch (Exception e) {
					LOG.error("保存4G订单费用稽核表异常：" + e.getMessage());
					throw new BError("保存4G订单费用稽核表异常:" + e.getMessage());
				}
			} else {
				// 存在就更新
				// 查出稽核表中的费用
				Map<String, String> qAmountParam = new HashMap<String, String>();
				qAmountParam.put("v_ORDERID", orderId);
				qAmountParam.put("v_ACCTTYPE", acctType);
				String amount2 = saopAuditDao.queryOrderChargeAmount4G(qAmountParam);
				amount = String.valueOf(Integer.valueOf(amount) + Integer.valueOf(amount2));
				try {
					Map<String, String> updAmountParam = new HashMap<String, String>();
					updAmountParam.put("v_ORDERID", orderId);
					updAmountParam.put("v_ACCTTYPE", acctType);
					updAmountParam.put("v_AMOUNT", amount);
					// 更新费用到订单信息稽核表
					saopAuditDao.updateOrderChargeAudit4G(updAmountParam);
					LOG.debug("更新4G订单费用稽核表成功 param=" + updAmountParam);
				} catch (Exception e) {
					LOG.error("更新4G订单费用稽核表异常：" + e.getMessage());
					throw new BError("更新4G订单费用稽核表异常:" + e.getMessage());
				}
			}
		}

		LOG.debug("4G订单信息稽核及订单费用稽核 数据抽取结束");
	}
}
