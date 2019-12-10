package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInst;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferInstListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<OfferInst> offerInsts;

	public List<OfferInst> getOfferInsts(){
		return offerInsts;
	}

	public void setOfferInsts(List<OfferInst> offerInsts){
		this.offerInsts = offerInsts;
	}
}
