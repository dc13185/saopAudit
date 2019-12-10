package com.asiainfo.crm.audit.model.offerinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;
import java.util.Date;
/**
 *create time :2017-08-03 15:19:48
 */
public class OfferInstAssure extends BaseEntity{
	
	private static final long serialVersionUID = 1L;


	 /**
	 * 记录销售品实例担保信息标识，主键。 
	 */
	   private Long offerInstAssureId;

	 /**
	 * 记录被担保的销售品实例，外键。 
	 */
	   private Long offerInstId;

	 /**
	 * 记录担保类型。LOVB=OFF-C-0002 
	 */
	   private String assureType;

	 /**
	 * 记录担保对象类型。LOVB=PRI-C-0008 
	 */
	   private String assureObjType;

	 /**
	 * 记录担保对象标识（记录员工标识、客户标识）。 
	 */
	   private Long assureObjId;

	 /**
	 * 记录担保生效时间。 
	 */
	   private Date effDate;

	 /**
	 * 记录担保失效时间。 
	 */
	   private Date expDate;

	 /**
	 * 记录适用区域标识。 
	 */
	   private Long applyRegionId;







	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;


	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	

	public Long getOfferInstAssureId(){
		return offerInstAssureId;
	}
	public void setOfferInstAssureId(Long offerInstAssureId){
		this.offerInstAssureId = offerInstAssureId;
	}
	public Long getOfferInstId(){
		return offerInstId;
	}
	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}
	public String getAssureType(){
		return assureType;
	}
	public void setAssureType(String assureType){
		this.assureType = assureType;
	}
	public String getAssureObjType(){
		return assureObjType;
	}
	public void setAssureObjType(String assureObjType){
		this.assureObjType = assureObjType;
	}
	public Long getAssureObjId(){
		return assureObjId;
	}
	public void setAssureObjId(Long assureObjId){
		this.assureObjId = assureObjId;
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
	public Long getApplyRegionId(){
		return applyRegionId;
	}
	public void setApplyRegionId(Long applyRegionId){
		this.applyRegionId = applyRegionId;
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