package com.asiainfo.crm.audit.model.prodinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *
 */
public class ProdInst extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录产品实例标识的主键
	 */
	private Long prodInstId;

	/**
	 * 记录产品的标识，外键
	 */
	private Long prodId;

	/**
	 * 记录功能产品实例对应的接入产品实例ID
	 */
	private Long accProdInstId;

	/**
	 * 记录产品使用类型。LOVB=PRI-C-0009
	 */
	private String prodUseType;

	/**
	 * 记录产品实例的业务号码。
	 */
	private String accNum;

	/**
	 * 记录宽带的帐号，如：XXX@adsl。
	 */
	private String account;

	/**
	 * 记录产品的付费模式，LOVB=PRI-0001
	 */
	private String paymentModeCd;

	/**
	 * 记录地址描述信息
	 */
	private String addressDesc;

	/**
	 * 记录产品产权客户的标识。
	 */
	private Long ownerCustId;

	/**
	 * 记录产品鉴权的密码。
	 */
	private String prodInstPwd;

	/**
	 * 记录OSS返回的局向标识。
	 */
	private Long exchId;

	/**
	 * 记录OSS返回的地址标识
	 */
	private Long addressId;

	/**
	 * 地域中的公共管理区域标识
	 */
	private Long regionId;

	/**
	 * 记录本地网标识，数据来源于公共管理区域。
	 */
	private Long lanId;

	/**
	 * 记录后端首次发起激活的请求的接口时间。(仅首次更新，通常用于套卡激活或准实时预付费的产品)
	 */
	private Date actDate;

	/**
	 * 记录起租时间。(由客户或协议决定发起的时间。)
	 */
	private Date beginRentDate;

	/**
	 * 记录停租时间。(由客户或协议决定发起的时间。)
	 */
	private Date stopRentDate;

	/**
	 * 记录实例创建的组织标识，员工选择的任职所对应的组织。
	 */
	private Long createOrgId;

	/**
	 * 记录首次订单竣工时间。（仅记录产品首次开通的时间，优先以开通返回的竣工时间为准。）
	 */
	private Date firstFinishDate;

	/**
	 * 记录产品信息业务变更的时间，保持档案时间的连续性，手动维护或人工信息维护不需进历史表用修改时间表达，不用修改这个时间，由客户发起的要进历史表。
	 */
	private Date busiModDate;

	/**
	 * 记录产品使用客户的标识。
	 */
	private Long useCustId;

	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;

	/**
	*  
	*/
	private Long pointOwnerId;

	/**
	 * 记录产品的业务分类
	 */
	private String prodFuncType;

	/**
	 * 记录外部产品实例标识
	 */
	private String extProdInstId;

	/**
	 * 外系统映射产品实例标识
	 */
	private Long outerProdInstId;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public Long getAccProdInstId(){
		return accProdInstId;
	}

	public void setAccProdInstId(Long accProdInstId){
		this.accProdInstId = accProdInstId;
	}

	public String getProdUseType(){
		return prodUseType;
	}

	public void setProdUseType(String prodUseType){
		this.prodUseType = prodUseType;
	}

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getPaymentModeCd(){
		return paymentModeCd;
	}

	public void setPaymentModeCd(String paymentModeCd){
		this.paymentModeCd = paymentModeCd;
	}

	public String getAddressDesc(){
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc){
		this.addressDesc = addressDesc;
	}

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
	}

	public String getProdInstPwd(){
		return prodInstPwd;
	}

	public void setProdInstPwd(String prodInstPwd){
		this.prodInstPwd = prodInstPwd;
	}

	public Long getExchId(){
		return exchId;
	}

	public void setExchId(Long exchId){
		this.exchId = exchId;
	}

	public Long getAddressId(){
		return addressId;
	}

	public void setAddressId(Long addressId){
		this.addressId = addressId;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

	public Long getLanId(){
		return lanId;
	}

	public void setLanId(Long lanId){
		this.lanId = lanId;
	}

	public Date getActDate(){
		return actDate;
	}

	public void setActDate(Date actDate){
		this.actDate = actDate;
	}

	public Date getBeginRentDate(){
		return beginRentDate;
	}

	public void setBeginRentDate(Date beginRentDate){
		this.beginRentDate = beginRentDate;
	}

	public Date getStopRentDate(){
		return stopRentDate;
	}

	public void setStopRentDate(Date stopRentDate){
		this.stopRentDate = stopRentDate;
	}

	public Long getCreateOrgId(){
		return createOrgId;
	}

	public void setCreateOrgId(Long createOrgId){
		this.createOrgId = createOrgId;
	}

	public Date getFirstFinishDate(){
		return firstFinishDate;
	}

	public void setFirstFinishDate(Date firstFinishDate){
		this.firstFinishDate = firstFinishDate;
	}

	public Date getBusiModDate(){
		return busiModDate;
	}

	public void setBusiModDate(Date busiModDate){
		this.busiModDate = busiModDate;
	}

	public Long getUseCustId(){
		return useCustId;
	}

	public void setUseCustId(Long useCustId){
		this.useCustId = useCustId;
	}

	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}

	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}

	public Long getPointOwnerId(){
		return pointOwnerId;
	}

	public void setPointOwnerId(Long pointOwnerId){
		this.pointOwnerId = pointOwnerId;
	}

	public String getProdFuncType(){
		return prodFuncType;
	}

	public void setProdFuncType(String prodFuncType){
		this.prodFuncType = prodFuncType;
	}

	public String getExtProdInstId(){
		return extProdInstId;
	}

	public void setExtProdInstId(String extProdInstId){
		this.extProdInstId = extProdInstId;
	}

	public Long getOuterProdInstId(){
		return outerProdInstId;
	}

	public void setOuterProdInstId(Long outerProdInstId){
		this.outerProdInstId = outerProdInstId;
	}

}
