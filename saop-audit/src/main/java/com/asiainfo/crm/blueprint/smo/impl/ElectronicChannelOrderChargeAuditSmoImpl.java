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
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.crm.bcomm.exception.BError;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 3G集团级统一门户订单费用稽核 参考存过crm_center.dep_audit.deporderchargeaudit
 */
@Component("electronicChannelOrderChargeAudit")
@Transactional(rollbackFor=Exception.class)
public class ElectronicChannelOrderChargeAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo{

	private static final Logger LOG = LoggerFactory.getLogger(ElectronicChannelOrderChargeAuditSmoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		LOG.debug("3G集团级统一门户订单费用稽核 数据抽取开始");
		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("3G集团级统一门户订单费用稽核报文为空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		// 解析报文，获取费用相关信息
		Node node = doc.selectSingleNode("/ContractRoot");
		Node lanIdNode = node
				.selectSingleNode("./SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/LAN_ID");
		String lanId = lanIdNode == null ? "" : lanIdNode.getText();
		LOG.debug("lanId:" + lanId);
		String provinceId = PROVINCE_CODE;
		Node orderIdNode = node
				.selectSingleNode(
						"./SvcCont/SOO[@type=\"SAVE_CUSTOMER_ORDER_REQ_TYPE\"]/CUSTOMER_ORDER/EXT_CUST_ORDER_ID");
		String orderId = orderIdNode == null ? "" : orderIdNode.getText();
		List<Node> nodeList = doc.selectNodes("/ContractRoot/SvcCont/SOO[@type=\"SAVE_ACCT_ITEM_REQ_TYPE\"]/ACCT_ITEM");
		Node groupIdNode = doc
				.selectSingleNode(
						"/ContractRoot/SvcCont/SOO[@type=\"SAVE_ACCT_ITEM_REQ_TYPE\"]/ORDER_REQ/ORDER_ITEM_GROUP_ID");
		String groupId = groupIdNode == null ? "" : groupIdNode.getText();
		String dealDt = super.processAuditTime(auditInfo.getCreateTime());
		for (Node no : nodeList) {
			String acctType = no.selectSingleNode("./ACCT_ITME_TYPE_ID").getText();
			String amount = no.selectSingleNode("./AMOUNT").getText();
			HashMap<String, String> queryCountParam = new HashMap<String, String>();
			queryCountParam.put("orderId", orderId);
			queryCountParam.put("groupId", groupId);
			queryCountParam.put("acctType", acctType);
			// 查询稽核数据是否存在
			int count = saopAuditDao.queryOrderChargeCount(queryCountParam);
			LOG.debug("saopAuditDao.queryOrderChargeCount param=" + queryCountParam);
			// 新增稽核数据param
			Map<String, String> insertAuditparam = null;
			// 更新稽核数据param
			HashMap<String, String> updateAmountParam = null;
			// 若不存在相同订单相同订单项分组相同费用项帐目类型标识，则插入这条数据。
			if (count == 0) {
				if (StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(acctType)) {
					insertAuditparam = new HashMap<String, String>();
					insertAuditparam.put("orderId", orderId);
					insertAuditparam.put("groupId", groupId);
					insertAuditparam.put("acctType", acctType);
					insertAuditparam.put("amount", amount);
					insertAuditparam.put("provinceId", provinceId);
					insertAuditparam.put("lanId", lanId);
					insertAuditparam.put("dealType", DEAL_TYPE);
					insertAuditparam.put("dealDt", dealDt);
					try {
						// 保存稽核数据
						saopAuditDao.insertOrderCharge(insertAuditparam);
						LOG.debug("保存订单费用稽核表成功 param=" + insertAuditparam);
					} catch (Exception e) {
						LOG.error("保存订单费用稽核表异常：" + e.getMessage());
						throw new BError("保存订单费用稽核表异常：" + e.getMessage());
					}
				}
			} else {
				// 查询出来有相同订单号，相同订单项分组，相同费用项标识的，需要合并（直接更新原来插入的数据）
				String amount2 = saopAuditDao.queryOrderChargeAmount(queryCountParam);
				amount = String.valueOf(Integer.parseInt(amount2) + Integer.parseInt(amount));
				updateAmountParam = new HashMap<String, String>();
				updateAmountParam.putAll(queryCountParam);
				updateAmountParam.put("amount", amount);
				try {
					saopAuditDao.updateOrderChargeAudit(updateAmountParam);
					LOG.debug("更新订单费用稽核表成功 param=" + updateAmountParam);
				} catch (Exception e) {
					LOG.error("更新订单费用稽核表异常：" + e.getMessage());
					throw new BError("更新订单费用稽核表异常：" + e.getMessage());
				}
			}
		}

		LOG.debug("3G集团级统一门户订单费用稽核 数据抽取结束");
		
	}

}
