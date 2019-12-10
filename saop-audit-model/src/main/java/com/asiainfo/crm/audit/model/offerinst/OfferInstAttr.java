package com.asiainfo.crm.audit.model.offerinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *
 */
public class OfferInstAttr extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 销售品实例属性的主键
	 */
	private Long offerInstAttrId;

	/**
	 * 销售品实例ID
	 */
	private Long offerInstId;

	/**
	 * 父级销售品实例属性标识，表达销售品实例属性组
	 */
	private Long parOfferInstAttrId;

	/**
	 * 记录作用对象类型,LOVB=PRI-C-0011
	 */
	private String objType;

	/**
	 * 作用对象标识，，如：销售品实例、产品实例
	 */
	private Long objId;

	/**
	 * 业务对象属性规格业务编码
	 */
	private Long attrId;

	/**
	 * 记录属性值的主键
	 */
	private Long attrValueId;

	/**
	 * 记录属性值。
	 */
	private String attrValue;

	/**
	 * 记录生效的时间。
	 */
	private Date effDate;

	/**
	 * 记录失效的时间。
	 */
	private Date expDate;

	/**
	 * 区域ID
	 */
	private Long applyRegionId;

	/**
	 * 记录产品信息业务变更的时间，保持档案时间的连续性，手动维护或人工信息维护不需进历史表用修改时间表达，不用修改这个时间，由客户发起的要进历史表。(记录的可以是立即竣工的时间或次月生效的时间)
	 */
	private Date busiModEffDate;

	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;

	/**
	 * 区域标识
	 */
	private Long regionId;

	public Long getOfferInstAttrId(){
		return offerInstAttrId;
	}

	public void setOfferInstAttrId(Long offerInstAttrId){
		this.offerInstAttrId = offerInstAttrId;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public Long getParOfferInstAttrId(){
		return parOfferInstAttrId;
	}

	public void setParOfferInstAttrId(Long parOfferInstAttrId){
		this.parOfferInstAttrId = parOfferInstAttrId;
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

	public Date getBusiModEffDate(){
		return busiModEffDate;
	}

	public void setBusiModEffDate(Date busiModEffDate){
		this.busiModEffDate = busiModEffDate;
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
