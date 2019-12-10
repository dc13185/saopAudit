package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInstFeeInfo;

/**
 * @author admin
 */
public class OfferInstFeeInfoVoExtend extends OfferInstFeeInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7998198995740387057L;
	
	List<OfferInstFeeAttrVo> offerInstFeeAttrs;

	public List<OfferInstFeeAttrVo> getOfferInstFeeAttrs(){
		return offerInstFeeAttrs;
	}

	public void setOfferInstFeeAttrs(List<OfferInstFeeAttrVo> offerInstFeeAttrs){
		this.offerInstFeeAttrs = offerInstFeeAttrs;
	}

}
