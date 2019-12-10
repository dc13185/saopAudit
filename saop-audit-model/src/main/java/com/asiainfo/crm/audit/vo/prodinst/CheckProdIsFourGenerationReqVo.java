package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * @author admin
 */
public class CheckProdIsFourGenerationReqVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long prodInstId;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

}
