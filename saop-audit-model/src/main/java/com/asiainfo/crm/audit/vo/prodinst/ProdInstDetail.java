package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.offerinst.OfferProdInstRel;
import com.asiainfo.crm.audit.model.prodinst.DevStaffInfo;
import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNum;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAttr;
import com.asiainfo.crm.audit.model.prodinst.ProdInstContact;
import com.asiainfo.crm.audit.model.prodinst.ProdInstPaymode;
import com.asiainfo.crm.audit.model.prodinst.ProdInstRel;
import com.asiainfo.crm.audit.model.prodinst.ProdInstState;
import com.asiainfo.crm.audit.model.prodinst.ProdResInstRel;

/**
 * @author admin
 */
public class ProdInstDetail extends ProdInst{

	private static final long serialVersionUID = 1L;

	private List<ProdInstAccNumDetail> prodInstAccNumDetails;

	private List<ProdInstAccNum> prodInstAccNums;

	private List<ProdInstAttr> prodInstAttrs;

	private List<ProdInstRel> prodInstRels;

	private List<OfferProdInstRel> offerProdInstRels;

	private List<DevStaffInfo> devStaffInfos;

	private List<ProdInstContact> prodInstContacts;

	private List<ProdResInstRel> prodResInstRels;

	private List<ProdInstState> prodInstStates;

	private List<ProdInstPaymode> prodInstPaymodes;

	private List<ProdInstAcctRelVoExtend> prodInstAcctRels;

	public List<ProdInstAccNumDetail> getProdInstAccNumDetails(){
		return prodInstAccNumDetails;
	}

	public void setProdInstAccNumDetails(List<ProdInstAccNumDetail> prodInstAccNumDetails){
		this.prodInstAccNumDetails = prodInstAccNumDetails;
	}

	public List<ProdInstAttr> getProdInstAttrs(){
		return prodInstAttrs;
	}

	public void setProdInstAttrs(List<ProdInstAttr> prodInstAttrs){
		this.prodInstAttrs = prodInstAttrs;
	}

	public List<ProdInstRel> getProdInstRels(){
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRel> prodInstRels){
		this.prodInstRels = prodInstRels;
	}

	public List<OfferProdInstRel> getOfferProdInstRels(){
		return offerProdInstRels;
	}

	public void setOfferProdInstRels(List<OfferProdInstRel> offerProdInstRels){
		this.offerProdInstRels = offerProdInstRels;
	}

	public List<DevStaffInfo> getDevStaffInfos(){
		return devStaffInfos;
	}

	public void setDevStaffInfos(List<DevStaffInfo> devStaffInfos){
		this.devStaffInfos = devStaffInfos;
	}

	public List<ProdInstContact> getProdInstContacts(){
		return prodInstContacts;
	}

	public void setProdInstContacts(List<ProdInstContact> prodInstContacts){
		this.prodInstContacts = prodInstContacts;
	}

	public List<ProdResInstRel> getProdResInstRels(){
		return prodResInstRels;
	}

	public void setProdResInstRels(List<ProdResInstRel> prodResInstRels){
		this.prodResInstRels = prodResInstRels;
	}

	public List<ProdInstState> getProdInstStates(){
		return prodInstStates;
	}

	public void setProdInstStates(List<ProdInstState> prodInstStates){
		this.prodInstStates = prodInstStates;
	}

	public List<ProdInstAccNum> getProdInstAccNums(){
		return prodInstAccNums;
	}

	public void setProdInstAccNums(List<ProdInstAccNum> prodInstAccNums){
		this.prodInstAccNums = prodInstAccNums;
	}

	public List<ProdInstPaymode> getProdInstPaymodes(){
		return prodInstPaymodes;
	}

	public void setProdInstPaymodes(List<ProdInstPaymode> prodInstPaymodes){
		this.prodInstPaymodes = prodInstPaymodes;
	}

	public List<ProdInstAcctRelVoExtend> getProdInstAcctRels(){
		return prodInstAcctRels;
	}

	public void setProdInstAcctRels(List<ProdInstAcctRelVoExtend> prodInstAcctRels){
		this.prodInstAcctRels = prodInstAcctRels;
	}

}
