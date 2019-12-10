package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.audit.model.offerinst.OfferObjInstRel;

/**
 * @author admin
 */
public class OfferObjInstRelVoExtend extends OfferObjInstRel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200934832389634834L;

	private Long offerId;

	private String roleName;

	private String objTypeName;

	private String acctCd;

	private String acctName;

	private String statusCd;

	private String statusName;

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getObjTypeName() {
		return objTypeName;
	}

	public void setObjTypeName(String objTypeName) {
		this.objTypeName = objTypeName;
	}

	public String getAcctCd() {
		return acctCd;
	}

	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
