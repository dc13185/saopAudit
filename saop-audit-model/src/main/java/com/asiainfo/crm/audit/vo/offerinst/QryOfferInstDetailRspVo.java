package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;

/**
 * @author fortune
 */
public class QryOfferInstDetailRspVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private OfferInstVoExtend offerInstDetail;

	public OfferInstVoExtend getOfferInstDetail(){
		return offerInstDetail;
	}

	public void setOfferInstDetail(OfferInstVoExtend offerInstDetail){
		this.offerInstDetail = offerInstDetail;
	}

}
