package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;
import java.util.Date;

/**
 * 新类.
 * 
 * @author fortune
 */
public class MainOfferInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录销售品产品实例关系标识，主键。.
	 */
	private Long offerProdInstRelId;
	/**
	 * 记录销售品实例标识，外键.
	 */
	private Long offerInstId;
	/**
	 * 记录产品实例标识，外键.
	 */
	private Long prodInstId;
	/**
	 * 记录销售品产品的使用、构成关系等。LOVB=PRI-C-0022.
	 */
	private String relType;

	private String offerType;
	/**
	 * 记录角色标识.
	 */
	private Long roleId;

	private String roleName;

	/**
	 * 销售品产品关系ID.
	 */
	private Long offerProdRelId;
	/**
	 * 记录关联产品实例的业务分类.
	 */
	private String prodFuncType;
	/**
	 * 地区标识.
	 */
	private Long regionId;
	/**
	 * 产品标识.
	 */
	private Long prodId;
	/**
	 * 接入产品实例标识.
	 */
	private Long accProdInstId;

	/**
	 * 销售品ID.
	 */
	private Long offerId;
	/**
	 * 销售品名称.
	 */
	private String offerName;

	/**
	 * 记录销售品实例具体的生效时间，不因销售品信息变更而改变.
	 */
	private Date effDate;

	/**
	 * 记录销售品实例具体的失效时间，指同客户约定的协议失效时间.
	 */
	private Date expDate;

	public Long getOfferProdInstRelId(){
		return offerProdInstRelId;
	}

	public void setOfferProdInstRelId(Long offerProdInstRelId){
		this.offerProdInstRelId = offerProdInstRelId;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
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

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public Long getOfferProdRelId(){
		return offerProdRelId;
	}

	public void setOfferProdRelId(Long offerProdRelId){
		this.offerProdRelId = offerProdRelId;
	}

	public String getProdFuncType(){
		return prodFuncType;
	}

	public void setProdFuncType(String prodFuncType){
		this.prodFuncType = prodFuncType;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public Long getAccProdInstId(){
		return accProdInstId;
	}

	public void setAccProdInstId(Long accProdInstId){
		this.accProdInstId = accProdInstId;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

	public String getOfferName(){
		return offerName;
	}

	public void setOfferName(String offerName){
		this.offerName = offerName;
	}

	public String getOfferType(){
		return offerType;
	}

	public void setOfferType(String offerType){
		this.offerType = offerType;
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

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

}
