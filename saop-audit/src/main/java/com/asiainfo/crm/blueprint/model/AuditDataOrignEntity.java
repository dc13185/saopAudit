package com.asiainfo.crm.blueprint.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 稽核数据来源核心配置表
 * 
 * @author yong
 * 
 */
public class AuditDataOrignEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5785231548982174525L;
	private String auditDataOriginId;
	private String saopLogId;
	private String saopSrvcInstId;
	private String saopPrimaryLogId;
	private String processState;
	private String processResult;
	private String createTime;
	private Date version;


	public String getAuditDataOriginId() {
		return auditDataOriginId;
	}

	public void setAuditDataOriginId(String auditDataOriginId) {
		this.auditDataOriginId = auditDataOriginId;
	}

	public String getSaopLogId() {
		return saopLogId;
	}

	public void setSaopLogId(String saopLogId) {
		this.saopLogId = saopLogId;
	}

	public String getSaopSrvcInstId() {
		return saopSrvcInstId;
	}

	public void setSaopSrvcInstId(String saopSrvcInstId) {
		this.saopSrvcInstId = saopSrvcInstId;
	}

	public String getSaopPrimaryLogId() {
		return saopPrimaryLogId;
	}

	public void setSaopPrimaryLogId(String saopPrimaryLogId) {
		this.saopPrimaryLogId = saopPrimaryLogId;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "AuditDataOrignEntity [saopLogId=" + saopLogId
				+ ", saopSrvcInstId=" + saopSrvcInstId + ", saopPrimaryLogId="
				+ saopPrimaryLogId + ", processState=" + processState + "]";
	}
	
}
