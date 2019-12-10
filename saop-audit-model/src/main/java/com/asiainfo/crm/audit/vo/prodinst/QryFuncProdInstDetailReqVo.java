package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;
/**
 * 查询功能类产品实例详情 入参，可指定回参范围.
 * @author admin
 *
 */
public class QryFuncProdInstDetailReqVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long prodInstId;
	private List<ScopeInfo> scopeInfos ;
	public Long getProdInstId() {
		return prodInstId;
	}
	public void setProdInstId(Long prodInstId) {
		this.prodInstId = prodInstId;
	}
	public List<ScopeInfo> getScopeInfos() {
		return scopeInfos;
	}
	public void setScopeInfos(List<ScopeInfo> scopeInfos) {
		this.scopeInfos = scopeInfos;
	}
	
}
