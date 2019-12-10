package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author admin
 */
public class QryFuncProdInstDetailListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private String accNum;
	private String account;
	private Long prodId;
	private Long prodInstId;
	private Long ydsfProdInstId;
	private Long custId;
	private Long acctId;
	private String acctCd;
	private List<String> statusCds;
	private Long regionId;
	private List<ScopeInfo> scopeInfos;
	private List<Long> funcProdIds;

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public Long getYdsfProdInstId(){
		return ydsfProdInstId;
	}

	public void setYdsfProdInstId(Long ydsfProdInstId){
		this.ydsfProdInstId = ydsfProdInstId;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setAcctId(Long acctId){
		this.acctId = acctId;
	}

	public String getAcctCd(){
		return acctCd;
	}

	public void setAcctCd(String acctCd){
		this.acctCd = acctCd;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

	public List<ScopeInfo> getScopeInfos(){
		return scopeInfos;
	}

	public void setScopeInfos(List<ScopeInfo> scopeInfos){
		this.scopeInfos = scopeInfos;
	}

	public List<Long> getFuncProdIds(){
		return funcProdIds;
	}

	public void setFuncProdIds(List<Long> funcProdIds){
		this.funcProdIds = funcProdIds;
	}

}
