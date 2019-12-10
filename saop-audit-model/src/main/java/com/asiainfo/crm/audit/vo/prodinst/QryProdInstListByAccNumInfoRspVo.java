package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class QryProdInstListByAccNumInfoRspVo implements Serializable{

	private static final long serialVersionUID = -5212894446533530061L;

	private List<ProdInstVoExtend> prodInsts;

	public List<ProdInstVoExtend> getProdInsts(){
		return prodInsts;
	}

	public void setProdInsts(List<ProdInstVoExtend> prodInsts){
		this.prodInsts = prodInsts;
	}

}
