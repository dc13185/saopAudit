package com.asiainfo.crm.audit.vo.prodinst;

import java.util.Date;

import com.asiainfo.crm.audit.model.prodinst.ProdResInstRel;

/**
 * @author wangxh
 * @ClassName: ProdResInstRelVoExtend
 * @Description:
 */
public class ProdResInstRelVoExtend extends ProdResInstRel{

	/**
	 *
	 */
	private static final long serialVersionUID = 8862575288596799363L;
	/**
     * 状态 原数据，新数据
     */
    private String statusCd;
    /**
     * 规则校验状态
     */
    private String ruleStatusCd;

    /**
     * 状态时间
     */
    private Date statusDate;
    /**
     * 创建人
     */
    private Long createStaff;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改人
     */
    private Long updateStaff;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 备注
     */
    private String remark;
    
	/**
	 * 记录营销资源类型名称
	 */
	private String mktResTypeName;
	
	
	private String deviceName;
    private Long handleResultCode;
	private String manuName;
	private String terminalDevSpecId;

	
	private String couponName;
	private String storeName;
	private Long isFourK;
	
    public String getStatusCd(){
    	return statusCd;
    }

	
    public void setStatusCd(String statusCd){
    	this.statusCd = statusCd;
    }

	
    public String getRuleStatusCd(){
    	return ruleStatusCd;
    }

	
    public void setRuleStatusCd(String ruleStatusCd){
    	this.ruleStatusCd = ruleStatusCd;
    }

	
    public Date getStatusDate(){
    	return statusDate;
    }

	
    public void setStatusDate(Date statusDate){
    	this.statusDate = statusDate;
    }

	
    public Long getCreateStaff(){
    	return createStaff;
    }

	
    public void setCreateStaff(Long createStaff){
    	this.createStaff = createStaff;
    }

	
    public Date getCreateDate(){
    	return createDate;
    }

	
    public void setCreateDate(Date createDate){
    	this.createDate = createDate;
    }

	
    public Long getUpdateStaff(){
    	return updateStaff;
    }

	
    public void setUpdateStaff(Long updateStaff){
    	this.updateStaff = updateStaff;
    }

	
    public Date getUpdateDate(){
    	return updateDate;
    }

	
    public void setUpdateDate(Date updateDate){
    	this.updateDate = updateDate;
    }

	
    public String getRemark(){
    	return remark;
    }

	
    public void setRemark(String remark){
    	this.remark = remark;
    }



	public String getMktResTypeName(){
		return mktResTypeName;
	}

	public void setMktResTypeName(String mktResTypeName){
		this.mktResTypeName = mktResTypeName;
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public Long getHandleResultCode() {
		return handleResultCode;
	}


	public void setHandleResultCode(Long handleResultCode) {
		this.handleResultCode = handleResultCode;
	}


	public String getManuName() {
		return manuName;
	}


	public void setManuName(String manuName) {
		this.manuName = manuName;
	}


	public String getTerminalDevSpecId() {
		return terminalDevSpecId;
	}


	public void setTerminalDevSpecId(String terminalDevSpecId) {
		this.terminalDevSpecId = terminalDevSpecId;
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public Long getIsFourK() {
		return isFourK;
	}


	public void setIsFourK(Long isFourK) {
		this.isFourK = isFourK;
	}
	
}
