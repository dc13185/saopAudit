package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAttr;

/**
 *
 */
public class ProdInstAttrVoExtend extends  ProdInstAttr{
	
	private static final long serialVersionUID = 1L;

	private String attrName;
	
	private String statusCd;		
	
	
    public String getStatusCd(){
    	return statusCd;
    }

	
    public void setStatusCd(String statusCd){
    	this.statusCd = statusCd;
    }

	public String getAttrName(){
		return attrName;
	}
	
	public void setAttrName(String attrName){
		this.attrName = attrName;
	}
		
}