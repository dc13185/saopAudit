package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstState;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryProdInstStateListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstState> prodInstStates;

	public List<ProdInstState> getProdInstStates(){
		return prodInstStates;
	}

	public void setProdInstStates(List<ProdInstState> prodInstStates){
		this.prodInstStates = prodInstStates;
	}

}
