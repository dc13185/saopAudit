package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdResInstRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdResInstRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdResInstRel> prodResInstRels;

	public List<ProdResInstRel> getProdResInstRels(){
		return prodResInstRels;
	}

	public void setProdResInstRels(List<ProdResInstRel> prodResInstRels){
		this.prodResInstRels = prodResInstRels;
	}

}
