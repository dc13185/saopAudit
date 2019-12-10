package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryProdInstStateListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long prodInstId;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

}
