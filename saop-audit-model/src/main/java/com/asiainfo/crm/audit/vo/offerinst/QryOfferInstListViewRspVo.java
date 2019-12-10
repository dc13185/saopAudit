package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class QryOfferInstListViewRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private  List<OfferInstVoExtend>  offerInsts;

	public List<OfferInstVoExtend> getOfferInsts() {
		return offerInsts;
	}

	public void setOfferInsts(List<OfferInstVoExtend> offerInsts) {
		this.offerInsts = offerInsts;
	}

}
