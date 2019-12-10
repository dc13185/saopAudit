package com.asiainfo.crm.audit.vo.offerinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class OfferInstPaymodeVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录销售品实例付费模式记录标识，主键。
	 */
	private Long paymodeId;
	/**
	 * 记录销售品实例标识，外键。
	 */
	private Long offerInstId;
	/**
	 * 记录销售品的付费模式，LOVB=PRI-0001
	 */
	private String paymentModeCd;
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

	public Long getPaymodeId(){
		return paymodeId;
	}

	public void setPaymodeId(Long paymodeId){
		this.paymodeId = paymodeId;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public String getPaymentModeCd(){
		return paymentModeCd;
	}

	public void setPaymentModeCd(String paymentModeCd){
		this.paymentModeCd = paymentModeCd;
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
