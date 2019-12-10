package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 查询产品实例关系回参
 * 
 * @author admin
 */
public class QryProdInstRelByMapForRuleRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private List<ProdInstRelVoExtend> prodInstRels;

	public List<ProdInstRelVoExtend> getProdInstRels() {
		return prodInstRels;
	}

	public void setProdInstRels(List<ProdInstRelVoExtend> prodInstRels) {
		this.prodInstRels = prodInstRels;
	}

	
}
