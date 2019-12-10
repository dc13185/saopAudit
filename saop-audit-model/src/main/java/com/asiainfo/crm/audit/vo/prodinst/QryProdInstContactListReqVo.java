package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryProdInstContactListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long prodInstContactId;

	public Long getProdInstContactId(){
		return prodInstContactId;
	}

	public void setProdInstContactId(Long prodInstContactId){
		this.prodInstContactId = prodInstContactId;
	}

}
