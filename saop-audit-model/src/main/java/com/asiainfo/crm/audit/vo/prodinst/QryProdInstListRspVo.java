package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInst> prodInsts;

	public List<ProdInst> getProdInsts(){
		return prodInsts;
	}

	public void setProdInsts(List<ProdInst> prodInsts){
		this.prodInsts = prodInsts;
	}

}
