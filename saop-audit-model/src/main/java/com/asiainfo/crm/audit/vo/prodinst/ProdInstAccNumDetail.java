package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.AccNumRel;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNum;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNumAttr;

/**
 * @author admin
 */
public class ProdInstAccNumDetail extends ProdInstAccNum{

	private static final long serialVersionUID = -1516290057310533673L;

	private List<AccNumRel> accNumRels;
	private List<ProdInstAccNumAttr> prodInstAccNumAttrs;

	public List<AccNumRel> getAccNumRels(){
		return accNumRels;
	}

	public void setAccNumRels(List<AccNumRel> accNumRels){
		this.accNumRels = accNumRels;
	}

	public List<ProdInstAccNumAttr> getProdInstAccNumAttrs(){
		return prodInstAccNumAttrs;
	}

	public void setProdInstAccNumAttrs(List<ProdInstAccNumAttr> prodInstAccNumAttrs){
		this.prodInstAccNumAttrs = prodInstAccNumAttrs;
	}

}
