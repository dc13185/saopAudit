package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAcctRelAttr;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAcctRelAttrListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstAcctRelAttr> prodInstAcctRelAttrs;

	public List<ProdInstAcctRelAttr> getProdInstAcctRelAttrs(){
		return prodInstAcctRelAttrs;
	}

	public void setProdInstAcctRelAttrs(List<ProdInstAcctRelAttr> prodInstAcctRelAttrs){
		this.prodInstAcctRelAttrs = prodInstAcctRelAttrs;
	}

}
