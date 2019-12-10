package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.angel.persistence.plugin.pagination.PageInfo;
import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstVoExtend;
/**
 * 产品实例列表响应.
 * @author Administrator
 *
 */
public class ProdInstListMutilRsp {
	
	private List<ProdInstVoExtend> accProdInsts;
	private PageInfo pageInfo;
	private List<ProdInst> prodInsts;

	public List<ProdInstVoExtend> getAccProdInsts() {
		return accProdInsts;
	}

	public void setAccProdInsts(List<ProdInstVoExtend> accProdInsts) {
		this.accProdInsts = accProdInsts;
	}

	
    public PageInfo getPageInfo(){
    	return pageInfo;
    }

	
    public void setPageInfo(PageInfo pageInfo){
    	this.pageInfo = pageInfo;
    }

	public List<ProdInst> getProdInsts() {
		return prodInsts;
	}

	public void setProdInsts(List<ProdInst> prodInsts) {
		this.prodInsts = prodInsts;
	}
}
