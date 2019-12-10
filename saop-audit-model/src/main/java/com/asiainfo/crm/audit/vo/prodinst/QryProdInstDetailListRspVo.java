package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

import java.util.List;

/**
 * Created by ewen on 2017/8/15 0015.
 */
public class QryProdInstDetailListRspVo extends BasePageInfoVo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 334161433563909450L;
	
	private List<ProdInstVoExtend> prodInstDetailList;

    public List<ProdInstVoExtend> getProdInstDetailList() {
        return prodInstDetailList;
    }

    public void setProdInstDetailList(List<ProdInstVoExtend> prodInstDetailList) {
        this.prodInstDetailList = prodInstDetailList;
    }
}
