package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author fortune
 */
public class QryProdInstRelReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private String aProdInstId;

	private String zProdInstId;

	private String relType;

	private List<String> statusCds;

	public String getaProdInstId(){
		return aProdInstId;
	}

	public void setaProdInstId(String aProdInstId){
		this.aProdInstId = aProdInstId;
	}

	public String getzProdInstId(){
		return zProdInstId;
	}

	public void setzProdInstId(String zProdInstId){
		this.zProdInstId = zProdInstId;
	}

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}
}
