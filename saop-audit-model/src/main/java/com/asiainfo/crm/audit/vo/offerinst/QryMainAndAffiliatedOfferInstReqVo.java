package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryMainAndAffiliatedOfferInstReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private Long prodInstId;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

}
