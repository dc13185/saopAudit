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

import com.asiainfo.crm.audit.vo.offerinst.OfferProdInstRelVoExtend;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferProdInstRelListReqVo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferProdInstRelRspVo;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.intf.IQueryProdInstMutilSmo;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * 协同通信全量稽核
 */
@Component("XttxAudit")
public class XttxAuditSmoImpl extends SaopAuditBase implements ISaopAuditSmo {

	private static final Logger LOG = LoggerFactory.getLogger(XttxAuditSmoImpl.class);
	
	@Autowired
	IQueryProdInstMutilSmo queryProdInstMutilSmo;
	
	@Override
	public void dataExtraction(AuditInputEntity auditInfo) throws Exception {
		
        String inXml = auditInfo.getDownXml();
		
		if(StringUtils.isBlank(inXml)){
			throw new Exception("协同通信稽核报文为空");
		}
		
		Document doc = XmlUtil.parseXml(inXml);
		
		String busNo = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProductNbr").getText();
		String loginNo = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProductNbr").getText();
		String state = "1001";
		String prodId = doc.selectSingleNode("/ContractRoot/SvcCont/ProdId").getText();
        String prodSpecId = doc.selectSingleNode("/ContractRoot/SvcCont/ProdSpecId").getText();
        //协同通信平台用户类型编码 8 = 阿里旺旺用户；16 = 阿里旺旺通信伴侣；17 = 商贸宝用友版用户；100 = 网络版用户；105 = 用友全讯通
        String userType = "100";
        QryOfferProdInstRelListReqVo reqVo = new QryOfferProdInstRelListReqVo();
        reqVo.setProdInstId(Long.valueOf(prodId));
        QryOfferProdInstRelRspVo rspVo = queryProdInstMutilSmo.getOfferProdInstRelInfo(reqVo);
        List<OfferProdInstRelVoExtend> offerProdInstRels = rspVo.getOfferProdInstRels();
        for(OfferProdInstRelVoExtend offerProdInstRel : offerProdInstRels){
        	//如果offerId=300509001807，userType=16
        	if("300509001807".equals(String.valueOf(offerProdInstRel.getOfferId()))){
        		userType = "16";
        		break;
			}
        }
		Node itemInfoNode = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ItemInfo[ItemID=270036]/ItemValue");
		String tel = null;
		String cellPhone = null;
		if(itemInfoNode != null){
			//手机号码正则表达式 更精确的匹配表达式：^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$
			//第一位为1  第二位为3,4,5,7,8   后9位为0-9任意数字
			String regex = "^1[3|4|5|7|8][0-9]{9}$";
			
			if(itemInfoNode.getText().matches(regex)){
				cellPhone = itemInfoNode.getText();
			}else{
				tel = itemInfoNode.getText();
			}
		}
		//所属企业ID，在江苏省内BSS即组合产品的主接入号码
		String eId = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/UserAcctNbr").getText();
		//所属企业名称
		String eName = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/CustName").getText();
		String openDate = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProdStartDate").getText();
		String updateDate = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/ProdStartDate").getText();
		//1231纳入（insert） ，1232退出（del）
		String ordertypecd = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/OrderTypeCd").getText();
		String areaCode = doc.selectSingleNode("/ContractRoot/SvcCont/BPMOrderEvent/BPMOrder/BelongInfo/AreaCode").getText();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("busNo", busNo);
		params.put("loginNo", loginNo);
		params.put("state", state);
		params.put("userType", userType);
		params.put("tel", tel);
		params.put("cellPhone", cellPhone);
		params.put("eId", eId);
		params.put("eName", eName);
		params.put("openDate", openDate);
		params.put("updateDate", updateDate);
		params.put("ordertypecd", ordertypecd);
		params.put("prodId", prodId);
		params.put("prodSpecId", prodSpecId);
		params.put("areaCode", areaCode);
		try{
			if("1231".equals(ordertypecd)){
				saopAuditDao.saveXttxAudit(params);
			}else if("1232".equals(ordertypecd)){
				saopAuditDao.delXttxAudit(params);
			}
		}catch (Exception e) {
			LOG.error("协同通信全量稽核", e);
		}

	}

}
