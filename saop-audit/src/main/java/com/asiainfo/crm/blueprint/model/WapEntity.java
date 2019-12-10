package com.asiainfo.crm.blueprint.model;

import java.io.Serializable;
import java.util.Date;

public class WapEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7591451706961173352L;

	private String mdn;
	private String imsi;
	private String state;
	private String payType;
	private String provinceCode;
	private String checkFlag;
	private Date createTime;
	private Date updateTime;
	private String chkBatch;
	private Long prodId;
	private Integer prodAreaId;
	private Integer prodSpecId;
	private Integer prodStatusCd;
	private String cityCode;
	private Date prodStartDate;
	private Date prodEndDate;
	private String gimsi;
	private String lteimsi;
	private String remark;
	private String orderTypeCd;

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getChkBatch() {
		return chkBatch;
	}

	public void setChkBatch(String chkBatch) {
		this.chkBatch = chkBatch;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Integer getProdAreaId() {
		return prodAreaId;
	}

	public void setProdAreaId(Integer prodAreaId) {
		this.prodAreaId = prodAreaId;
	}

	public Integer getProdSpecId() {
		return prodSpecId;
	}

	public void setProdSpecId(Integer prodSpecId) {
		this.prodSpecId = prodSpecId;
	}

	public Integer getProdStatusCd() {
		return prodStatusCd;
	}

	public void setProdStatusCd(Integer prodStatusCd) {
		this.prodStatusCd = prodStatusCd;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Date getProdStartDate() {
		return prodStartDate;
	}

	public void setProdStartDate(Date prodStartDate) {
		this.prodStartDate = prodStartDate;
	}

	public Date getProdEndDate() {
		return prodEndDate;
	}

	public void setProdEndDate(Date prodEndDate) {
		this.prodEndDate = prodEndDate;
	}

	public String getGimsi() {
		return gimsi;
	}

	public void setGimsi(String gimsi) {
		this.gimsi = gimsi;
	}


	public String getLteimsi() {
		return lteimsi;
	}

	public void setLteimsi(String lteimsi) {
		this.lteimsi = lteimsi;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderTypeCd() {
		return orderTypeCd;
	}

	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}

	@Override
	public String toString() {
		return "WapEntity [mdn=" + mdn + ", imsi=" + imsi + ", payType="
				+ payType + ", prodStatusCd=" + prodStatusCd + ", gimsi="
				+ gimsi + ", lteimsi=" + lteimsi + ", orderTypeCd="
				+ orderTypeCd + "]";
	}

	
}
