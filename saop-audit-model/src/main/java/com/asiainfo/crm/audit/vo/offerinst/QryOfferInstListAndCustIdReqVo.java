package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferInstListAndCustIdReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private Long accProdInstId;

	public Long getAccProdInstId(){
		return accProdInstId;
	}

	public void setAccProdInstId(Long accProdInstId){
		this.accProdInstId = accProdInstId;
	}

}
