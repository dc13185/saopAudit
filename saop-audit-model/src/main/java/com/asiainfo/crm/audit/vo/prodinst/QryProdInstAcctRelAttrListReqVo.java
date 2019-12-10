package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAcctRelAttrListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long prodInstAcctRelAttrId;

	public Long getProdInstAcctRelAttrId(){
		return prodInstAcctRelAttrId;
	}

	public void setProdInstAcctRelAttrId(Long prodInstAcctRelAttrId){
		this.prodInstAcctRelAttrId = prodInstAcctRelAttrId;
	}

}
