package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstVo;

/**
 * 获取产品实例列表的请求对象.
 * 
 * @author Administrator
 */
public class QueryProdInstMutilReqVo extends ProdInstVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2864485296015039907L;
	/**
	 * 一个服务下能力区分编码.
	 */
	private String apiCode;
	/**
	 * 客户的tokenID.
	 */
	private String custTokenId;

	/**
	 * 产权客户ID.
	 */
	private Long custId;

	private String isNeedBaseOffer;

	private String isNeedRelOffer;

	private List<Long> prodIds;

	private List<Long> prodInstIds;

	private List<Long> acctIds;

	private List<Long> regionIds;

	private Long offerInstId;

	public String getApiCode(){
		return apiCode;
	}

	public void setApiCode(String apiCode){
		this.apiCode = apiCode;
	}

	public String getCustTokenId(){
		return custTokenId;
	}

	public void setCustTokenId(String custTokenId){
		this.custTokenId = custTokenId;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public String getIsNeedBaseOffer(){
		return isNeedBaseOffer;
	}

	public void setIsNeedBaseOffer(String isNeedBaseOffer){
		this.isNeedBaseOffer = isNeedBaseOffer;
	}

	public String getIsNeedRelOffer() {
		return isNeedRelOffer;
	}

	public void setIsNeedRelOffer(String isNeedRelOffer) {
		this.isNeedRelOffer = isNeedRelOffer;
	}

	public List<Long> getProdIds(){
		return prodIds;
	}

	public void setProdIds(List<Long> prodIds){
		this.prodIds = prodIds;
	}

	public List<Long> getProdInstIds(){
		return prodInstIds;
	}

	public void setProdInstIds(List<Long> prodInstIds){
		this.prodInstIds = prodInstIds;
	}

	public List<Long> getAcctIds(){
		return acctIds;
	}

	public void setAcctIds(List<Long> acctIds){
		this.acctIds = acctIds;
	}

	public List<Long> getRegionIds(){
		return regionIds;
	}

	public void setRegionIds(List<Long> regionIds){
		this.regionIds = regionIds;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

}
