package com.asiainfo.crm.blueprint.model;

/**
 * @author chenchao
 * 
 */
public class UimResourceReqVo {
	
	private Long areaId;
	
	private Long channelId;
	
	private String phoneNumberId;

	private String virtualCardFlag;
	
	private String mktResCode;
	
	private Long terminalDevSpec;

	private RouteParam routeParam;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public String getVirtualCardFlag() {
		return virtualCardFlag;
	}

	public void setVirtualCardFlag(String virtualCardFlag) {
		this.virtualCardFlag = virtualCardFlag;
	}

	public String getMktResCode() {
		return mktResCode;
	}

	public void setMktResCode(String mktResCode) {
		this.mktResCode = mktResCode;
	}

	public RouteParam getRouteParam() {
		return routeParam;
	}

	public void setRouteParam(RouteParam routeParam) {
		this.routeParam = routeParam;
	}

	public Long getTerminalDevSpec() {
		return terminalDevSpec;
	}

	public void setTerminalDevSpec(Long terminalDevSpec) {
		this.terminalDevSpec = terminalDevSpec;
	}
}

