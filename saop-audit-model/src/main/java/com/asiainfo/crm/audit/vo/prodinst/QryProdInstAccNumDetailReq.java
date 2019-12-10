package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryProdInstAccNumDetailReq extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private Long prodInstAccNumId;

	private List<ScopeInfo> scopeInfos;

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

	public Long getProdInstAccNumId(){
		return prodInstAccNumId;
	}

	public void setProdInstAccNumId(Long prodInstAccNumId){
		this.prodInstAccNumId = prodInstAccNumId;
	}

}
