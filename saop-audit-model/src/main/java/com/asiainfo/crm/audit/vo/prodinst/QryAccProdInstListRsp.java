package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccProdInstListRsp extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;
	private List<ProdInstVoExtend> accProdInsts;
	private List<ScopeInfo> scopeInfos;

	public List<ProdInstVoExtend> getAccProdInsts(){
		return accProdInsts;
	}

	public void setAccProdInsts(List<ProdInstVoExtend> accProdInsts){
		this.accProdInsts = accProdInsts;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

}
