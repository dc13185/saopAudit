package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstRel> prodInstRels;

	public List<ProdInstRel> getProdInstRels(){
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRel> prodInstRels){
		this.prodInstRels = prodInstRels;
	}

}
