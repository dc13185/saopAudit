package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferInstListOfPackageReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;
	private Long aOfferInstId;
	private Long relType;

	public Long getaOfferInstId(){
		return aOfferInstId;
	}

	public void setaOfferInstId(Long aOfferInstId){
		this.aOfferInstId = aOfferInstId;
	}

	public Long getRelType(){
		return relType;
	}

	public void setRelType(Long relType){
		this.relType = relType;
	}

}
