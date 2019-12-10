package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferProdInstRelListReq extends BasePageInfoVo {

	private static final long serialVersionUID = 1188872878848598039L;

	private Long offerInstId;
	private Long prodInstId;
	private String relType;
	private Long roleId;
	private List<String> statusCds;

	private String isDetail;

	public Long getOfferInstId() {
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId) {
		this.offerInstId = offerInstId;
	}

	public Long getProdInstId() {
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId) {
		this.prodInstId = prodInstId;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<String> getStatusCds() {
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds) {
		this.statusCds = statusCds;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

}
