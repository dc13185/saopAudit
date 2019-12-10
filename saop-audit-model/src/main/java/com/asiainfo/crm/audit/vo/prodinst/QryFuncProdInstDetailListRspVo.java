package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryFuncProdInstDetailListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	private List<QryFuncProdInstDetailRsp> prodInstDetailList;

	public List<QryFuncProdInstDetailRsp> getProdInstDetailList(){
		return prodInstDetailList;
	}

	public void setProdInstDetailList(List<QryFuncProdInstDetailRsp> prodInstDetailList){
		this.prodInstDetailList = prodInstDetailList;
	}

}
