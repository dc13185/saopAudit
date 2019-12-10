package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 *create time :2017-08-03 14:50:59
 */
public class OfferInstFeeInfoVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录销售品实例费用标识，主键。 
	 */
	   private Long offerInstFeeInfoId;
	 /**
	 * 记录销售品实例标识，外键。 
	 */
	   private Long offerInstId;
	 /**
	 * 记录销售品标识，外键。 
	 */
	   private Long offerId;
	 /**
	 * 记录产品实例标识，外键。 
	 */
	   private Long prodInstId;
	 /**
	 * 记录帐目类型标识，外键。 
	 */
	   private Long acctItemTypeId;
	 /**
	 * 记录币种。LOVB=OTC-0005 
	 */
	   private String currency;
	 /**
	 * 记录优惠比例。 
	 */
	   private Integer ratio;
	 /**
	 * 记录金额。 
	 */
	   private Long amount;
	 /**
	 * 记录优惠方式（直降多少、降至多少、折扣比），LOVB=PRI-C-0014 
	 */
	   private String ratioMethod;
	 /**
	 * 记录是否一口价。LOVB=PUB-C-0006 
	 */
	   private String isFixedPrice;
	 /**
	 * 记录应收金额。 
	 */
	   private Long realAmount;
	 /**
	 * 记录收款单位。 
	 */
	   private Long chargeOrg;
	 /**
	 * 记录委托收款单位。 
	 */
	   private Long commChargeOrg;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	
	public Long getOfferInstFeeInfoId(){
		return offerInstFeeInfoId;
	}
	public void setOfferInstFeeInfoId(Long offerInstFeeInfoId){
		this.offerInstFeeInfoId = offerInstFeeInfoId;
	}
	public Long getOfferInstId(){
		return offerInstId;
	}
	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}
	public Long getOfferId(){
		return offerId;
	}
	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}
	public Long getProdInstId(){
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}
	public Long getAcctItemTypeId(){
		return acctItemTypeId;
	}
	public void setAcctItemTypeId(Long acctItemTypeId){
		this.acctItemTypeId = acctItemTypeId;
	}
	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}
	public Integer getRatio(){
		return ratio;
	}
	public void setRatio(Integer ratio){
		this.ratio = ratio;
	}
	public Long getAmount(){
		return amount;
	}
	public void setAmount(Long amount){
		this.amount = amount;
	}
	public String getRatioMethod(){
		return ratioMethod;
	}
	public void setRatioMethod(String ratioMethod){
		this.ratioMethod = ratioMethod;
	}
	public String getIsFixedPrice(){
		return isFixedPrice;
	}
	public void setIsFixedPrice(String isFixedPrice){
		this.isFixedPrice = isFixedPrice;
	}
	public Long getRealAmount(){
		return realAmount;
	}
	public void setRealAmount(Long realAmount){
		this.realAmount = realAmount;
	}
	public Long getChargeOrg(){
		return chargeOrg;
	}
	public void setChargeOrg(Long chargeOrg){
		this.chargeOrg = chargeOrg;
	}
	public Long getCommChargeOrg(){
		return commChargeOrg;
	}
	public void setCommChargeOrg(Long commChargeOrg){
		this.commChargeOrg = commChargeOrg;
	}
	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}
	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}