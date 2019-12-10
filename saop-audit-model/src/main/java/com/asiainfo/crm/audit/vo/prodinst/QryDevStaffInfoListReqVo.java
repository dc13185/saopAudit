package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryDevStaffInfoListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long devStaffInfoId;

	public Long getDevStaffInfoId(){
		return devStaffInfoId;
	}

	public void setDevStaffInfoId(Long devStaffInfoId){
		this.devStaffInfoId = devStaffInfoId;
	}

}
