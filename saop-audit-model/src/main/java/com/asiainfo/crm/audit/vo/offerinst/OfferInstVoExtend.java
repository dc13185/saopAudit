package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferInst;
import com.asiainfo.crm.audit.model.offerinst.OfferInstAssure;
import com.asiainfo.crm.audit.model.offerinst.OfferInstAttr;
import com.asiainfo.crm.audit.model.offerinst.OfferObjInstRel;
import com.asiainfo.crm.audit.model.offerinst.OfferResInstRel;
import com.asiainfo.crm.audit.vo.prodinst.DevStaffInfoVoExtend;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstVoExtend;

/**
 * @author fortune
 */
public class OfferInstVoExtend extends OfferInst{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1904508217826484440L;

	private String offerName;

	/**
	 * 销售品详细描述.
	 */
	private String offerDesc;
	private Long roleId;
	private String roleName;
	


	/**
	 * 记录产权客户标识.
	 */
	private String ownerCustName;

	private String regionName;

	private String statusName;

	private String statusCd;

	private List<OfferProdInstRelVoExtend> offerProdInstRels;

	private List<OfferInstAttr> offerInstAttrs;

	private List<OfferObjInstRel> offerObjInstRels;

	private List<OfferInstRelVoExtend> offerInstRels;

	private List<OfferResInstRel> offerResInstRels;

	private List<OfferInstAssure> offerInstAssures;

	// private List<DevStaffInfo> devStaffInfos;
	private List<DevStaffInfoVoExtend> devStaffInfos;

	// private List<OfferInstFeeInfo> offerInstFeeInfos;

	private List<OfferInstFeeInfoVoExtend> offerInstFeeInfos;
	private List<ProdInstVoExtend> prodInsts;

	private List<OfferInstPaymodeVoExtend> offerInstPaymodes;

	private List<OfferInstVoExtend> preferentialOfferInsts;

	private List<OfferObjInstRelVoExtend> preferentialAccts;

	public List<OfferInstAttr> getOfferInstAttrs(){
		return offerInstAttrs;
	}

	public void setOfferInstAttrs(List<OfferInstAttr> offerInstAttrs){
		this.offerInstAttrs = offerInstAttrs;
	}

	public List<OfferObjInstRel> getOfferObjInstRels(){
		return offerObjInstRels;
	}

	public void setOfferObjInstRels(List<OfferObjInstRel> offerObjInstRels){
		this.offerObjInstRels = offerObjInstRels;
	}

	public List<OfferInstRelVoExtend> getOfferInstRels(){
		return offerInstRels;
	}

	public void setOfferInstRels(List<OfferInstRelVoExtend> offerInstRels){
		this.offerInstRels = offerInstRels;
	}

	public List<OfferResInstRel> getOfferResInstRels(){
		return offerResInstRels;
	}

	public void setOfferResInstRels(List<OfferResInstRel> offerResInstRels){
		this.offerResInstRels = offerResInstRels;
	}

	public List<OfferInstAssure> getOfferInstAssures(){
		return offerInstAssures;
	}

	public void setOfferInstAssures(List<OfferInstAssure> offerInstAssures){
		this.offerInstAssures = offerInstAssures;
	}

	public List<DevStaffInfoVoExtend> getDevStaffInfos(){
		return devStaffInfos;
	}

	public void setDevStaffInfos(List<DevStaffInfoVoExtend> devStaffInfos){
		this.devStaffInfos = devStaffInfos;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public String getOfferDesc(){
		return offerDesc;
	}

	public void setOfferDesc(String offerDesc){
		this.offerDesc = offerDesc;
	}

	public String getOwnerCustName(){
		return ownerCustName;
	}

	public void setOwnerCustName(String ownerCustName){
		this.ownerCustName = ownerCustName;
	}

	public String getRegionName(){
		return regionName;
	}

	public void setRegionName(String regionName){
		this.regionName = regionName;
	}

	public String getStatusName(){
		return statusName;
	}

	public void setStatusName(String statusName){
		this.statusName = statusName;
	}

	public List<OfferProdInstRelVoExtend> getOfferProdInstRels(){
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRelVoExtend> offerProdInstRels){
		this.offerProdInstRels = offerProdInstRels;
	}

	public List<ProdInstVoExtend> getProdInsts(){
		return prodInsts;
	}

	public void setProdInsts(List<ProdInstVoExtend> prodInsts){
		this.prodInsts = prodInsts;
	}

	public String getOfferName(){
		return offerName;
	}

	public void setOfferName(String offerName){
		this.offerName = offerName;
	}

	public List<OfferInstFeeInfoVoExtend> getOfferInstFeeInfos(){
		return offerInstFeeInfos;
	}

	public void setOfferInstFeeInfos(List<OfferInstFeeInfoVoExtend> offerInstFeeInfos){
		this.offerInstFeeInfos = offerInstFeeInfos;
	}

	public List<OfferInstPaymodeVoExtend> getOfferInstPaymodes(){
		return offerInstPaymodes;
	}

	public void setOfferInstPaymodes(List<OfferInstPaymodeVoExtend> offerInstPaymodes){
		this.offerInstPaymodes = offerInstPaymodes;
	}

	public List<OfferInstVoExtend> getPreferentialOfferInsts(){
		return preferentialOfferInsts;
	}

	public void setPreferentialOfferInsts(List<OfferInstVoExtend> preferentialOfferInsts){
		this.preferentialOfferInsts = preferentialOfferInsts;
	}

	public List<OfferObjInstRelVoExtend> getPreferentialAccts(){
		return preferentialAccts;
	}

	public void setPreferentialAccts(List<OfferObjInstRelVoExtend> preferentialAccts){
		this.preferentialAccts = preferentialAccts;
	}
    public Long getRoleId(){
    	return roleId;
    }

	
    public void setRoleId(Long roleId){
    	this.roleId = roleId;
    }

	
    public String getRoleName(){
    	return roleName;
    }

	
    public void setRoleName(String roleName){
    	this.roleName = roleName;
    }
}
