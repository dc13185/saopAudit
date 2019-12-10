package com.asiainfo.crm.audit.vo.offerinst;

import java.util.Date;
import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author Administrator
 */
public class QryOfferInstListReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = 4589782696036475517L;

	/**
	 * 产权客户标识
	 */
	private Long ownerCustId;
	/**
	 * 产品实例标识
	 */
	private Long prodInstId;

	private Long offerInstId;
	/**
	 * 作用客户标识
	 * 
	 * @return
	 */
	private String effCustId;
	/**
	 * 作用账户标识
	 * 
	 * @return
	 */
	private String effAcctId;

	/**
	 * 银行帐号
	 * 
	 * @return
	 */
	private String effBankNbr;
	/**
	 * 外部号码
	 * 
	 * @return
	 */
	private String extNbr;
	/**
	 * 销售品规格
	 */
	private Long offerId;
	/**
	 * 销售品实例生效时间范围
	 */
	private Date effDateScope;
	/**
	 * 销售品实例失效时间范围
	 */
	private Date expDateScope;
	/**
	 * 业务号码
	 * 
	 * @return
	 */
	private String accNbr;
	/**
	 * 产品标识
	 */
	private Long prodId;
	/**
	 * 销售品实例状态
	 */
	private List<String> statusCds;

	public Long getOwnerCustId(){
		return ownerCustId;
	}

	public void setOwnerCustId(Long ownerCustId){
		this.ownerCustId = ownerCustId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getEffCustId(){
		return effCustId;
	}

	public void setEffCustId(String effCustId){
		this.effCustId = effCustId;
	}

	public String getEffAcctId(){
		return effAcctId;
	}

	public void setEffAcctId(String effAcctId){
		this.effAcctId = effAcctId;
	}

	public String getEffBankNbr(){
		return effBankNbr;
	}

	public void setEffBankNbr(String effBankNbr){
		this.effBankNbr = effBankNbr;
	}

	public String getExtNbr(){
		return extNbr;
	}

	public void setExtNbr(String extNbr){
		this.extNbr = extNbr;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setOfferId(Long offerId){
		this.offerId = offerId;
	}

	public Date getEffDateScope(){
		return effDateScope;
	}

	public void setEffDateScope(Date effDateScope){
		this.effDateScope = effDateScope;
	}

	public Date getExpDateScope(){
		return expDateScope;
	}

	public void setExpDateScope(Date expDateScope){
		this.expDateScope = expDateScope;
	}

	public String getAccNbr(){
		return accNbr;
	}

	public void setAccNbr(String accNbr){
		this.accNbr = accNbr;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public List<String> getStatusCds(){
		return statusCds;
	}

	public void setStatusCds(List<String> statusCds){
		this.statusCds = statusCds;
	}

	public Long getOfferInstId(){
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId){
		this.offerInstId = offerInstId;
	}

	/**
	 * 分页信息
	 */
	// pageInfo PageInfo

}
