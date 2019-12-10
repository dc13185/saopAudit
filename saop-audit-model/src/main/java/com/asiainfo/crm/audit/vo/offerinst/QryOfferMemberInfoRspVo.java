package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryOfferMemberInfoRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -8844677627715002559L;

	private List<OfferObjInstRelVoExtend> offerObjInstRels;

	private List<OfferProdInstRelVoExtend> offerProdInstRels;

	public List<OfferProdInstRelVoExtend> getOfferProdInstRels(){
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRelVoExtend> offerProdInstRels){
		this.offerProdInstRels = offerProdInstRels;
	}

	public List<OfferObjInstRelVoExtend> getOfferObjInstRels(){
		return offerObjInstRels;
	}

	public void setOfferObjInstRels(List<OfferObjInstRelVoExtend> offerObjInstRels){
		this.offerObjInstRels = offerObjInstRels;
	}

}
