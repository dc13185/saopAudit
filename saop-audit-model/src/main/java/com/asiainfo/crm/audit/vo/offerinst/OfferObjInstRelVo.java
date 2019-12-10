package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import java.util.Date;
/**
 *create time :2017-08-03 15:02:45
 */
public class OfferObjInstRelVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录销售品包含对象实例标识，主键。 
	 */
	   private Long offerObjInstRelId;
	 /**
	 * 记录销售品实例标识，外键。 
	 */
	   private Long offerInstId;
	 /**
	 * 记录对象类型，LOVB=PRI-C-0012 
	 */
	   private String objType;
	 /**
	 * 记录对象实例标识（01=客户实例标识 02=帐户标识 03=银行帐号） 
	 */
	   private String objId;
	 /**
	 * 记录角色标识。 
	 */
	   private Long roleId;
	 /**
	 * 记录生效时间。 
	 */
	   private Date effDate;
	 /**
	 * 记录失效时间。 
	 */
	   private Date expDate;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * 销售品包含对象关系ID 
	 */
	   private Long offerObjRelId;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	
	public Long getOfferObjInstRelId(){
		return offerObjInstRelId;
	}
	public void setOfferObjInstRelId(Long offerObjInstRelId){
		this.offerObjInstRelId = offerObjInstRelId;
	}
	public Long getOfferInstId(){
		return offerInstId;
	}
	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}
	public String getObjType(){
		return objType;
	}
	public void setObjType(String objType){
		this.objType = objType;
	}
	public String getObjId(){
		return objId;
	}
	public void setObjId(String objId){
		this.objId = objId;
	}
	public Long getRoleId(){
		return roleId;
	}
	public void setRoleId(Long roleId){
		this.roleId = roleId;
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
	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}
	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}
	public Long getOfferObjRelId(){
		return offerObjRelId;
	}
	public void setOfferObjRelId(Long offerObjRelId){
		this.offerObjRelId = offerObjRelId;
	}
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}