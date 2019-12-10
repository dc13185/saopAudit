package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstAccNbrAttrListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long accNumInstAttrId;

	public Long getAccNumInstAttrId(){
		return accNumInstAttrId;
	}

	public void setAccNumInstAttrId(Long accNumInstAttrId){
		this.accNumInstAttrId = accNumInstAttrId;
	}

}
