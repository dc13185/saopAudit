package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAccNumListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long prodInstAcctNumId;

	private Long prodInstId;

	private String accNumType;

	public Long getProdInstAcctNumId(){
		return prodInstAcctNumId;
	}

	public void setProdInstAcctNumId(Long prodInstAcctNumId){
		this.prodInstAcctNumId = prodInstAcctNumId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getAccNumType(){
		return accNumType;
	}

	public void setAccNumType(String accNumType){
		this.accNumType = accNumType;
	}

}
