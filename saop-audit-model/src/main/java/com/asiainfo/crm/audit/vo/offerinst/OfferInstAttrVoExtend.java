package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.audit.model.offerinst.OfferInstAttr;

/**
 * 
 * @author fortune
 *
 */
public class OfferInstAttrVoExtend   extends OfferInstAttr{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String attrName;

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
}
