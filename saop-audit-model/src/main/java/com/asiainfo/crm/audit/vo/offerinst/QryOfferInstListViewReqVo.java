package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class QryOfferInstListViewReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private String offerInstId;

	private String custId;
	
	private String custTokenId;
	
	private String accNum;

	public String getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(String offerInstId){
		this.offerInstId = offerInstId;
	}

	public String getCustId(){
		return custId;
	}

	public void setCustId(String custId){
		this.custId = custId;
	}

	public String getCustTokenId() {
		return custTokenId;
	}

	public void setCustTokenId(String custTokenId) {
		this.custTokenId = custTokenId;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

}
