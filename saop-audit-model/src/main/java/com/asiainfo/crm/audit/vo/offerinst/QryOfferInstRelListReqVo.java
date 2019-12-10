package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class QryOfferInstRelListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;
	/**
	 * A端销售品实例标识
	 */
	private Long aOfferInstId;
	/**
	 * Z端销售品实例标识
	 */
	private Long zOfferInstId;

	private Long offerInstId;

	private String relType;

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

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}
}
