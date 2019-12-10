package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.audit.model.prodinst.DevStaffInfo;

/**
 * @author admin
 */
public class DevStaffInfoVoExtend extends DevStaffInfo{

	private static final long serialVersionUID = 8862575288596799363L;

	private String staffName;

	public String getStaffName(){
		return staffName;
	}

	public void setStaffName(String staffName){
		this.staffName = staffName;
	}

}
