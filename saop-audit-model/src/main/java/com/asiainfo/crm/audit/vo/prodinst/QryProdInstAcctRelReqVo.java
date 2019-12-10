package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 查询账号列表请求对象.
 * 
 * @author Administrator
 */
public class QryProdInstAcctRelReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private String apiCode;

	private Long custId;

	private String custTokenId;

	private Long prodInstId;

	private String accNum;
	
	private Long acctId;

    public String getApiCode(){
    	return apiCode;
    }
	
    public void setApiCode(String apiCode){
    	this.apiCode = apiCode;
    }

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public String getCustTokenId(){
		return custTokenId;
	}

	public void setCustTokenId(String custTokenId){
		this.custTokenId = custTokenId;
	}

	public Long getAcctId(){
		return acctId;
	}
	
	public void setAcctId(Long acctId){
		this.acctId = acctId;
	}

	@Override
	public String toString(){
		return "QryProdInstAcctRelReqVo [apiCode=" + apiCode + ", custId=" + custId + ", custTokenId=" + custTokenId
		        + ", prodInstId=" + prodInstId + ",acctId" + acctId + "]";
	}


}
