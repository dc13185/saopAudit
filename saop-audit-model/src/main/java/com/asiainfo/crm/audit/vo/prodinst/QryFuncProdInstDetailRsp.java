package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author admin
 */
public class QryFuncProdInstDetailRsp implements Serializable{

	private static final long serialVersionUID = -4301363205883932553L;

	private ProdInstVoExtend funcProdInstDetail;

	private List<ScopeInfo> scopeInfos;

	public ProdInstVoExtend getFuncProdInstDetail(){
		return funcProdInstDetail;
	}

	public void setFuncProdInstDetail(ProdInstVoExtend funcProdInstDetail){
		this.funcProdInstDetail = funcProdInstDetail;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

}
