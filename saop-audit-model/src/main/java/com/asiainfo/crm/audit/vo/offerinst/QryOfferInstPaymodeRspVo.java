package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;

/**
 * @author admin
 */
public class QryOfferInstPaymodeRspVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private OfferInstPaymodeVoExtend offerInstPaymode;

	public OfferInstPaymodeVoExtend getOfferInstPaymode(){
		return offerInstPaymode;
	}

	public void setOfferInstPaymode(OfferInstPaymodeVoExtend offerInstPaymode){
		this.offerInstPaymode = offerInstPaymode;
	}

}
