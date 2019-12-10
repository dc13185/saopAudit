package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccNumRelListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long accNumRelId;

	public Long getAccNumRelId(){
		return accNumRelId;
	}

	public void setAccNumRelId(Long accNumRelId){
		this.accNumRelId = accNumRelId;
	}

}
