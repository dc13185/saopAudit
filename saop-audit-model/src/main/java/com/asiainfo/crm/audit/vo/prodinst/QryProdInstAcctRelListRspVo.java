package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAcctRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAcctRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstAcctRel> prodInstAcctRels;

	public List<ProdInstAcctRel> getProdInstAcctRels(){
		return prodInstAcctRels;
	}

	public void setProdInstAcctRels(List<ProdInstAcctRel> prodInstAcctRels){
		this.prodInstAcctRels = prodInstAcctRels;
	}

}
