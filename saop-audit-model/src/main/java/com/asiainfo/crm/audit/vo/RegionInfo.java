package com.asiainfo.crm.audit.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fortune
 */
public class RegionInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String commonRegionId;
	private String parRegionId;
	private String regionName;
	private String regionPyName;
	private String regionNbr;
	private String regionType;
	private String regionDesc;
	private String regionLevel;
	private String regionSort;
	private String proviceNbr;
	private String cityFlag;
	private String statusCd;
	private String createStaff;
	private String updateStaff;
	private Date createDate;

	private Date statusDate;
	private Date updateDate;
	private String remark;

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getStatusDate(){
		return statusDate;
	}

	public void setStatusDate(Date statusDate){
		this.statusDate = statusDate;
	}

	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String getCommonRegionId(){
		return commonRegionId;
	}

	public void setCommonRegionId(String commonRegionId){
		this.commonRegionId = commonRegionId;
	}

	public String getParRegionId(){
		return parRegionId;
	}

	public void setParRegionId(String parRegionId){
		this.parRegionId = parRegionId;
	}

	public String getRegionName(){
		return regionName;
	}

	public void setRegionName(String regionName){
		this.regionName = regionName;
	}

	public String getRegionPyName(){
		return regionPyName;
	}

	public void setRegionPyName(String regionPyName){
		this.regionPyName = regionPyName;
	}

	public String getRegionNbr(){
		return regionNbr;
	}

	public void setRegionNbr(String regionNbr){
		this.regionNbr = regionNbr;
	}

	public String getRegionType(){
		return regionType;
	}

	public void setRegionType(String regionType){
		this.regionType = regionType;
	}

	public String getRegionDesc(){
		return regionDesc;
	}

	public void setRegionDesc(String regionDesc){
		this.regionDesc = regionDesc;
	}

	public String getRegionLevel(){
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel){
		this.regionLevel = regionLevel;
	}

	public String getRegionSort(){
		return regionSort;
	}

	public void setRegionSort(String regionSort){
		this.regionSort = regionSort;
	}

	public String getProviceNbr(){
		return proviceNbr;
	}

	public void setProviceNbr(String proviceNbr){
		this.proviceNbr = proviceNbr;
	}

	public String getCityFlag(){
		return cityFlag;
	}

	public void setCityFlag(String cityFlag){
		this.cityFlag = cityFlag;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public String getCreateStaff(){
		return createStaff;
	}

	public void setCreateStaff(String createStaff){
		this.createStaff = createStaff;
	}

	public String getUpdateStaff(){
		return updateStaff;
	}

	public void setUpdateStaff(String updateStaff){
		this.updateStaff = updateStaff;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
