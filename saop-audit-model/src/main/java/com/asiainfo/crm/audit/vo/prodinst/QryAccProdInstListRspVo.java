package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccProdInstListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	List<ProdInst> accProdInsts;

	public List<ProdInst> getAccProdInsts(){
		return accProdInsts;
	}

	public void setAccProdInsts(List<ProdInst> accProdInsts){
		this.accProdInsts = accProdInsts;
	}

}
