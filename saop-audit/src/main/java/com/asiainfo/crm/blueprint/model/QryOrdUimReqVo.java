package com.asiainfo.crm.blueprint.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenchao on 2017/11/13.
 */
public class QryOrdUimReqVo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer applyObjSpec;
	private List<Long> serviceOfferIds;
	private String mktResType;
	private String startDate;
	private String endDate;
	
	public Integer getApplyObjSpec() {
		return applyObjSpec;
	}
	public void setApplyObjSpec(Integer applyObjSpec) {
		this.applyObjSpec = applyObjSpec;
	}
	public String getMktResType() {
		return mktResType;
	}
	public void setMktResType(String mktResType) {
		this.mktResType = mktResType;
	}
	public List<Long> getServiceOfferIds() {
		return serviceOfferIds;
	}
	public void setServiceOfferIds(List<Long> serviceOfferIds) {
		this.serviceOfferIds = serviceOfferIds;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}
