package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAcctRelListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;
	private Long prodInstAcctRelId;
	private Long prodInstId;

	public Long getProdInstAcctRelId(){
		return prodInstAcctRelId;
	}

	public void setProdInstAcctRelId(Long prodInstAcctRelId){
		this.prodInstAcctRelId = prodInstAcctRelId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

}
