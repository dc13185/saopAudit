package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferProdInstRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferProdInstRelListRsp extends BasePageInfoVo{

	private static final long serialVersionUID = 1188872878848598039L;

	private List<OfferProdInstRel> offerProdInstRels;
	
	private List<OfferProdInstRelVoExtend> offerProdInstRelVos;

	public List<OfferProdInstRel> getOfferProdInstRels() {
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRel> offerProdInstRels) {
		this.offerProdInstRels = offerProdInstRels;
	}

	public List<OfferProdInstRelVoExtend> getOfferProdInstRelVos() {
		return offerProdInstRelVos;
	}

	public void setOfferProdInstRelVos(List<OfferProdInstRelVoExtend> offerProdInstRelVos) {
		this.offerProdInstRelVos = offerProdInstRelVos;
	}
	
}
