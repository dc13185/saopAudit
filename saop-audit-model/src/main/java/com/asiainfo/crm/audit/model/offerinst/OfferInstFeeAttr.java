package com.asiainfo.crm.audit.model.offerinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *
 */
public class OfferInstFeeAttr extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录销售品实例费用属性标识，主键。
	 */
	private Long offerInstFeeAttrId;

	/**
	 * 记录销售品实例费用标识，外键。
	 */
	private Long offerInstFeeInfoId;

	/**
	 * 记录属性标识，外键。
	 */
	private Long attrId;

	/**
	 * 记录属性值标识，外键。
	 */
	private Long attrValueId;

	/**
	 * 记录属性值。
	 */
	private String attrValue;

	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;

	/**
	 * 区域标识
	 */
	private Long regionId;

	public Long getOfferInstFeeAttrId(){
		return offerInstFeeAttrId;
	}

	public void setOfferInstFeeAttrId(Long offerInstFeeAttrId){
		this.offerInstFeeAttrId = offerInstFeeAttrId;
	}

	public Long getOfferInstFeeInfoId(){
		return offerInstFeeInfoId;
	}

	public void setOfferInstFeeInfoId(Long offerInstFeeInfoId){
		this.offerInstFeeInfoId = offerInstFeeInfoId;
	}

	public Long getAttrId(){
		return attrId;
	}

	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}

	public Long getAttrValueId(){
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId){
		this.attrValueId = attrValueId;
	}

	public String getAttrValue(){
		return attrValue;
	}

	public void setAttrValue(String attrValue){
		this.attrValue = attrValue;
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
