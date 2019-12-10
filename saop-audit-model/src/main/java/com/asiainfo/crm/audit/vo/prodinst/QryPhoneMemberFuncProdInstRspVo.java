package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryPhoneMemberFuncProdInstRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<ProdInstVoExtend> prodInsts;

	public List<ProdInstVoExtend> getProdInsts(){
		return prodInsts;
	}

	public void setProdInsts(List<ProdInstVoExtend> prodInsts){
		this.prodInsts = prodInsts;
	}

}
