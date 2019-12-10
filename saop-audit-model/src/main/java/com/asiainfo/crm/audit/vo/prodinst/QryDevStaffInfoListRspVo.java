package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.DevStaffInfo;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryDevStaffInfoListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;
	private List<DevStaffInfo> devStaffInfos;

	public List<DevStaffInfo> getDevStaffInfos(){
		return devStaffInfos;
	}

	public void setDevStaffInfos(List<DevStaffInfo> devStaffInfos){
		this.devStaffInfos = devStaffInfos;
	}

}
