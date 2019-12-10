package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author admin
 */
public class QryAccProdInstDetailRsp implements Serializable{

	private static final long serialVersionUID = -5212894446533530061L;

	private ProdInstVoExtend prodInstDetail;

	private List<ScopeInfo> scopeInfos;

	public ProdInstVoExtend getProdInstDetail(){
		return prodInstDetail;
	}

	public void setProdInstDetail(ProdInstVoExtend prodInstDetail){
		this.prodInstDetail = prodInstDetail;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

}
