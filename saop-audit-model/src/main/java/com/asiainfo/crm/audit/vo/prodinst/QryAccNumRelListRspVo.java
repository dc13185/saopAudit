package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.AccNumRel;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryAccNumRelListRspVo extends BasePageInfoVo{

	private static final long serialVersionUID = -4301363205883932553L;

	private List<AccNumRel> accNumRels;

	public List<AccNumRel> getAccNumRels(){
		return accNumRels;
	}

	public void setAccNumRels(List<AccNumRel> accNumRels){
		this.accNumRels = accNumRels;
	}

}
