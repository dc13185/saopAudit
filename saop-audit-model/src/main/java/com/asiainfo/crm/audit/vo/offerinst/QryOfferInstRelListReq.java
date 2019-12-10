package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferInstRelListReq extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;
	private Long aOfferInstId;

	private Long zOfferInstId;

	private String relType;

	private Long roleId;

	private List<String> statusCds;
	
	private String isDetail;

	public Long getaOfferInstId(){
		return aOfferInstId;
	}

	public void setaOfferInstId(Long aOfferInstId){
		this.aOfferInstId = aOfferInstId;
	}

	public Long getzOfferInstId(){
		return zOfferInstId;
	}

	public void setzOfferInstId(Long zOfferInstId){
		this.zOfferInstId = zOfferInstId;
	}

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

	public Long getRoleId(){
		return roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

}
