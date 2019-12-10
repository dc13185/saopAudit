package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryFuncProdInstListRsp extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<ProdInstVoExtend> funcProdInsts;

	public List<ProdInstVoExtend> getFuncProdInsts(){
		return funcProdInsts;
	}

	public void setFuncProdInsts(List<ProdInstVoExtend> funcProdInsts){
		this.funcProdInsts = funcProdInsts;
	}

}
