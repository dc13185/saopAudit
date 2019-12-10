package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.audit.model.offerinst.OfferInst;
import com.asiainfo.crm.audit.model.offerinst.OfferInstRel;

/**
 * @author fortune
 */
public class OfferInstRelVoExtend extends OfferInstRel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3094151227909978919L;

	private OfferInst aOfferInst;

	private String statusCd;

	private OfferInst zOfferInst;

	private String roleName;

	private String aExtOfferInstId;

	private String zExtOfferInstId;

	public OfferInst getaOfferInst(){
		return aOfferInst;
	}

	public void setaOfferInst(OfferInst aOfferInst){
		this.aOfferInst = aOfferInst;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public OfferInst getzOfferInst(){
		return zOfferInst;
	}

	public void setzOfferInst(OfferInst zOfferInst){
		this.zOfferInst = zOfferInst;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getzExtOfferInstId(){
		return zExtOfferInstId;
	}

	public void setzExtOfferInstId(String zExtOfferInstId){
		this.zExtOfferInstId = zExtOfferInstId;
	}

	public String getaExtOfferInstId(){
		return aExtOfferInstId;
	}

	public void setaExtOfferInstId(String aExtOfferInstId){
		this.aExtOfferInstId = aExtOfferInstId;
	}

}
