package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.audit.model.prodinst.ProdInstRelAttr;

/**
 * 
 * @author Administrator
 *
 */
public class ProdInstRelAttrVoExtend extends ProdInstRelAttr{

	/**
     * 
     */
    private static final long serialVersionUID = 5365594472626788732L;
    
    private String attrName;

	public String getAttrName(){
	    return attrName;
    }

	public void setAttrName(String attrName){
	    this.attrName = attrName;
    } 
}
