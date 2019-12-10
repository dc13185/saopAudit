package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * @author admin
 */
public class QryAccProdInstDetailReq implements Serializable{

	private static final long serialVersionUID = -5212894446533530061L;

	private String isDetail;
	private Long prodInstId;
	private String accNum;
	private Long prodId;
	private String account;
	private Long regionId;
	private List<ScopeInfo> scopeInfos;

	public String getIsDetail(){
		return isDetail;
	}

	public void setIsDetail(String isDetail){
		this.isDetail = isDetail;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
