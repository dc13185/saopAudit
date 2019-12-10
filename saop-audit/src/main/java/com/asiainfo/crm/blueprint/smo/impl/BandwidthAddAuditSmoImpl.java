package com.asiainfo.crm.blueprint.smo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.intf.IInstIntfQuerySmo;
import com.asiainfo.crm.blueprint.intf.IQueryProdInstMutilSmo;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * @author 周勇
 *         解析带宽型稽核报文，并入增量稽核和全量稽核表，参考存过crm_center.ddn_audit_proc.ddn_audit_add
 *         除了需要解析报文以外，还需要查询BSS
 */
@Component("bandwidthAddAudit")
public class BandwidthAddAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo{

	private static final Logger LOG = LoggerFactory.getLogger(BandwidthAddAuditSmoImpl.class);
	
	@Autowired
	IInstIntfQuerySmo instIntfQuerySmo;
	
	@Autowired
	IQueryProdInstMutilSmo queryProdInstMutilSmo;
	
	@SuppressWarnings("unchecked")
	@Override
	public void dataExtraction(AuditInputEntity auditInputEntity) throws Exception {
		String inXml = auditInputEntity.getDownXml();
		String buscode = auditInputEntity.getBuscode();
		if (StringUtils.isEmpty(inXml)) {
			throw new Exception("获取一点收费解析带宽型稽核报文空");
		}
		Document doc = XmlUtil.parseXml(inXml);
		String xpath = "/ContractRoot/SvcCont/SOO[@type=\"ADD_ORDER_ITEM_GROUP_REQ_TYPE\"]/ORDER_ITEM_GROUP/SERVICE_OFFER_ID";
		String serverId = doc.selectSingleNode(xpath).getText();
		String custId = "";
		String accNbr = "";
		String beginRentTime = "";// BEGIN_RENT_TIME,yyyyMMdd
		LOG.debug("serverId={}", serverId);
		if ("4040100027".equals(serverId)) {
			custId = doc.selectSingleNode("//CUST_ID").getText();// <CUST_ID>:getCustIdByExtId(8320000,,9990000000000097289,$-1$)</CUST_ID>
			String[] arr = custId.split(",");
			custId = arr[2];
			List<Node> accNbrNodes = doc.selectNodes("//ACC_NBR");
			for (Node accNbrNode : accNbrNodes) {
				accNbr = accNbrNode.getText();
			}
			beginRentTime = doc.selectSingleNode("//BEGIN_RENT_TIME") == null ? ""
					: doc.selectSingleNode("//BEGIN_RENT_TIME").getText();
			LOG.debug("custId = " + custId + "  accNbr=" + accNbr + "  beginRentTime=" + beginRentTime);
			if ("BUS35001".equals(buscode) || "BUS35002".equals(buscode) || "BUS35003".equals(buscode)
					|| "BUS35004".equals(buscode) || "BUS35005".equals(buscode) || "BUS35006".equals(buscode)) {
				List<Node> prodInstList = doc.selectNodes("//SOO[@type=\"SAVE_PROD_INST_REQ_TYPE\"]/PROD_INST");
				String productNbr = "";
				for (Node n : prodInstList) {
					productNbr = n.selectSingleNode("./PRODUCT_NBR").getText();
					if ("35103001001000000001".equals(productNbr) || "35103010001000000001".equals(productNbr)
							|| "35103005001000000001".equals(productNbr) || "".equals(productNbr)
							|| "35103003001000000001".equals(productNbr) || "35103010001000000003".equals(productNbr)
							|| "35103015001000000001".equals(productNbr)) { 
						String lanId = n.selectSingleNode("./LAN_ID").getText();
						LOG.debug("lanIdHcodeMap.containsKey(lanId)" + lanId2AreaCodeMap.containsKey(lanId));
						if (lanId2AreaCodeMap.containsKey(lanId)) {
							String extProdInstId = n.selectSingleNode("./EXT_PROD_INST_ID").getText();
							String sql = "select count(1)  from ddn_audit da  where da.ext_prod_inst_id = '"
									+ extProdInstId + "'";
							HashMap<String, String> sqlParam = new HashMap<String, String>();
							sqlParam.put("SQL", sql);
							String result1 = this.saopAuditDao.getValue(sqlParam);
							sql = "select count(1)  from DDN_AUDIT_DATA_ADD da  where da.ext_prod_inst_id = '"
									+ extProdInstId + "'";
							sqlParam.put("SQL", sql);
							String result2 = this.saopAuditDao.getValue(sqlParam);
							Integer count1 = Integer.parseInt(result1);
							Integer count2 = Integer.parseInt(result2);
							String areaId = lanId2AreaCodeMap.get(lanId);
							LOG.debug("count1=" + count1 + "  count2=" + count2);
							if (count1 == 0 && count2 == 0) {
								Map<String, String> param = new HashMap<String, String>();
								param.put("V_PRODUCTNBR", productNbr);
								String pExtProdInstId = extProdInstId.substring(0, extProdInstId.length() - 1);// <ACCOUNT_ID>:getAccountIdByExtId(8320200,0510,5102220055,$-318700011$)</ACCOUNT_ID>

								param.put("V_PROD_INST_ID", pExtProdInstId);
								param.put("V_EXT_PROD_INST_ID", extProdInstId);
								param.put("V_CUSTID", custId);
								param.put("V_ACCNBR", accNbr);
								param.put("V_BEGINRENTTIME", beginRentTime);
								param.put("V_AREAID", areaId);
								LOG.debug("====saveDdnAuditDataAdd===" + param);
								this.saopAuditDao.saveDdnAuditDataAdd(param);
							} else if (count1 > 0) {
								sql = "update ddn_audit a   set del_dt =to_date('" + beginRentTime
										+ "','yyyymmddhh24miss')   where a.ext_prod_inst_id = '" + extProdInstId
										+ "' and status_cd = '110000'";
								sqlParam.put("SQL", sql);
								this.saopAuditDao.updateAudit(sqlParam);
							} else if (count2 > 0) {
								sql = "update DDN_AUDIT_DATA_ADD a   set del_dt =to_date('" + beginRentTime
										+ "','yyyymmddhh24miss')   where  a.ext_prod_inst_id = '" + extProdInstId
										+ "'  and status_cd = '110000'";
								sqlParam.put("SQL", sql);
								this.saopAuditDao.updateAudit(sqlParam);
							}
						}
					}
				}
			}

		} else if ("4020100000".equals(serverId)) {
			List<Node> nodes = doc.selectNodes("//SOO[@type=\"DEL_PROD_INST_REQ_TYPE\"]");
			for (Node n : nodes) {
				String extProdInstId = n.selectSingleNode("./PROD_INST_ID").getText();
				String prodInstId = "";// <ACCOUNT_ID>:getAccountIdByExtId(8320200,0510,5102220055,$-318700011$)</ACCOUNT_ID>
				if (extProdInstId != null) {
					String arr1[] = extProdInstId.split(",");
					prodInstId = arr1[2];
				}
				String sql = "select count(1)  from ddn_audit da  where da.ext_prod_inst_id = '" + prodInstId + "'";
				HashMap<String, String> sqlParam = new HashMap<String, String>();
				sqlParam.put("SQL", sql);
				String result1 = this.saopAuditDao.getValue(sqlParam);
				sql = "select count(1)  from DDN_AUDIT_DATA_ADD da  where da.ext_prod_inst_id = '" + prodInstId + "'";
				sqlParam.put("SQL", sql);
				String result2 = this.saopAuditDao.getValue(sqlParam);
				Integer count1 = Integer.parseInt(result1);
				Integer count2 = Integer.parseInt(result2);
				LOG.debug("count1=" + count1 + "  count2=" + count2);
				if (count1 > 0) {
					sql = "update ddn_audit a   set status_cd = '110000'   where  a.ext_prod_inst_id = '"
							+ extProdInstId + "' and status_cd = '110000'";
					sqlParam.put("SQL", sql);
					this.saopAuditDao.updateAudit(sqlParam);
				} else if (count2 > 0) {
					sql = "update DDN_AUDIT_DATA_ADD a   set status_cd = '110000'    where  a.ext_prod_inst_id = '"
							+ extProdInstId + "' and status_cd = '110000'";
					sqlParam.put("SQL", sql);
					this.saopAuditDao.updateAudit(sqlParam);
				}
			}
		}
		LOG.debug("====OVER===");
		//更新
		update();
		//转储
		moveOrDelDDNAuditData();
	}
	
	/**
	 * 参考存过ddn_audit_proc.findProdInstId
	 */
	private void update(){
		List<Map<String,String>> list = this.saopAuditDao.queryDdnAuditAdd();
		for(Map<String,String> map : list){
			String extProdInstId = map.get("EXT_PROD_INST_ID");
			String prodInstId = map.get("PROD_INST_ID");
			String pExtProdInstId = map.get("P_EXT_PROD_INST_ID");
			String prodNbr = map.get("PRODUCT_NBR");
			if(StringUtils.isEmpty(prodInstId)){
				Map<String, String> params = new HashMap<String, String>();
				//非IP虚拟网的
				if(!"35103015001000000001".equals(prodNbr)){
					updateProdId(params, extProdInstId);
					params.put("v_PEXTPRODINSTID", pExtProdInstId);
					saopAuditDao.queryProdIdByPExt(params);
					String prodInstId2 = map.get("PROD_INST_ID");
					if(StringUtils.isEmpty(prodInstId2)){
						String extProdInstId2 = map.get("EXT_PROD_INST_ID");
						updateProdId(params, extProdInstId2);
					}
				}else{//IP虚拟网的
					updateProdId(params, extProdInstId);
				}
			}
		}
	}

	//更新prodId
	private void updateProdId(Map<String, String> params, String extProdInstId) {
		String prodInstId;
		//根据ext_prod_inst_id 找当前端的prod_id
		prodInstId = instIntfQuerySmo.getProdInstIdByExtId("", "", extProdInstId, "");
		params.put("V_PRODINSTID", prodInstId);
		params.put("V_EXTPRODINSTID", extProdInstId);
		saopAuditDao.updateDdnAuditDataAdd(params);
	}
	
	/*
	 * 转储 参考存过ddn_audit_proc.moveOrDelDDNAuditData
	 */
	public void moveOrDelDDNAuditData(){
		//删除上上个月的
		saopAuditDao.deleteDdnAudit();
		String condition = "1";
		saopAuditDao.deleteDdnAuditDataAdd(condition);
		//把prod_id不为null的从DDN_AUDIT_DATA_ADD 移到 DDN_AUDIT表里,并删除DDN_AUDIT_DATA_ADD
		saopAuditDao.insertDdnAudit();
		condition = "2";
		saopAuditDao.deleteDdnAuditDataAdd(condition);
	}
	
}
