package com.asiainfo.crm.audit.model.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 * 
 * @author fortune
 *
 */
public class OfferProdInstRelList  extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录销售品实例列表
	 */
	private List<OfferProdInstRel> offerProdInstRels;

	
    public List<OfferProdInstRel> getOfferProdInstRels(){
    	return offerProdInstRels;
    }

	
    public void setOfferProdInstRels(List<OfferProdInstRel> offerProdInstRels){
    	this.offerProdInstRels = offerProdInstRels;
    }
	
	

}
