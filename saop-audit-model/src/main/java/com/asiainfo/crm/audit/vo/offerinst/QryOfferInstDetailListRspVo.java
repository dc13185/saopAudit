package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author Administrator
 */
public class QryOfferInstDetailListRspVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private OfferInstVoExtend offerInstDetail;

	private List<ScopeInfo> scopeInfos;

	public OfferInstVoExtend getOfferInstDetail(){
		return offerInstDetail;
	}

	public void setOfferInstDetail(OfferInstVoExtend offerInstDetail){
		this.offerInstDetail = offerInstDetail;
	}

	public List<ScopeInfo> getScopeInfos() {
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos) {
		this.scopeInfos = scopeInfos;
	}
}
