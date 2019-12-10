package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class QryProdInstDetailRspVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private ProdInstVoExtend prodInstDetail;

	public ProdInstVoExtend getProdInstDetail(){
		return prodInstDetail;
	}

	public void setProdInstDetail(ProdInstVoExtend prodInstDetail){
		this.prodInstDetail = prodInstDetail;
	}

}
