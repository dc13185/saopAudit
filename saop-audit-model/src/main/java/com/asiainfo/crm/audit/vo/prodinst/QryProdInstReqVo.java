package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryProdInstReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private String accNum;

	private List<String> accNums;

	private Long prodId;

	private Long aProdInstId;

	private String relType;

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public List<String> getAccNums(){
		return accNums;
	}

	public void setAccNums(List<String> accNums){
		this.accNums = accNums;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public Long getaProdInstId(){
		return aProdInstId;
	}

	public void setaProdInstId(Long aProdInstId){
		this.aProdInstId = aProdInstId;
	}

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

}
