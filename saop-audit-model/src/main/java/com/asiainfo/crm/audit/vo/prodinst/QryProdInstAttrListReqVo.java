package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

/**
 * @author admin
 */
public class QryProdInstAttrListReqVo extends ProdInstAttrVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private List<Long> attrIds;

	public List<Long> getAttrIds(){
		return attrIds;
	}

	public void setAttrIds(List<Long> attrIds){
		this.attrIds = attrIds;
	}

}
