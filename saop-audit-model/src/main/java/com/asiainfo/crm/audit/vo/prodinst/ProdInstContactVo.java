package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
/**
 *@create
 */
public class ProdInstContactVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录产品联系人的标识，主键。 
	 */
	   private Long prodInstContactId;
	 /**
	 * 记录产品实例标识，外键。 
	 */
	   private Long prodInstId;
	 /**
	 * 记录联系人姓名（数据可以来源于参与人联系信息，这里是副本） 
	 */
	   private String contactName;
	 /**
	 * 记录联系人号码。 
	 */
	   private String contactPhone;
	 /**
	 * 记录是否首选联系人。LOVB=PUB-C-0006 
	 */
	   private String fisrtChange;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	 /**
	 * QQ号 
	 */
	   private String qqCode;
	 /**
	 * 微信号 
	 */
	   private String wxCode;
	 /**
	 * 易信号 
	 */
	   private String yxCode;
	
	public Long getProdInstContactId(){
		return prodInstContactId;
	}
	public void setProdInstContactId(Long prodInstContactId){
		this.prodInstContactId = prodInstContactId;
	}
	public Long getProdInstId(){
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	public String getContactPhone(){
		return contactPhone;
	}
	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}
	public String getFisrtChange(){
		return fisrtChange;
	}
	public void setFisrtChange(String fisrtChange){
		this.fisrtChange = fisrtChange;
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
	public String getQqCode(){
		return qqCode;
	}
	public void setQqCode(String qqCode){
		this.qqCode = qqCode;
	}
	public String getWxCode(){
		return wxCode;
	}
	public void setWxCode(String wxCode){
		this.wxCode = wxCode;
	}
	public String getYxCode(){
		return yxCode;
	}
	public void setYxCode(String yxCode){
		this.yxCode = yxCode;
	}
	
}