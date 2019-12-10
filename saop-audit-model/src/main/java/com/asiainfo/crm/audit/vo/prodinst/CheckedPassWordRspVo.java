package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * 客户鉴权认证产品或账号密码响应对象.
 * 
 * @author Administrator
 */
public class CheckedPassWordRspVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Boolean isChecked;

	public Boolean getIsChecked(){
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked){
		this.isChecked = isChecked;
	}

}
