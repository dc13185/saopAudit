package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstRelRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private List<ProdInstRel> prodInstRels;

	public List<ProdInstRel> getProdInstRels() {
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRel> prodInstRels) {
		this.prodInstRels = prodInstRels;
	}



}
