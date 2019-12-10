package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInstAttr;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryOfferInstAttrListRspVo extends BasePageInfoVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<OfferInstAttr> offerInstAttrs;


	public List<OfferInstAttr> getOfferInstAttrs() {
		return offerInstAttrs;
	}


	public void setOfferInstAttrs(List<OfferInstAttr> offerInstAttrs) {
		this.offerInstAttrs = offerInstAttrs;
	}
	
	

}
