package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * 
 * 
 *
 */
public class QryRealNameInfoReqVo implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -2908146601647964403L;
    
    private Long prodInstId;

	public Long getProdInstId(){
	    return prodInstId;
    }

	public void setProdInstId(Long prodInstId){
	    this.prodInstId = prodInstId;
    }
		
}
