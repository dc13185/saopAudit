package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.vo.ScopeInfo;

/**
 * 查询产品实例详情请求对象.
 * @author Administrator
 *
 */
public class QryProdInstDetailReqVo extends ProdInstVo{
	/**
     * 
     */
    private static final long serialVersionUID = -1516290057310533673L;
    
	/**
	 * 一个服务下的能力编码.
	 */
	private String apiCode;

	private String mktResInstNbr;
	
	
	private List<ScopeInfo> scopeInfos;

    
    public String getApiCode(){
    	return apiCode;
    }

    public void setApiCode(String apiCode){
    	this.apiCode = apiCode;
    }

	public String getMktResInstNbr() {
		return mktResInstNbr;
	}

	public void setMktResInstNbr(String mktResInstNbr) {
		this.mktResInstNbr = mktResInstNbr;
	}

	public List<ScopeInfo> getScopeInfos(){
    	return scopeInfos;
    }

	
    public void setScopeInfos(List<ScopeInfo> scopeInfos){
    	this.scopeInfos = scopeInfos;
    }

}
