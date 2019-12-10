package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInst;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferInstListAndCustIdRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private List<OfferInst> offerInsts;

	private Long ownerCustId;

	public List<OfferInst> getOfferInsts(){
		return offerInsts;
	}

	public void setOfferInsts(List<OfferInst> offerInsts){
		this.offerInsts = offerInsts;
	}

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
	}

}
