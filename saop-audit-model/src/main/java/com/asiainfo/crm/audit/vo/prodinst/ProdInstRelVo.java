package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import java.util.Date;
/**
 *@create
 */
public class ProdInstRelVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录产品实例关系标识，主键。 
	 */
	   private Long prodInstRelId;
	 /**
	 * 记录A端产品实例标识，外键。 
	 */
	   private Long aProdInstId;
	 /**
	 * 记录Z端产品实例标识，外键。 
	 */
	   private Long zProdInstId;
	 /**
	 * 记录产品实例的关系类型。LOVB=PRI-0003 
	 */
	   private String relType;
	 /**
	 * 记录角色标识。 
	 */
	   private Long roleId;
	 /**
	 * 记录关系的生效时间。 
	 */
	   private Date effDate;
	 /**
	 * 记录关系的失效时间。 
	 */
	   private Date expDate;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	 /**
	 * A端区域标识 
	 */
	   private Long regionId;
	 /**
	 * Z端区域标识 
	 */
	   private Long zRegionId;
	
	public Long getProdInstRelId(){
		return prodInstRelId;
	}
	public void setProdInstRelId(Long prodInstRelId){
		this.prodInstRelId = prodInstRelId;
	}
	public Long getaProdInstId(){
		return aProdInstId;
	}
	public void setaProdInstId(Long aProdInstId){
		this.aProdInstId = aProdInstId;
	}
	public Long getzProdInstId(){
		return zProdInstId;
	}
	public void setzProdInstId(Long zProdInstId){
		this.zProdInstId = zProdInstId;
	}
	public String getRelType(){
		return relType;
	}
	public void setRelType(String relType){
		this.relType = relType;
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
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	public Long getzRegionId(){
		return zRegionId;
	}
	public void setzRegionId(Long zRegionId){
		this.zRegionId = zRegionId;
	}
	
}