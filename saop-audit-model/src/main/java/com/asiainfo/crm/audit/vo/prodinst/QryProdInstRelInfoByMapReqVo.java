package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstRelInfoByMapReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private Long zProdInstId;

	public Long getzProdInstId(){
		return zProdInstId;
	}

	public void setzProdInstId(Long zProdInstId){
		this.zProdInstId = zProdInstId;
	}

}
