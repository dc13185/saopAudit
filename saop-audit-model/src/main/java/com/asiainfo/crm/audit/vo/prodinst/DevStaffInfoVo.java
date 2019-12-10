package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
/**
 *@create
 */
public class DevStaffInfoVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录发展人信息标识，主键。 
	 */
	   private Long devStaffInfoId;
	 /**
	 * 记录作用对象类型：订单实例、销售品实例、产品实例、终端实例、属性标识。LOVB=PRI-C-0015 
	 */
	   private String objType;
	 /**
	 * 记录作用对象标识。 
	 */
	   private Long objId;
	 /**
	 * 记录发展员工标识。 
	 */
	   private Long devStaffId;
	 /**
	 * 记录发展人角色类型，LOVB=PRI-C-0016 
	 */
	   private String devStaffType;
	 /**
	 * 记录发展团队标识。 
	 */
	   private Long devOrgId;
	 /**
	 * 客户订单标识 
	 */
	   private Long custOrderId;
	 /**
	 * 记录订单项标识。 
	 */
	   private Long orderItemId;
	 /**
	 * 记录属性标识。 
	 */
	   private Long attrId;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	
	public Long getDevStaffInfoId(){
		return devStaffInfoId;
	}
	public void setDevStaffInfoId(Long devStaffInfoId){
		this.devStaffInfoId = devStaffInfoId;
	}
	public String getObjType(){
		return objType;
	}
	public void setObjType(String objType){
		this.objType = objType;
	}
	public Long getObjId(){
		return objId;
	}
	public void setObjId(Long objId){
		this.objId = objId;
	}
	public Long getDevStaffId(){
		return devStaffId;
	}
	public void setDevStaffId(Long devStaffId){
		this.devStaffId = devStaffId;
	}
	public String getDevStaffType(){
		return devStaffType;
	}
	public void setDevStaffType(String devStaffType){
		this.devStaffType = devStaffType;
	}
	public Long getDevOrgId(){
		return devOrgId;
	}
	public void setDevOrgId(Long devOrgId){
		this.devOrgId = devOrgId;
	}
	public Long getCustOrderId(){
		return custOrderId;
	}
	public void setCustOrderId(Long custOrderId){
		this.custOrderId = custOrderId;
	}
	public Long getOrderItemId(){
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId){
		this.orderItemId = orderItemId;
	}
	public Long getAttrId(){
		return attrId;
	}
	public void setAttrId(Long attrId){
		this.attrId = attrId;
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