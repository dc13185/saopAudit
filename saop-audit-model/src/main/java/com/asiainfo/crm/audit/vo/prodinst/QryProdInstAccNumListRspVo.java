package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNum;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAccNumListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<ProdInstAccNum> prodInstAccNums;

	public List<ProdInstAccNum> getProdInstAccNums(){
		return prodInstAccNums;
	}

	public void setProdInstAccNums(List<ProdInstAccNum> prodInstAccNums){
		this.prodInstAccNums = prodInstAccNums;
	}

}
