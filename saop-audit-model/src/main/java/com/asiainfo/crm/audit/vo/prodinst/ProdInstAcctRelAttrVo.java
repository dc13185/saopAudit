package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import java.util.Date;
/**
 *@create
 */
public class ProdInstAcctRelAttrVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 帐务定制关系附加属性标识 
	 */
	   private Long prodInstAcctRelAttrId;
	 /**
	 * 帐务关系标识
 
	 */
	   private Long prodInstAcctRelId;
	 /**
	 * 记录属性标识。 
	 */
	   private Long attrId;
	 /**
	 * 属性值标识 
	 */
	   private Long attrValueId;
	 /**
	 * 记录属性值。 
	 */
	   private String attrValue;
	 /**
	 * 记录生效时间 
	 */
	   private Date effDate;
	 /**
	 * 记录失效时间 
	 */
	   private Date expDate;
	 /**
	 * 记录属性的名称。 
	 */
	   private String attrName;
	 /**
	 * 区域标识 
	 */
	   private Long regionId;
	
	public Long getProdInstAcctRelAttrId(){
		return prodInstAcctRelAttrId;
	}
	public void setProdInstAcctRelAttrId(Long prodInstAcctRelAttrId){
		this.prodInstAcctRelAttrId = prodInstAcctRelAttrId;
	}
	public Long getProdInstAcctRelId(){
		return prodInstAcctRelId;
	}
	public void setProdInstAcctRelId(Long prodInstAcctRelId){
		this.prodInstAcctRelId = prodInstAcctRelId;
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
	public String getAttrName(){
		return attrName;
	}
	public void setAttrName(String attrName){
		this.attrName = attrName;
	}
	public Long getRegionId(){
		return regionId;
	}
	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}
	
}