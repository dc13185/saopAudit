package com.asiainfo.crm.blueprint.model;

public class AuditInputEntity {
	//集团下发报文或者是省分上传集团报文
	private String downXml;
	//报竣报文
	private String reportXml;
	//业务编码
	private String buscode;
	//服务编码
	private String svcCode;

	private String createTime;
	
	private String auditOriginDataId;
	
	public String getDownXml() {
		return downXml;
	}
	public void setDownXml(String downXml) {
		this.downXml = downXml;
	}
	public String getReportXml() {
		return reportXml;
	}
	public void setReportXml(String reportXml) {
		this.reportXml = reportXml;
	}
	public String getBuscode() {
		return buscode;
	}
	public void setBuscode(String buscode) {
		this.buscode = buscode;
	}
	public String getSvcCode() {
		return svcCode;
	}
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAuditOriginDataId() {
		return auditOriginDataId;
	}
	public void setAuditOriginDataId(String auditOriginDataId) {
		this.auditOriginDataId = auditOriginDataId;
	}
}
