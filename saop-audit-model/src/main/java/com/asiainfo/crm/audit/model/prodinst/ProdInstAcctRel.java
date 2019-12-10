package com.asiainfo.crm.audit.model.prodinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;
import java.util.Date;
/**
 *@create
 */
public class ProdInstAcctRel extends BaseEntity{
	
	private static final long serialVersionUID = 1L;


	 /**
	 * 记录帐务关系的唯一编号。
 
	 */
	   private Long prodInstAcctRelId;

	 /**
	 * 为每个帐户生成的唯一编号，只具有逻辑上的含义，没有物理意义。每个帐户标识生成之后，帐户标识在整个服务提供有效期内保持不变。 
	 */
	   private Long acctId;

	 /**
	 * 为每个帐目组生成的唯一编号。 
	 */
	   private Integer acctItemGroupId;

	 /**
	 * 产品实例ID的标识 
	 */
	   private Long prodInstId;

	 /**
	 * 当前帐户所定制的各付款方式的优先级 
	 */
	   private Integer priority;

	 /**
	 * 表达此条定制关系的支付额度类型如全额支付。ACC-C-0210 
	 */
	   private Integer paymentLimitType;

	 /**
	 * 指出本条定制关系的具体额度。其中作为绝对值时，以分为单位。作为百分比时，以万分之一为单位（即表示到小数点后两位）。全额支付是为空 
	 */
	   private Long paymentLimit;

	 /**
	 * 记录额度上限，-1代表不限制。 
	 */
	   private Long upperAmount;

	 /**
	 * 生效时间 
	 */
	   private Date effDate;

	 /**
	 * 失效时间 
	 */
	   private Date expDate;

	 /**
	 * LOVB=ACC-C-0044 
	 */
	   private Integer ifDefaultAcctId;


	 /**
	 *  
	 */
	   private Long lastOrderItemId;


	 /**
	 * 记录协议标识。 
	 */
	   private Long agreeId;





	 /**
	 * 外部帐务关系标识 
	 */
	   private String extProdInstAcctId;

	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	

	public Long getProdInstAcctRelId(){
		return prodInstAcctRelId;
	}
	public void setProdInstAcctRelId(Long prodInstAcctRelId){
		this.prodInstAcctRelId = prodInstAcctRelId;
	}
	public Long getAcctId(){
		return acctId;
	}
	public void setAcctId(Long acctId){
		this.acctId = acctId;
	}
	public Integer getAcctItemGroupId(){
		return acctItemGroupId;
	}
	public void setAcctItemGroupId(Integer acctItemGroupId){
		this.acctItemGroupId = acctItemGroupId;
	}
	public Long getProdInstId(){
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}
	public Integer getPriority(){
		return priority;
	}
	public void setPriority(Integer priority){
		this.priority = priority;
	}
	public Integer getPaymentLimitType(){
		return paymentLimitType;
	}
	public void setPaymentLimitType(Integer paymentLimitType){
		this.paymentLimitType = paymentLimitType;
	}
	public Long getPaymentLimit(){
		return paymentLimit;
	}
	public void setPaymentLimit(Long paymentLimit){
		this.paymentLimit = paymentLimit;
	}
	public Long getUpperAmount(){
		return upperAmount;
	}
	public void setUpperAmount(Long upperAmount){
		this.upperAmount = upperAmount;
	}
	public Date getEffDate(){
		return effDate;
	}
	public void setEffDate(Date effDate){
		this.effDate = effDate;
	}
	public Date getExpDate(){
		return expDate;
	}
	public void setExpDate(Date expDate){
		this.expDate = expDate;
	}
	public Integer getIfDefaultAcctId(){
		return ifDefaultAcctId;
	}
	public void setIfDefaultAcctId(Integer ifDefaultAcctId){
		this.ifDefaultAcctId = ifDefaultAcctId;
	}
	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}
	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}
	public Long getAgreeId(){
		return agreeId;
	}
	public void setAgreeId(Long agreeId){
		this.agreeId = agreeId;
	}
	public String getExtProdInstAcctId(){
		return extProdInstAcctId;
	}
	public void setExtProdInstAcctId(String extProdInstAcctId){
		this.extProdInstAcctId = extProdInstAcctId;
	}
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}