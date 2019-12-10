package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

/**
 * 查询支付关系列表返回对象.
 * 
 * @author Administrator
 */
public class QryProdInstAcctRelRspVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<ProdInstAcctRelVo> prodInstAcctRels;

	public List<ProdInstAcctRelVo> getProdInstAcctRels(){
		return prodInstAcctRels;
	}

	public void setProdInstAcctRels(List<ProdInstAcctRelVo> prodInstAcctRels){
		this.prodInstAcctRels = prodInstAcctRels;
	}

}
