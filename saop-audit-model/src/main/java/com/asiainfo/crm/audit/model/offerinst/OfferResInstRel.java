package com.asiainfo.crm.audit.model.offerinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *create time :2017-08-03 15:21:25
 */
public class OfferResInstRel extends BaseEntity{
	
	private static final long serialVersionUID = 1L;


	 /**
	 * 记录销售品实例营销资源实例关系标识，主键。 
	 */
	   private Long offerResInstRelId;

	 /**
	 * 记录销售品实例标识，外键。 
	 */
	   private Long offerInstId;

	 /**
	 * 记录营销资源实例标识，外键。 
	 */
	   private Long mktResInstId;

	 /**
	 * 记录营销资源标识。 
	 */
	   private Long mktResId;

	 /**
	 * 记录该资源的使用产品实例，例如终端的的使用产品实例。 
	 */
	   private Long useProdInstId;

	 /**
	 * 记录关系类型，表达销售品同营销资源的关联方式，LOVB=PRI-C-0023 
	 */
	   private String relType;

	 /**
	 * 记录资源数量。 
	 */
	   private Integer mktResNum;







	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;


	 /**
	 * 营销资源实例编码 
	 */
	   private String mktResInstNbr;

	 /**
	 * 记录外部营销资源实例标识 
	 */
	   private String extMktResInstId;

	 /**
	 * 记录营销资源类型，LOVB=RES-0003 
	 */
	   private String mktResType;

	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	

	public Long getOfferResInstRelId(){
		return offerResInstRelId;
	}
	public void setOfferResInstRelId(Long offerResInstRelId){
		this.offerResInstRelId = offerResInstRelId;
	}
	public Long getOfferInstId(){
		return offerInstId;
	}
	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}
	public Long getMktResInstId(){
		return mktResInstId;
	}
	public void setMktResInstId(Long mktResInstId){
		this.mktResInstId = mktResInstId;
	}
	public Long getMktResId(){
		return mktResId;
	}
	public void setMktResId(Long mktResId){
		this.mktResId = mktResId;
	}
	public Long getUseProdInstId(){
		return useProdInstId;
	}
	public void setUseProdInstId(Long useProdInstId){
		this.useProdInstId = useProdInstId;
	}
	public String getRelType(){
		return relType;
	}
	public void setRelType(String relType){
		this.relType = relType;
	}
	public Integer getMktResNum(){
		return mktResNum;
	}
	public void setMktResNum(Integer mktResNum){
		this.mktResNum = mktResNum;
	}
	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}
	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}
	public String getMktResInstNbr(){
		return mktResInstNbr;
	}
	public void setMktResInstNbr(String mktResInstNbr){
		this.mktResInstNbr = mktResInstNbr;
	}
	public String getExtMktResInstId(){
		return extMktResInstId;
	}
	public void setExtMktResInstId(String extMktResInstId){
		this.extMktResInstId = extMktResInstId;
	}
	public String getMktResType(){
		return mktResType;
	}
	public void setMktResType(String mktResType){
		this.mktResType = mktResType;
	}
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}