package com.asiainfo.crm.audit.model.prodinst;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 *
 */
public class ProdInstAccNum extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录号码实例标识，主键。
	 */
	private Long prodInstAccNumId;

	/**
	 * 记录产品实例标识，外键。
	 */
	private Long prodInstId;

	/**
	 * 记录接入号的类型。LOVB=PRI-C-0006
	 */
	private String accNumType;

	/**
	 * 记录接入号码。
	 */
	private String accNum;

	/**
	 * 记录有产品同平台有关系的平台标识。
	 */
	private Long platId;

	/**
	 * 记录密码。
	 */
	private String password;

	/**
	 * 记录号码的适用区域范围。
	 */
	private Long applyRegionId;

	/**
	 * 记录上一次维护记录的订单项标识。
	 */
	private Long lastOrderItemId;

	/**
	 * 接入号资源端类型
	 */
	private Integer anTypeCd;

	/**
	 * 接入号码标识
	 */
	private Long anId;

	/**
	 * 区域标识
	 */
	private Long regionId;

	public Long getProdInstAccNumId(){
		return prodInstAccNumId;
	}

	public void setProdInstAccNumId(Long prodInstAccNumId){
		this.prodInstAccNumId = prodInstAccNumId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getAccNumType(){
		return accNumType;
	}

	public void setAccNumType(String accNumType){
		this.accNumType = accNumType;
	}

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public Long getPlatId(){
		return platId;
	}

	public void setPlatId(Long platId){
		this.platId = platId;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public Long getApplyRegionId(){
		return applyRegionId;
	}

	public void setApplyRegionId(Long applyRegionId){
		this.applyRegionId = applyRegionId;
	}

	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}

	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}

	public Integer getAnTypeCd(){
		return anTypeCd;
	}

	public void setAnTypeCd(Integer anTypeCd){
		this.anTypeCd = anTypeCd;
	}

	public Long getAnId(){
		return anId;
	}

	public void setAnId(Long anId){
		this.anId = anId;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

}
