package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author admin
 */
public class QryFuncProdInstDetailReq implements Serializable{

	private static final long serialVersionUID = -4301363205883932553L;

	private String isDetail;

	private Long prodInstId;

	private List<ScopeInfo> scopeInfos;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

	public String getIsDetail(){
		return isDetail;
	}

	public void setIsDetail(String isDetail){
		this.isDetail = isDetail;
	}

}
