package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferProdInstRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;
	
	private List<OfferProdInstRelVoExtend> offerProdInstRelDetailVos;

	public List<OfferProdInstRelVoExtend> getOfferProdInstRelDetailVos(){
		return offerProdInstRelDetailVos;
	}

	public void setOfferProdInstRelDetailVos(List<OfferProdInstRelVoExtend> offerProdInstRelDetailVos){
		this.offerProdInstRelDetailVos = offerProdInstRelDetailVos;
	}
}
