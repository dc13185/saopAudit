package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryFuncProdInstListReq extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private String isDetail;

	private Long accProdInstId;

	private Long funcProdId;

	private List<String> statusCds;

	public Long getAccProdInstId(){
		return accProdInstId;
	}

	public void setAccProdInstId(Long accProdInstId){
		this.accProdInstId = accProdInstId;
	}

	public Long getFuncProdId(){
		return funcProdId;
	}

	public void setFuncProdId(Long funcProdId){
		this.funcProdId = funcProdId;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public String getIsDetail(){
		return isDetail;
	}

	public void setIsDetail(String isDetail){
		this.isDetail = isDetail;
	}

}
