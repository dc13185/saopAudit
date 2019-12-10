package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstRelInfoByMapRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	private List<ProdInstRelVoExtend> prodInstRels;

	public List<ProdInstRelVoExtend> getProdInstRels(){
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRelVoExtend> prodInstRels){
		this.prodInstRels = prodInstRels;
	}

}
