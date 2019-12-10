package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.audit.vo.prodinst.ProdInstVoExtend;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 */
public class QryOfferProdToOfferRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private List<ProdInstVoExtend> accProdInsts;

	
    public List<ProdInstVoExtend> getAccProdInsts(){
    	return accProdInsts;
    }

	
    public void setAccProdInsts(List<ProdInstVoExtend> accProdInsts){
    	this.accProdInsts = accProdInsts;
    }

	
}
