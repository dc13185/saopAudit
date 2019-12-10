package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInstRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferInstRelListVo extends BasePageInfoVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<OfferInstRel> offerInstRels;
	
	private List<OfferInstRelVoExtend> offerInstRelVos;

	public List<OfferInstRel> getOfferInstRels(){
		return offerInstRels;
	}

	public void setOfferInstRels(List<OfferInstRel> offerInstRels){
		this.offerInstRels = offerInstRels;
	}

	public List<OfferInstRelVoExtend> getOfferInstRelVos() {
		return offerInstRelVos;
	}

	public void setOfferInstRelVos(List<OfferInstRelVoExtend> offerInstRelVos) {
		this.offerInstRelVos = offerInstRelVos;
	}

}
