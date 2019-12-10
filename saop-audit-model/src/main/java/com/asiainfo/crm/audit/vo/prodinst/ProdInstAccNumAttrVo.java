package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 *@create
 */
public class ProdInstAccNumAttrVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录号码实例属性标识，主键。 
	 */
	   private Long accNumInstAttrId;
	 /**
	 * 记录号码实例标识，外键。 
	 */
	   private Long prodInstAccNumId;
	 /**
	 * 记录属性标识。 
	 */
	   private Long attrId;
	 /**
	 * 记录属性值标识。 
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
	
	public Long getAccNumInstAttrId(){
		return accNumInstAttrId;
	}
	public void setAccNumInstAttrId(Long accNumInstAttrId){
		this.accNumInstAttrId = accNumInstAttrId;
	}
	public Long getProdInstAccNumId(){
		return prodInstAccNumId;
	}
	public void setProdInstAccNumId(Long prodInstAccNumId){
		this.prodInstAccNumId = prodInstAccNumId;
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