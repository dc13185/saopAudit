package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.audit.vo.ScopeInfo;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

import java.util.List;

/**
 * Created by ewen on 2017/8/15 0015.
 */
public class QryProdInstDetailListReqVo extends BasePageInfoVo{

    /**
     * 
     */
    private static final long serialVersionUID = -7853645550404204198L;

	/**
     * 记录产品实例的业务号码。
     */
    private String accNum;

    private List<ScopeInfo> scopeInfos;

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public List<ScopeInfo> getScopeInfos() {
        return scopeInfos;
    }

    public void setScopeInfos(List<ScopeInfo> scopeInfos) {
        this.scopeInfos = scopeInfos;
    }
}
