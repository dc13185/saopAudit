package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * @author admin
 */
public class QryProdInstAloneRspVo implements Serializable{

	private static final long serialVersionUID = -4301363205883932553L;

	private ProdInstVoExtend prodInst;

	public ProdInstVoExtend getProdInst(){
		return prodInst;
	}

	public void setProdInst(ProdInstVoExtend prodInst){
		this.prodInst = prodInst;
	}

}
