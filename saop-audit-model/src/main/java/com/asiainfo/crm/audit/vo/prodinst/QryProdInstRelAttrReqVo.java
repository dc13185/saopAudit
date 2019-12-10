package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstRelAttrReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private Long prodInstRelId;

	public Long getProdInstRelId(){
		return prodInstRelId;
	}

	public void setProdInstRelId(Long prodInstRelId){
		this.prodInstRelId = prodInstRelId;
	}

}
