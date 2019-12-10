package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
/**
 * @author admin
 */
public class QryProdInstAttrListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstAttrVoExtend> prodInstAttrs;

	public List<ProdInstAttrVoExtend> getProdInstAttrs(){
		return prodInstAttrs;
	}

	public void setProdInstAttrs(List<ProdInstAttrVoExtend> prodInstAttrs){
		this.prodInstAttrs = prodInstAttrs;
	}

}
