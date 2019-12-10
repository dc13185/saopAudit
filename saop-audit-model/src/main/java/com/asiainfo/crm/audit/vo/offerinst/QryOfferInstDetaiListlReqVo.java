package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferInstDetaiListlReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private String offerInstId;

	private String extOfferInstId;

	private String isDetail;

	private List<ScopeInfo> scopeInfos;

	public String getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(String offerInstId){
		this.offerInstId = offerInstId;
	}

	public String getExtOfferInstId() {
		return extOfferInstId;
	}

	public void setExtOfferInstId(String extOfferInstId) {
		this.extOfferInstId = extOfferInstId;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

}
