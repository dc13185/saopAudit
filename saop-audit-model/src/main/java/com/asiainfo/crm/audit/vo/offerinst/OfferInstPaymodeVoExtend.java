package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.audit.model.offerinst.OfferInstPaymode;

/**
 * @author admin
 */
public class OfferInstPaymodeVoExtend extends OfferInstPaymode{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1310235928683108591L;
	
	private String paymentModeName;

	public String getPaymentModeName(){
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName){
		this.paymentModeName = paymentModeName;
	}

}
