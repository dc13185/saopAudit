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

import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

@Component("ivpnAudit")
public class IvpnAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo{

	private static final Logger LOG = LoggerFactory.getLogger(IvpnAuditSmoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {

		String inXml = auditInfo.getDownXml();
		if (StringUtils.isBlank(inXml)) {
			throw new Exception("Ivpn稽核报文为空");
		}

		Document doc = XmlUtil.parseXml(inXml);
		List<Node> custOrders = doc.selectNodes("/ContractRoot/SvcCont/BusinessEvent/CustOrder");
		if(null != custOrders && custOrders.size() > 0){
			for(Node custOrder : custOrders){
				// 稽核所需字段
				// ProvinceCode,CustID,CityCode,ProductNbr,CustOrderType,PricePlanCode,ProdCode
				String provinceCode = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/BelongInfo/ProvinceCode").getText();
				// 成员productId
				String productId = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/ProductId").getText();
				// http://crm3.sc.ctc.com/inst-service/service/cust_inst_qryAccProdInstDetail
				// 通过productId查询ownerCustId
				// http://crm3.sc.ctc.com/cust-service/service/cust_cust_qryCustomerList
				// 通过custId查extCustId
				String custID = "";
				String cityCode = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/BelongInfo/CityCode").getText();
				String productNbr = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/ProductNbr").getText();
				String custOrderType = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/OrderTypeCd").getText();
				String pricePlanCode = custOrder.selectSingleNode("./CustOrderLine/PricePlanSET/PricePlanInfo/PricePlanCode").getText();
				String prodCode = custOrder.selectSingleNode("./CustOrderLine/CustOrderLineInfo/ProdCode").getText();
				Map<String, String> param = new HashMap<String, String>();
				param.put("provinceCode",provinceCode);
				param.put("custID", custID);
				param.put("cityCode", cityCode);
				param.put("productNbr", productNbr);
				param.put("custOrderType", custOrderType);
				param.put("pricePlanCode", pricePlanCode);
				param.put("prodCode", prodCode);
				try{
					//插入CEP_IVPN_ALL_AUDIT稽核表
					saopAuditDao.insertIvpnAllAudit(param);
					LOG.debug("成功插入CEP_IVPN_ALL_AUDIT稽核表：", param);
				}catch(Exception e){
					LOG.error("插入CEP_IVPN_ALL_AUDIT稽核表失败：", e.getMessage());
					throw new Exception("插入CEP_IVPN_ALL_AUDIT稽核表失败："+e.getMessage());
				}
			}
		}
		
	}
}
