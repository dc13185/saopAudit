package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferProdInstRel;
import com.asiainfo.crm.audit.model.prodinst.DevStaffInfo;
import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAttr;
import com.asiainfo.crm.audit.model.prodinst.ProdInstContact;
import com.asiainfo.crm.audit.model.prodinst.ProdInstPaymode;
import com.asiainfo.crm.audit.model.prodinst.ProdInstState;
import com.asiainfo.crm.audit.vo.offerinst.OfferInstVoExtend;
import com.asiainfo.crm.audit.vo.offerinst.OfferProdInstRelVoExtend;

/**
 * @author fortune
 */
public class ProdInstVoExtend extends ProdInst{

	/**
	 * 
	 */
	private static final long serialVersionUID = -233778697762600474L;
	/**
	 * 记录产品名称
	 */
	private String prodName;
	private String custName;
	private String statusCd;

	private String statusName;

	private Long roleId;

	private String paymentModeName;
	private String custGroupName;
	private String roleName;
	/**
	 * 身份证号码.
	 */
	private String certNum;

	/**
	 * 订单流水.
	 */
	private String coId;

	/**
	 * 区域名称.
	 */
	private String regionName;

	/**
	 * 基础销售品实例ID.
	 */
	private Long baseOfferInstId;

	/**
	 * 基础销售品规格ID.
	 */
	private Long baseOfferId;

	/**
	 * 基础销售品规格名称.
	 */
	private String baseOfferName;
	/**
	 * 客户证件类型.
	 */
	private String certTypeName;
	/**
	 * 客户地址信息.
	 */
	private String custAddr;
	/**
	 * 是否默认账户.
	 */
	private Long acctId;
	/**
	 * 合同号.
	 */
	private String acctCd;
	private List<ProdInstRelVoExtend> prodInstRels;

	// private List<ProdInstAccNum> prodInstAccNums;
	private List<ProdInstAccNumVoExtend> prodInstAccNums;
	/**
	 * 产品实例联系人.
	 */
	private List<ProdInstContact> prodInstContactList;
	/**
	 * 产品实例营销资源实例关系.
	 */
	private List<ProdResInstRelVoExtend> prodResInstRelList;
	/**
	 * 产品实例停机记录.
	 */
	private List<ProdInstState> prodInstStateList;
	/**
	 * 产品支付关系列表.
	 */
	private List<ProdInstAcctRelVoExtend> prodInstAcctRelList;

	private List<ProdInstAcctRelVoExtend> prodInstAccts;

	private List<OfferInstVoExtend> mainOfferInsts;
	/**
	 * 属性列表.
	 */
	private List<ProdInstAttr> prodInstAttrList;
	/**
	 * 接入号列表.
	 */
	private List<ProdInstAccNumVoExtend> prodInstAccNumList;
	/**
	 * 关系列表.
	 */
	private List<ProdInstRelVoExtend> prodInstRelList;
	/**
	 * 产品销售品实例关系.
	 */
	private List<OfferProdInstRel> offerProdInstRelList;
	/**
	 * 发展人信息.
	 */
	private List<DevStaffInfo> devStaffInfoList;

	/**
	 * 销售品列表.
	 */
	private List<OfferInstVoExtend> offerInsts;

	/**
	 * 服务列表.
	 */
	private List<ProdInstVoExtend> funcProdInsts;
	/**
	 * 服务参数.
	 */
	private List<ProdInstAttrVoExtend> prodInstAttrs;

	private List<ProdInstAttrVoExtend> funcProdInstAttrs;

	private List<ProdInstPaymode> prodInstPaymodeList;

	private List<ProdInstStateVoExtend> prodInstStates;

	private List<ProdInstContactVoExtend> prodInstContacts;

	private List<ProdInstPaymodeVoExtend> prodInstPaymodes;

	private List<OfferProdInstRelVoExtend> offerProdInstRels;

	private List<DevStaffInfoVoExtend> devStaffInfos;

	private List<ProdResInstRelVoExtend> prodResInstRels;

	public String getCustName(){
		return custName;
	}

	public void setCustName(String custName){
		this.custName = custName;
	}

	public String getPaymentModeName(){
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName){
		this.paymentModeName = paymentModeName;
	}

	public String getCustGroupName(){
		return custGroupName;
	}

	public void setCustGroupName(String custGroupName){
		this.custGroupName = custGroupName;
	}

	public String getCertNum(){
		return certNum;
	}

	public void setCertNum(String certNum){
		this.certNum = certNum;
	}

	public String getCoId(){
		return coId;
	}

	public void setCoId(String coId){
		this.coId = coId;
	}

	public String getRegionName(){
		return regionName;
	}

	public void setRegionName(String regionName){
		this.regionName = regionName;
	}

	public Long getBaseOfferInstId(){
		return baseOfferInstId;
	}

	public void setBaseOfferInstId(Long baseOfferInstId){
		this.baseOfferInstId = baseOfferInstId;
	}

	public Long getBaseOfferId(){
		return baseOfferId;
	}

	public void setBaseOfferId(Long baseOfferId){
		this.baseOfferId = baseOfferId;
	}

	public String getBaseOfferName(){
		return baseOfferName;
	}

	public void setBaseOfferName(String baseOfferName){
		this.baseOfferName = baseOfferName;
	}

	public List<ProdInstAttrVoExtend> getProdInstAttrs(){
		return prodInstAttrs;
	}

	public void setProdInstAttrs(List<ProdInstAttrVoExtend> prodInstAttrs){
		this.prodInstAttrs = prodInstAttrs;
	}

	public List<ProdInstRelVoExtend> getProdInstRels(){
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRelVoExtend> prodInstRels){
		this.prodInstRels = prodInstRels;
	}

	public List<ProdInstAccNumVoExtend> getProdInstAccNums(){
		return prodInstAccNums;
	}

	public void setProdInstAccNums(List<ProdInstAccNumVoExtend> prodInstAccNums){
		this.prodInstAccNums = prodInstAccNums;
	}

	public List<ProdInstAttr> getProdInstAttrList(){
		return prodInstAttrList;
	}

	public void setProdInstAttrList(List<ProdInstAttr> prodInstAttrList){
		this.prodInstAttrList = prodInstAttrList;
	}

	public List<ProdInstAccNumVoExtend> getProdInstAccNumList(){
		return prodInstAccNumList;
	}

	public void setProdInstAccNumList(List<ProdInstAccNumVoExtend> prodInstAccNumList){
		this.prodInstAccNumList = prodInstAccNumList;
	}

	public List<ProdInstRelVoExtend> getProdInstRelList(){
		return prodInstRelList;
	}

	public void setProdInstRelList(List<ProdInstRelVoExtend> prodInstRelList){
		this.prodInstRelList = prodInstRelList;
	}

	public List<OfferProdInstRel> getOfferProdInstRelList(){
		return offerProdInstRelList;
	}

	public void setOfferProdInstRelList(List<OfferProdInstRel> offerProdInstRelList){
		this.offerProdInstRelList = offerProdInstRelList;
	}

	public List<DevStaffInfo> getDevStaffInfoList(){
		return devStaffInfoList;
	}

	public void setDevStaffInfoList(List<DevStaffInfo> devStaffInfoList){
		this.devStaffInfoList = devStaffInfoList;
	}

	public List<ProdInstContact> getProdInstContactList(){
		return prodInstContactList;
	}

	public void setProdInstContactList(List<ProdInstContact> prodInstContactList){
		this.prodInstContactList = prodInstContactList;
	}

	public List<ProdResInstRelVoExtend> getProdResInstRelList(){
		return prodResInstRelList;
	}

	public void setProdResInstRelList(List<ProdResInstRelVoExtend> prodResInstRelList){
		this.prodResInstRelList = prodResInstRelList;
	}

	public List<ProdInstState> getProdInstStateList(){
		return prodInstStateList;
	}

	public void setProdInstStateList(List<ProdInstState> prodInstStateList){
		this.prodInstStateList = prodInstStateList;
	}

	public List<OfferInstVoExtend> getMainOfferInsts(){
		return mainOfferInsts;
	}

	public void setMainOfferInsts(List<OfferInstVoExtend> mainOfferInsts){
		this.mainOfferInsts = mainOfferInsts;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public String getStatusName(){
		return statusName;
	}

	public void setStatusName(String statusName){
		this.statusName = statusName;
	}

	public Long getRoleId(){
		return roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getProdName(){
		return prodName;
	}

	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	public List<OfferInstVoExtend> getOfferInsts(){
		return offerInsts;
	}

	public void setOfferInsts(List<OfferInstVoExtend> offerInsts){
		this.offerInsts = offerInsts;
	}

	public List<ProdInstAcctRelVoExtend> getProdInstAcctRelList(){
		return prodInstAcctRelList;
	}

	public void setProdInstAcctRelList(List<ProdInstAcctRelVoExtend> prodInstAcctRelList){
		this.prodInstAcctRelList = prodInstAcctRelList;
	}

	public List<ProdInstVoExtend> getFuncProdInsts(){
		return funcProdInsts;
	}

	public void setFuncProdInsts(List<ProdInstVoExtend> funcProdInsts){
		this.funcProdInsts = funcProdInsts;
	}

	public List<ProdInstAttrVoExtend> getFuncProdInstAttrs(){
		return funcProdInstAttrs;
	}

	public void setFuncProdInstAttrs(List<ProdInstAttrVoExtend> funcProdInstAttrs){
		this.funcProdInstAttrs = funcProdInstAttrs;
	}

	public List<ProdInstPaymode> getProdInstPaymodeList(){
		return prodInstPaymodeList;
	}

	public void setProdInstPaymodeList(List<ProdInstPaymode> prodInstPaymodeList){
		this.prodInstPaymodeList = prodInstPaymodeList;
	}

	public List<ProdInstAcctRelVoExtend> getProdInstAccts(){
		return prodInstAccts;
	}

	public void setProdInstAccts(List<ProdInstAcctRelVoExtend> prodInstAccts){
		this.prodInstAccts = prodInstAccts;
	}

	public List<ProdInstStateVoExtend> getProdInstStates(){
		return prodInstStates;
	}

	public void setProdInstStates(List<ProdInstStateVoExtend> prodInstStates){
		this.prodInstStates = prodInstStates;
	}

	public List<ProdInstContactVoExtend> getProdInstContacts(){
		return prodInstContacts;
	}

	public void setProdInstContacts(List<ProdInstContactVoExtend> prodInstContacts){
		this.prodInstContacts = prodInstContacts;
	}

	public List<ProdInstPaymodeVoExtend> getProdInstPaymodes(){
		return prodInstPaymodes;
	}

	public void setProdInstPaymodes(List<ProdInstPaymodeVoExtend> prodInstPaymodes){
		this.prodInstPaymodes = prodInstPaymodes;
	}

	public List<OfferProdInstRelVoExtend> getOfferProdInstRels(){
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRelVoExtend> offerProdInstRels){
		this.offerProdInstRels = offerProdInstRels;
	}

	public List<DevStaffInfoVoExtend> getDevStaffInfos(){
		return devStaffInfos;
	}

	public void setDevStaffInfos(List<DevStaffInfoVoExtend> devStaffInfos){
		this.devStaffInfos = devStaffInfos;
	}

	public List<ProdResInstRelVoExtend> getProdResInstRels(){
		return prodResInstRels;
	}

	public void setProdResInstRels(List<ProdResInstRelVoExtend> prodResInstRels){
		this.prodResInstRels = prodResInstRels;
	}

	public String getCertTypeName(){
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName){
		this.certTypeName = certTypeName;
	}

	public String getCustAddr(){
		return custAddr;
	}

	public void setCustAddr(String custAddr){
		this.custAddr = custAddr;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setAcctId(Long acctId){
		this.acctId = acctId;
	}

	public String getAcctCd(){
		return acctCd;
	}

	public void setAcctCd(String acctCd){
		this.acctCd = acctCd;
	}

}
