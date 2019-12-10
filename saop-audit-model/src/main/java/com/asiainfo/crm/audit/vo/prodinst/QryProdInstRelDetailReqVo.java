package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 查询产品实例关系列表入参
 * 
 * @author admin
 */
public class QryProdInstRelDetailReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;
	private Long prodInstRelId;

	private String aProdInstId;

	private String zProdInstId;

	private String relType;
	
	private Long roleId;



	private List<String> statusCds;

	public Long getProdInstRelId(){
		return prodInstRelId;
	}

	public void setProdInstRelId(Long prodInstRelId){
		this.prodInstRelId = prodInstRelId;
	}

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
	
    public Long getRoleId(){
    	return roleId;
    }

	
    public void setRoleId(Long roleId){
    	this.roleId = roleId;
    }
}
