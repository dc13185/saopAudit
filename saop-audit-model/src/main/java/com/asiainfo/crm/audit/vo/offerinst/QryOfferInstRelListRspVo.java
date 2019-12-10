package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferInstRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<OfferInstRelVoExtend> offerInstRels;

	public List<OfferInstRelVoExtend> getOfferInstRels(){
		return offerInstRels;
	}

	public void setOfferInstRels(List<OfferInstRelVoExtend> offerInstRels){
		this.offerInstRels = offerInstRels;
	}

}
