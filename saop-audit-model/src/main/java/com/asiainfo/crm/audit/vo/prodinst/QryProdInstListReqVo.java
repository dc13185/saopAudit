package com.asiainfo.crm.audit.vo.prodinst;

/**
 * @author fortune
 */
public class QryProdInstListReqVo extends ProdInstVo{

	private static final long serialVersionUID = 1L;

	private Long custId;

	private Long zProdInstId;

	private String relType;

	private Long offerId;

	private Long accProdId;

	private String accNum;

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public Long getzProdInstId(){
		return zProdInstId;
	}

	public void setzProdInstId(Long zProdInstId){
		this.zProdInstId = zProdInstId;
	}

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

	public Long getAccProdId(){
		return accProdId;
	}

	public void setAccProdId(Long accProdId){
		this.accProdId = accProdId;
	}

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

}
