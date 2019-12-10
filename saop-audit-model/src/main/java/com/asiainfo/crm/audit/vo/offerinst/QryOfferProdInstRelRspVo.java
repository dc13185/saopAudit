package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryOfferProdInstRelRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -8390788590489169670L;

	private List<OfferProdInstRelVoExtend> offerProdInstRels;

	public List<OfferProdInstRelVoExtend> getOfferProdInstRels(){
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRelVoExtend> offerProdInstRels){
		this.offerProdInstRels = offerProdInstRels;
	}

}
