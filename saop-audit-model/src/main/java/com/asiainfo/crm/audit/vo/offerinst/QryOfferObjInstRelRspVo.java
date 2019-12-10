package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryOfferObjInstRelRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4423133919385499028L;

	private List<OfferObjInstRelVoExtend> offerObjInstRels;

	public List<OfferObjInstRelVoExtend> getOfferObjInstRels(){
		return offerObjInstRels;
	}

	public void setOfferObjInstRels(List<OfferObjInstRelVoExtend> offerObjInstRels){
		this.offerObjInstRels = offerObjInstRels;
	}

}
