package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstPaymode;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstPaymodeListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstPaymode> prodInstPaymodes;

	public List<ProdInstPaymode> getProdInstPaymodes(){
		return prodInstPaymodes;
	}

	public void setProdInstPaymodes(List<ProdInstPaymode> prodInstPaymodes){
		this.prodInstPaymodes = prodInstPaymodes;
	}

}
