package com.asiainfo.crm.audit.model.offerinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;
import java.util.Date;
/**
 * create time :2017-08-03 14:45:01
 */
public class OfferInstRel extends BaseEntity{
	
	private static final long serialVersionUID = 1L;


	 /**
	 * 记录销售品实例关系标识，主键。 
	 */
	   private Long offerInstRelId;

	 /**
	 * 记录A销售品实例标识，外键。 
	 */
	   private Long aOfferInstId;

	 /**
	 * 记录Z销售品实例标识，外键。 
	 */
	   private Long zOfferInstId;

	 /**
	 * 记录角色标识。 
	 */
	   private Long roleId;

	 /**
	 * 记录关系类型。LOVB=PRI-0005 
	 */
	   private String relType;

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
	 * 区域标识 
	 */
	   private Long regionId;
	

	public Long getOfferInstRelId(){
		return offerInstRelId;
	}
	public void setOfferInstRelId(Long offerInstRelId){
		this.offerInstRelId = offerInstRelId;
	}
	public Long getaOfferInstId(){
		return aOfferInstId;
	}
	public void setaOfferInstId(Long aOfferInstId){
		this.aOfferInstId = aOfferInstId;
	}
	public Long getzOfferInstId(){
		return zOfferInstId;
	}
	public void setzOfferInstId(Long zOfferInstId){
		this.zOfferInstId = zOfferInstId;
	}
	public Long getRoleId(){
		return roleId;
	}
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	public String getRelType(){
		return relType;
	}
	public void setRelType(String relType){
		this.relType = relType;
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
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}