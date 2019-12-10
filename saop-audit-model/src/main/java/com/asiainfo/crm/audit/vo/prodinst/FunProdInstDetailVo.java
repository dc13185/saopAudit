package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BaseVo;

/**
 * 功能类产品实例详情.
 * 
 * @author admin
 */
public class FunProdInstDetailVo extends BaseVo{

	private static final long serialVersionUID = 1L;

	private ProdInstVoExtend prodInstDetail;

	public ProdInstVoExtend getProdInstDetail(){
		return prodInstDetail;
	}

	public void setProdInstDetail(ProdInstVoExtend prodInstDetail){
		this.prodInstDetail = prodInstDetail;
	}

}
