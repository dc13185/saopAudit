package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.audit.model.offerinst.OfferProdInstRel;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstVoExtend;

/**
 * @author fortune
 */
public class OfferProdInstRelVoExtend extends OfferProdInstRel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024175983475013810L;

	private String roleName;

	/**
	 * 记录状态 .
	 */
	private String statusCd;

	private String statusName;

	private String prodName;

	private OfferInstVoExtend offerInst;

	private ProdInstVoExtend prodInst;

	private Long offerId;

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public String getStatusName(){
		return statusName;
	}

	public void setStatusName(String statusName){
		this.statusName = statusName;
	}

	public OfferInstVoExtend getOfferInst(){
		return offerInst;
	}

	public void setOfferInst(OfferInstVoExtend offerInst){
		this.offerInst = offerInst;
	}

	public ProdInstVoExtend getProdInst(){
		return prodInst;
	}

	public void setProdInst(ProdInstVoExtend prodInst){
		this.prodInst = prodInst;
	}

	public String getProdName(){
		return prodName;
	}

	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

}
