package com.asiainfo.crm.audit.model.prodinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;
import java.util.Date;
/**
 *@create
 */
public class ProdInstAttr extends BaseEntity{
	
	private static final long serialVersionUID = 1L;


	 /**
	 * 记录产品实例属性标识，主键。 
	 */
	   private Long prodInstAttrId;

	 /**
	 * 记录上级产品实例属性标识。 
	 */
	   private Long parProdInstAttrId;

	 /**
	 * 记录产品实例I标识，外键。 
	 */
	   private Long prodInstId;

	 /**
	 * 记录属性标识，外键。 
	 */
	   private Long attrId;

	 /**
	 * 属性值标识 
	 */
	   private Long attrValueId;

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

	 /**
	 * 记录生效的时间。 
	 */
	   private Date effDate;

	 /**
	 * 记录失效的时间。 
	 */
	   private Date expDate;
	

	public Long getProdInstAttrId(){
		return prodInstAttrId;
	}
	public void setProdInstAttrId(Long prodInstAttrId){
		this.prodInstAttrId = prodInstAttrId;
	}
	public Long getParProdInstAttrId(){
		return parProdInstAttrId;
	}
	public void setParProdInstAttrId(Long parProdInstAttrId){
		this.parProdInstAttrId = parProdInstAttrId;
	}
	public Long getProdInstId(){
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
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
	
}