package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstContact;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstContactListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstContact> prodInstContacts;

	public List<ProdInstContact> getProdInstContacts(){
		return prodInstContacts;
	}

	public void setProdInstContacts(List<ProdInstContact> prodInstContacts){
		this.prodInstContacts = prodInstContacts;
	}

}
