package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryProdInstPaymodeListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long paymodeId;

	private Long prodInstId;

	public Long getPaymodeId(){
		return paymodeId;
	}

	public void setPaymodeId(Long paymodeId){
		this.paymodeId = paymodeId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

}
