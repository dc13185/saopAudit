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
 * c网全量稽核
 * 
 * @author chenchao
 *
 */
@Component("cProdAudit")
public class CProdAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(CProdAuditSmoImpl.class);

	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {

		String inXml = auditInfo.getDownXml();

		if (StringUtils.isBlank(inXml)) {
			throw new Exception("C网全量稽核报文为空");
		}

		Document doc = XmlUtil.parseXml(inXml);
		String cityCode = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/BelongInfo/CityCode")
				.getText();
		String mdn = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProductNbr").getText();
		String imsi = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/IMSI").getText();
		String state = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProdStatusCd").getText();
		Node payTypeNode = doc
				.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ItemInfo[ItemID=320001]/ItemValue");
		String payType = payTypeNode != null ? payTypeNode.getText() : "";
		String provinceCode = doc
				.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/BelongInfo/ProvinceCode").getText();
		Node lteimsiNode = doc
				.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ItemInfo[ItemID=170]/ItemValue");
		String lteimsi = lteimsiNode != null ? lteimsiNode.getText() : "";
		String orderTypeCd = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/OrderTypeCd").getText();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityCode", lanIdMap.get(lanIdV2Map.get(areaCode2LanIdMap.get(cityCode).substring(0, 5) + "00")));
		params.put("mdn", super.processPhoneNbr(mdn));
		params.put("imsi", imsi);
		params.put("state", state);
		params.put("payType", payType);
		params.put("provinceCode", provinceCode);
		params.put("lteimsi", lteimsi);
		LOG.debug("C网全量稽核:", params);
		try {
			if ("100".equals(orderTypeCd)) {
				// 新装
				saopAuditDao.delCProdAudit(params);
				saopAuditDao.saveCProdAudit(params);
			} else if ("300".equals(orderTypeCd)) {
				// 拆机
				saopAuditDao.delCProdAudit(params);
			} else {
				int count = saopAuditDao.queryCProdAudit(params);
				// 更新
				if (count == 0) {
					saopAuditDao.saveCProdAudit(params);
				} else if (count == 1) {
					saopAuditDao.updateCProdAudit(params);
				} else if (count == 2) {
					saopAuditDao.delCProdAudit(params);
					saopAuditDao.saveCProdAudit(params);
				} else {
					throw new Exception("C网稽核根据mdn:" + mdn + ",imis:" + imsi + "查询出已存在的记录数大于2,需要具体分析数据。");
				}
			}
		} catch (Exception e) {
			LOG.error("C网全量稽核异常:", e.getMessage());
			throw new Exception("C网全量稽核异常:" + e.getMessage());
		}

	}
}
