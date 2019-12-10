package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccProdInstListReq extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private String accNum;
	private String account;
	private Long ownerCustId;
	private Long useCustId;
	private Long prodId;
	private List<String> statusCds;
	private Long lastOrderItemId;
	private Long regionId;
	private String isDetail;

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

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
	}

	public Long getUseCustId(){
		return useCustId;
	}

	public void setUseCustId(Long useCustId){
		this.useCustId = useCustId;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}

	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

	public String getIsDetail(){
		return isDetail;
	}

	public void setIsDetail(String isDetail){
		this.isDetail = isDetail;
	}

}
