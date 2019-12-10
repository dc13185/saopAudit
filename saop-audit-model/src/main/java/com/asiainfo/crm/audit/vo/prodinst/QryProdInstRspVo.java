package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInst;
/**
 * 
 * @author Administrator
 *
 */
public class QryProdInstRspVo implements Serializable{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInst> prodInsts;

	public List<ProdInst> getProdInsts(){
		return prodInsts;
	}

	public void setProdInsts(List<ProdInst> prodInsts){
		this.prodInsts = prodInsts;
	}

}
