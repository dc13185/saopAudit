package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */

public class QryProdInstRelsReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private Long prodInstId;
	private List<String> relTypes;

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public List<String> getRelTypes(){
		return relTypes;
	}

	public void setRelTypes(List<String> relTypes){
		this.relTypes = relTypes;
	}

}
