package com.asiainfo.crm.ftp.model;

import java.io.Serializable;
import java.util.Date;

public class CepFtpUploadLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 899770035814866422L;

	private String busCode;
	private String fileDate;
	private String reSendTime;
	private String seqNum;
	private String fileName;
	private String createState;
	private String createDesc;
	private String sendState;
	private String sendDesc;
	private Date version;
	private String platCode;

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getReSendTime() {
		return reSendTime;
	}

	public void setReSendTime(String reSendTime) {
		this.reSendTime = reSendTime;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreateState() {
		return createState;
	}

	public void setCreateState(String createState) {
		this.createState = createState;
	}

	public String getCreateDesc() {
		return createDesc;
	}

	public void setCreateDesc(String createDesc) {
		this.createDesc = createDesc;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public String getSendDesc() {
		return sendDesc;
	}

	public void setSendDesc(String sendDesc) {
		this.sendDesc = sendDesc;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "CepFtpUploadLogEntity [busCode=" + busCode + ", fileDate="
				+ fileDate + ", reSendTime=" + reSendTime + ", seqNum="
				+ seqNum + ", fileName=" + fileName + ", createState="
				+ createState + ", createDesc=" + createDesc + ", sendState="
				+ sendState + ", sendDesc=" + sendDesc + ", version=" + version
				+ "]";
	}

	public String getPlatCode() {
		return platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}


}
