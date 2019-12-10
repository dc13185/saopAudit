package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;

/**
 * @author admin
 */
public class QryOfferInstAloneRspVo implements Serializable{

	private static final long serialVersionUID = 4589782696036475517L;

	private OfferInstVoExtend offerInst;

	public OfferInstVoExtend getOfferInst(){
		return offerInst;
	}

	public void setOfferInst(OfferInstVoExtend offerInst){
		this.offerInst = offerInst;
	}

}
