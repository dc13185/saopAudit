package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryMainAndAffiliatedOfferInstRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<OfferInstVoExtend> mainOfferInsts;

	private List<OfferInstVoExtend> affiliatedOfferInsts;

	public List<OfferInstVoExtend> getMainOfferInsts(){
		return mainOfferInsts;
	}

	public void setMainOfferInsts(List<OfferInstVoExtend> mainOfferInsts){
		this.mainOfferInsts = mainOfferInsts;
	}

	public List<OfferInstVoExtend> getAffiliatedOfferInsts(){
		return affiliatedOfferInsts;
	}

	public void setAffiliatedOfferInsts(List<OfferInstVoExtend> affiliatedOfferInsts){
		this.affiliatedOfferInsts = affiliatedOfferInsts;
	}

}
