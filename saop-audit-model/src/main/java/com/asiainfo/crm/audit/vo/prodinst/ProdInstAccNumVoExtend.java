package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.AccNumRel;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNum;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAccNumAttr;

/**
 * @author fortune
 */
public class ProdInstAccNumVoExtend extends ProdInstAccNum{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836518091950416942L;

	/**
	 * 记录接入号的类型名称。LOVB=PRI-C-0006 .
	 */
	private String accNumTypeName;

	private String statusCd;

	private String statusCdName;

	private List<ProdInstAccNumAttr> prodInstAccNumAttr;
	private List<AccNumRel> accNumRel;

	public String getAccNumTypeName(){
		return accNumTypeName;
	}

	public void setAccNumTypeName(String accNumTypeName){
		this.accNumTypeName = accNumTypeName;
	}

	public String getStatusCdName(){
		return statusCdName;
	}

	
    public List<ProdInstAccNumAttr> getProdInstAccNumAttr(){
    	return prodInstAccNumAttr;
    }

	
    public void setProdInstAccNumAttr(List<ProdInstAccNumAttr> prodInstAccNumAttr){
    	this.prodInstAccNumAttr = prodInstAccNumAttr;
    }

	
    public List<AccNumRel> getAccNumRel(){
    	return accNumRel;
    }

	
    public void setAccNumRel(List<AccNumRel> accNumRel){
    	this.accNumRel = accNumRel;
    }

	public void setStatusCdName(String statusCdName){
		this.statusCdName = statusCdName;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

}
