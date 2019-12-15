package com.asiainfo.crm.order.domain;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class MessageOrderEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String transactionId;
	private String serviceInstId;
	private String lanId;
	private String businessFlag;
	private String extCustOrderId;
	private String isale;
	private String olId;
	private String processState;
	private String processResult;
	private Date createTime;
	private Date version;
	private String msgStrPltfrm;
	private Date partitionFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getServiceInstId() {
		return serviceInstId;
	}

	public void setServiceInstId(String serviceInstId) {
		this.serviceInstId = serviceInstId;
	}

	public String getLanId() {
		return lanId;
	}

	public void setLanId(String lanId) {
		this.lanId = lanId;
	}

	public String getBusinessFlag() {
		return businessFlag;
	}

	public void setBusinessFlag(String businessFlag) {
		this.businessFlag = businessFlag;
	}

	public String getExtCustOrderId() {
		return extCustOrderId;
	}

	public void setExtCustOrderId(String extCustOrderId) {
		this.extCustOrderId = extCustOrderId;
	}

	public String getIsale() {
		return isale;
	}

	public void setIsale(String isale) {
		this.isale = isale;
	}

	public String getOlId() {
		return olId;
	}

	public void setOlId(String olId) {
		this.olId = olId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public String getMsgStrPltfrm() {
		return msgStrPltfrm;
	}

	public void setMsgStrPltfrm(String msgStrPltfrm) {
		this.msgStrPltfrm = msgStrPltfrm;
	}

	public Date getPartitionFlag() {
		return partitionFlag;
	}

	public void setPartitionFlag(Date partitionFlag) {
		this.partitionFlag = partitionFlag;
	}
}
