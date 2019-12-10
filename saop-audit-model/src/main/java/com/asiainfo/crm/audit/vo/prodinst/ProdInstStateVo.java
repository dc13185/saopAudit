package com.asiainfo.crm.audit.vo.prodinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class ProdInstStateVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录停机记录标识，主键。
	 */
	private Long prodInstStateId;
	/**
	 * 记录产品实例标识，外键。
	 */
	private Long prodInstId;
	/**
	 * 记录产品实例的停机类型。LOVB=PRI-0018
	 */
	private String stopType;
	/**
	 * 记录生效时间。
	 */
	private Date effDate;
	/**
	 * 记录失效时间。
	 */
	private Date expDate;
	/**
	 * 记录停机方向。LOVB=PRI-C-0027
	 */
	private String stopDirection;
	/**
	 * 记录产品实例停机的原因，由主数据配置。
	 */
	private String stopReason;
	/**
	 * 事件触发时间
	 */
	private Date eventDate;
	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;
	/**
	 * 区域标识
	 */
	private Long regionId;

	public Long getProdInstStateId(){
		return prodInstStateId;
	}

	public void setProdInstStateId(Long prodInstStateId){
		this.prodInstStateId = prodInstStateId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getStopType(){
		return stopType;
	}

	public void setStopType(String stopType){
		this.stopType = stopType;
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

	public String getStopDirection(){
		return stopDirection;
	}

	public void setStopDirection(String stopDirection){
		this.stopDirection = stopDirection;
	}

	public String getStopReason(){
		return stopReason;
	}

	public void setStopReason(String stopReason){
		this.stopReason = stopReason;
	}

	public Date getEventDate(){
		return eventDate;
	}

	public void setEventDate(Date eventDate){
		this.eventDate = eventDate;
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
