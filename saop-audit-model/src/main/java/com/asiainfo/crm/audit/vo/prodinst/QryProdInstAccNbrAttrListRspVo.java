package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNumAttr;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAccNbrAttrListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstAccNumAttr> prodInstAccNumAttrs;

	public List<ProdInstAccNumAttr> getProdInstAccNumAttrs(){
		return prodInstAccNumAttrs;
	}

	public void setProdInstAccNumAttrs(List<ProdInstAccNumAttr> prodInstAccNumAttrs){
		this.prodInstAccNumAttrs = prodInstAccNumAttrs;
	}

}
