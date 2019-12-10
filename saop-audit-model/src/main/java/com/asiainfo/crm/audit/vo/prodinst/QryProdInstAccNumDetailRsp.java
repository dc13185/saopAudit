package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryProdInstAccNumDetailRsp extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private ProdInstAccNumVoExtend prodInstAccNumDetail;

	public ProdInstAccNumVoExtend getProdInstAccNumDetail(){
		return prodInstAccNumDetail;
	}

	public void setProdInstAccNumDetail(ProdInstAccNumVoExtend prodInstAccNumDetail){
		this.prodInstAccNumDetail = prodInstAccNumDetail;
	}

}
