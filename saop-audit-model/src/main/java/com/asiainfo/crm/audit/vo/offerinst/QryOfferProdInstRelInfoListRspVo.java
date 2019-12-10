package com.asiainfo.crm.audit.vo.offerinst;

import java.util.List;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 
 * @author fortune
 *
 */
public class QryOfferProdInstRelInfoListRspVo extends BasePageInfoVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1188872878848598039L;

	
	private List<MainOfferInfoVo> mainOfferInfoVos;


	public List<MainOfferInfoVo> getMainOfferInfoVos() {
		return mainOfferInfoVos;
	}


	public void setMainOfferInfoVos(List<MainOfferInfoVo> mainOfferInfoVos) {
		this.mainOfferInfoVos = mainOfferInfoVos;
	}
	
	
	
}
