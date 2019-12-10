package com.asiainfo.crm.blueprint.model;

import java.io.Serializable;

/**
 * @author chenchao
 */
public class UIMResourceRespVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1156889271845478121L;
	private String anTypeCd;
	private String deviceName;
	private String manuName;
	private String relaTerminalDevId;
	private String mktResId;
	private Long mktResInstId;
	private String terminalDevSpecId;
	private Long handleResultCode;
	private Long isFourK;

	public Long getMktResInstId(){
		return mktResInstId;
	}

	public void setMktResInstId(Long mktResInstId){
		this.mktResInstId = mktResInstId;
	}

	public String getAnTypeCd(){
		return anTypeCd;
	}

	public void setAnTypeCd(String anTypeCd){
		this.anTypeCd = anTypeCd;
	}

	public String getDeviceName(){
		return deviceName;
	}

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getManuName(){
		return manuName;
	}

	public void setManuName(String manuName){
		this.manuName = manuName;
	}

	public String getRelaTerminalDevId(){
		return relaTerminalDevId;
	}

	public void setRelaTerminalDevId(String relaTerminalDevId){
		this.relaTerminalDevId = relaTerminalDevId;
	}

	public String getMktResId(){
		return mktResId;
	}

	public void setMktResId(String mktResId){
		this.mktResId = mktResId;
	}

	public String getTerminalDevSpecId(){
		return terminalDevSpecId;
	}

	public void setTerminalDevSpecId(String terminalDevSpecId){
		this.terminalDevSpecId = terminalDevSpecId;
	}

	public Long getHandleResultCode(){
		return handleResultCode;
	}

	public void setHandleResultCode(Long handleResultCode){
		this.handleResultCode = handleResultCode;
	}

	public Long getIsFourK(){
		return isFourK;
	}

	public void setIsFourK(Long isFourK){
		this.isFourK = isFourK;
	}
}
