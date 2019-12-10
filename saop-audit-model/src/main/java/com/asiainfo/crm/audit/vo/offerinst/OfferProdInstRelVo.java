package com.asiainfo.crm.audit.vo.offerinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 *
 */
public class OfferProdInstRelVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录销售品产品实例关系标识，主键。
	 */
	private Long offerProdInstRelId;
	/**
	 * 记录销售品实例标识，外键。
	 */
	private Long offerInstId;
	/**
	 * 记录产品实例标识，外键。
	 */
	private Long prodInstId;
	/**
	 * 记录销售品产品的使用、构成关系等。LOVB=PRI-C-0022
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
	 * 记录角色标识。
	 */
	private Long roleId;
	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;
	/**
	 * 销售品产品关系ID
	 */
	private Long offerProdRelId;
	/**
	 * 记录关联产品实例的业务分类
	 */
	private String prodFuncType;
	/**
	 * 产品标识
	 */
	private Long prodId;
	/**
	 * 接入产品实例标识
	 */
	private Long accProdInstId;
	/**
	 * "产品使用类型： 主：代表产品只能作为主产品存在； 附：代表产品只能作为附属产品存在；
	 * 主附并存：代表产品既可作为主产品又可以作为附属产品存在。LOVB=PRD-C-0002"
	 */
	private String prodUseType;
	/**
	 * 销售品实例所在地域中的公共管理区域标识
	 */
	private Long regionId;
	/**
	 * 产品实例所在地域中的公共管理区域标识
	 */
	private Long piRegionId;

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

	public Long getRoleId(){
		return roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}

	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
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

	public String getProdUseType(){
		return prodUseType;
	}

	public void setProdUseType(String prodUseType){
		this.prodUseType = prodUseType;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

	public Long getPiRegionId(){
		return piRegionId;
	}

	public void setPiRegionId(Long piRegionId){
		this.piRegionId = piRegionId;
	}

}
