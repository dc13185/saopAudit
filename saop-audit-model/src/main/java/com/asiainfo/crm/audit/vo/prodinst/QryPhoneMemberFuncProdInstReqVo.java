package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryPhoneMemberFuncProdInstReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;
	private Long acctId;
	private Long funcProdId;

	public Long getAcctId(){
		return acctId;
	}

	public void setAcctId(Long acctId){
		this.acctId = acctId;
	}

	public Long getFuncProdId(){
		return funcProdId;
	}

	public void setFuncProdId(Long funcProdId){
		this.funcProdId = funcProdId;
	}

}
