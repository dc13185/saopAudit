package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * @author admin
 */
public class CheckProdIsFourGenerationRspVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String isFourGeneration;

	public String getIsFourGeneration(){
		return isFourGeneration;
	}

	public void setIsFourGeneration(String isFourGeneration){
		this.isFourGeneration = isFourGeneration;
	}

}
