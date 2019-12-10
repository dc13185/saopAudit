package com.asiainfo.crm.audit.vo.offerinst;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class QryOfferInstDetailListRsp implements Serializable{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<QryOfferInstDetailListRspVo> offerInstDetailList;

	public List<QryOfferInstDetailListRspVo> getOfferInstDetailList(){
		return offerInstDetailList;
	}

	public void setOfferInstDetailList(List<QryOfferInstDetailListRspVo> offerInstDetailList){
		this.offerInstDetailList = offerInstDetailList;
	}

}
