package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstRelAttr;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstRelAttrRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstRelAttr> prodInstRelAttrs;

	public List<ProdInstRelAttr> getProdInstRelAttrs(){
		return prodInstRelAttrs;
	}

	public void setProdInstRelAttrs(List<ProdInstRelAttr> prodInstRelAttrs){
		this.prodInstRelAttrs = prodInstRelAttrs;
	}

}
