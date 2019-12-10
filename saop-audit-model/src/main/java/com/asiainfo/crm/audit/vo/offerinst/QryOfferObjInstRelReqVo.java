package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class QryOfferObjInstRelReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -2611280501720224448L;
	private Long offerInstId;
	private Long offerObjInstRelId;
	private Long objType;
	private String objId;
	private Long offerObjRelId;

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public Long getOfferObjInstRelId(){
		return offerObjInstRelId;
	}

	public void setOfferObjInstRelId(Long offerObjInstRelId){
		this.offerObjInstRelId = offerObjInstRelId;
	}

	public Long getObjType(){
		return objType;
	}

	public void setObjType(Long objType){
		this.objType = objType;
	}

	public String getObjId(){
	    return objId;
    }

	public void setObjId(String objId){
	    this.objId = objId;
    }

	public Long getOfferObjRelId(){
	    return offerObjRelId;
    }

	public void setOfferObjRelId(Long offerObjRelId){
	    this.offerObjRelId = offerObjRelId;
    }
}
