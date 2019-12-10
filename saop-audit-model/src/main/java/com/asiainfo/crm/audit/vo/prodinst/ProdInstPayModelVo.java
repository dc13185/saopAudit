package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import java.util.Date;
/**
 *
 */
public class ProdInstPayModelVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录产品实例付费模式记录标识，主键。 
	 */
	   private Long paymodeId;
	 /**
	 * 记录产品实例标识，外键。 
	 */
	   private Long prodInstId;
	 /**
	 * 记录产品的付费模式，LOVB=PRI-0001 
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
	
	public Long getPaymodeId(){
		return paymodeId;
	}
	public void setPaymodeId(Long paymodeId){
		this.paymodeId = paymodeId;
	}
	public Long getProdInstId(){
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
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
	
}