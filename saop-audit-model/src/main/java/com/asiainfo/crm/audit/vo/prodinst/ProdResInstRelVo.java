package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class ProdResInstRelVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录关系标识，主键。
	 */
	private Long prodResInstRelId;
	/**
	 * 记录产品实例标识，外键。
	 */
	private Long prodInstId;
	/**
	 * 记录营销资源类型，LOVB=RES-0003
	 */
	private String mktResType;
	/**
	 * 记录营销资源实例标识，外键。
	 */
	private Long mktResInstId;
	/**
	 * 记录营销资源标识，外键。
	 */
	private Long mktResId;
	/**
	 * 记录产品实例和营销类资源实例的关联方式。LOVB=PRI-C-0024
	 */
	private String relType;
	/**
	 * 记录终端的使用类型，LOVB=PRI-C-0010
	 */
	private String propertyType;
	/**
	 * 记录营销资源数量。
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
	 * 区域标识
	 */
	private Long regionId;

	public Long getProdResInstRelId(){
		return prodResInstRelId;
	}

	public void setProdResInstRelId(Long prodResInstRelId){
		this.prodResInstRelId = prodResInstRelId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getMktResType(){
		return mktResType;
	}

	public void setMktResType(String mktResType){
		this.mktResType = mktResType;
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

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

	public String getPropertyType(){
		return propertyType;
	}

	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
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

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

}
