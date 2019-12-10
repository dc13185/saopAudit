package com.asiainfo.crm.audit.vo.offerinst;

import java.util.Date;
import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferProdInstRelListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private Long offerProdInstRelId;

	private Long offerInstId;

	private Long offerId;

	private Long prodInstId;

	private Long prodFuncType;

	private Long offerProdRelId;

	private String relType;

	private Long roleId;

	private Date effDateScope;

	private Date expDateScope;

	private List<String> statusCds;

	private Long custId;

	private Long custTokenId;

	private String offerType;
	
	private String prodUseType;




	private Long prodId;


	public Long getOfferProdInstRelId(){
		return offerProdInstRelId;
	}

	public void setOfferProdInstRelId(Long offerProdInstRelId){
		this.offerProdInstRelId = offerProdInstRelId;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public Long getOfferProdRelId(){
		return offerProdRelId;
	}

	public void setOfferProdRelId(Long offerProdRelId){
		this.offerProdRelId = offerProdRelId;
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

	public Date getEffDateScope(){
		return effDateScope;
	}

	public void setEffDateScope(Date effDateScope){
		this.effDateScope = effDateScope;
	}

	public Date getExpDateScope(){
		return expDateScope;
	}

	public void setExpDateScope(Date expDateScope){
		this.expDateScope = expDateScope;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public Long getCustTokenId(){
		return custTokenId;
	}

	public void setCustTokenId(Long custTokenId){
		this.custTokenId = custTokenId;
	}

	public String getOfferType(){
		return offerType;
	}

	public void setOfferType(String offerType){
		this.offerType = offerType;
	}



	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}
    public String getProdUseType(){
    	return prodUseType;
    }

	
    public void setProdUseType(String prodUseType){
    	this.prodUseType = prodUseType;
    }

	public Long getProdFuncType() {
		return prodFuncType;
	}

	public void setProdFuncType(Long prodFuncType) {
		this.prodFuncType = prodFuncType;
	}

}
