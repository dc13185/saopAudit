package com.asiainfo.crm.audit.model.offerinst;

import java.util.Date;
import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *
 */
public class OfferInst extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录销售品实例标识，主键。
	 */
	private Long offerInstId;

	/**
	 * 记录套餐标志。 主要有：1-组合套餐，2-单一套 餐，3-基础销售品，0-品牌类销售品
	 */
	private Long offerId;

	/**
	 * 记录销售品标识归属的销售品类型，冗余存储。
	 */
	private String offerType;

	/**
	 * 记录产权客户标识。
	 */
	private Long ownerCustId;

	/**
	 * 记录销售品实例具体的生效时间，不因销售品信息变更而改变。
	 */
	private Date effDate;

	/**
	 * 记录销售品实例具体的失效时间，指同客户约定的协议失效时间。
	 */
	private Date expDate;

	/**
	 * 记录销售品协议项的标识，协议子域的外键。
	 */
	private Long offerAgreeId;

	/**
	 * 记录实例创建的组织标识。
	 */
	private Long createOrgId;

	/**
	 * 记录套餐到期是否自动续约、自动退订，也可以由10000客户确认后自动退订改自动续约。LOVB=OFF-0008
	 */
	private String expProcMethod;

	/**
	 * 记录本地网标识。
	 */
	private Long lanId;

	/**
	 * 记录销售品实例所属的区域。指向公共管理区域标识
	 */
	private Long regionId;

	/**
	 * 记录销售品信息业务变更的时间，保持档案时间的连续性，手动维护或人工信息维护不需进历史表用修改时间表达，不用修改这个时间，由客户发起的要进历史表。
	 */
	private Date busiModDate;

	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;

	/**
	 * 记录外部销售品实例标识
	 */
	private String extOfferInstId;

	/**
	 * 冗余销售品规格上的可独立订购标记，LOVB=OFF-C-0029
	 */
	private String isIndependent;

	/**
	 * 外系统映射销售品实例标识
	 */
	private Long outerOfferInstId;

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

	public String getOfferType(){
		return offerType;
	}

	public void setOfferType(String offerType){
		this.offerType = offerType;
	}

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
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

	public Long getOfferAgreeId(){
		return offerAgreeId;
	}

	public void setOfferAgreeId(Long offerAgreeId){
		this.offerAgreeId = offerAgreeId;
	}

	public Long getCreateOrgId(){
		return createOrgId;
	}

	public void setCreateOrgId(Long createOrgId){
		this.createOrgId = createOrgId;
	}

	public String getExpProcMethod(){
		return expProcMethod;
	}

	public void setExpProcMethod(String expProcMethod){
		this.expProcMethod = expProcMethod;
	}

	public Long getLanId(){
		return lanId;
	}

	public void setLanId(Long lanId){
		this.lanId = lanId;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

	public Date getBusiModDate(){
		return busiModDate;
	}

	public void setBusiModDate(Date busiModDate){
		this.busiModDate = busiModDate;
	}

	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}

	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}

	public String getExtOfferInstId(){
		return extOfferInstId;
	}

	public void setExtOfferInstId(String extOfferInstId){
		this.extOfferInstId = extOfferInstId;
	}

	public String getIsIndependent(){
		return isIndependent;
	}

	public void setIsIndependent(String isIndependent){
		this.isIndependent = isIndependent;
	}

	public Long getOuterOfferInstId(){
		return outerOfferInstId;
	}

	public void setOuterOfferInstId(Long outerOfferInstId){
		this.outerOfferInstId = outerOfferInstId;
	}

}
