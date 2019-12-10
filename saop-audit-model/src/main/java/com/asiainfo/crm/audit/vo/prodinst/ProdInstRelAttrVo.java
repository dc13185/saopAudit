package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import java.util.Date;
/**
 *@create
 */
public class ProdInstRelAttrVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 产品实例关系属性标识 
	 */
	   private Long prodInstRelAttrId;
	 /**
	 * 记录产品实例关系标识，主键。 
	 */
	   private Long prodInstRelId;
	 /**
	 * 属性标识 
	 */
	   private Long attrId;
	 /**
	 * 记录属性值。 
	 */
	   private String attrValue;
	 /**
	 * 记录产品属性信息业务变更的时间，保持档案时间的连续性，手动维护或人工信息维护不需进历史表用修改时间表达，不用修改这个时间，由客户发起的要进历史表。 
	 */
	   private Date busiModDate;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	
	public Long getProdInstRelAttrId(){
		return prodInstRelAttrId;
	}
	public void setProdInstRelAttrId(Long prodInstRelAttrId){
		this.prodInstRelAttrId = prodInstRelAttrId;
	}
	public Long getProdInstRelId(){
		return prodInstRelId;
	}
	public void setProdInstRelId(Long prodInstRelId){
		this.prodInstRelId = prodInstRelId;
	}
	public Long getAttrId(){
		return attrId;
	}
	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}
	public String getAttrValue(){
		return attrValue;
	}
	public void setAttrValue(String attrValue){
		this.attrValue = attrValue;
	}
	public Date getBusiModDate(){
		return busiModDate;
	}
	public void setBusiModDate(Date busiModDate){
		this.busiModDate = busiModDate;
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