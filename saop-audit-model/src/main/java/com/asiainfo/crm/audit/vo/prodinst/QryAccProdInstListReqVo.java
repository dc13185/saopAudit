package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccProdInstListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long useCustId;

	private Long ownerCustId;

	public Long getUseCustId(){
		return useCustId;
	}

	public void setUseCustId(Long useCustId){
		this.useCustId = useCustId;
	}

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
	}

}
