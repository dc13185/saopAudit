package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInstAcctRel;
import com.asiainfo.crm.audit.model.prodinst.ProdInstAcctRelAttr;

/**
 *
 */
public class ProdInstAcctRelVoExtend extends ProdInstAcctRel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -720145368448828111L;
	
	/**
	 * 省内新增字段 用来落省内的帐户合同号
	 */
	private String acctCd;
	/**
	 * 省内新增字段 用来区分四川A、B大区，因送计费接口时，送的数据不一致 A区送Acct_Cd，B大区送acct_id
	 */
	private Integer regionType;
	/**
	 * 帐户注册的名称
	 */
	private String paymentMan = "";
	/*
	 * 谭分比例
	 */
	private Long percent = 100L;
	/**
	 * 记录参与人唯一标识，作为外键，帐户原来挂在客户下，现改挂参与人下。
	 */
	private Long custId;

	/**
	 * 银行名称
	 */
	private String bankName = "";

	/**
	 * 银行账号
	 */
	private String bankAcctCd = "";

	/**
	 * 接入号码
	 */
	private String accessNumber = "";

	/**
	 * 投递联系地址。
	 */
	private String mailingDetail = "";

	/**
	 * 为每种付款方式定义的唯一代码 。OTC-0001
	 */
	private Integer paymentMethod;
	/**
	 * 付费方式名称
	 */
	private String paymentTypeName = "";

	private List<ProdInstAcctRelAttr> prodInstAcctRelAttrs;

	private String payAcctName;

	private String acctBillingTypeName;

	private Long acctBillingType;

	public Integer getPaymentMethod(){
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getBankName(){
		return bankName;
	}

	public void setBankName(String bankName){
		if(bankName != null){
			this.bankName = bankName;
		}
	}

	public String getAcctCd(){
		return acctCd;
	}

	public void setAcctCd(String acctCd){
		this.acctCd = acctCd;
	}

	public Integer getRegionType(){
		return regionType;
	}

	public void setRegionType(Integer regionType){
		this.regionType = regionType;
	}

	public Long getPercent(){
		return percent;
	}

	public void setPercent(Long percent){
		if(percent != null){
			this.percent = percent;
		}
	}

	public String getBankAcctCd(){
		return bankAcctCd;
	}

	public void setBankAcctCd(String bankAcctCd){
		if(bankAcctCd != null){
			this.bankAcctCd = bankAcctCd;
		}
	}

	public String getAccessNumber(){
		return accessNumber;
	}

	public void setAccessNumber(String accessNumber){
		if(accessNumber != null){
			this.accessNumber = accessNumber;
		}
	}

	public String getMailingDetail(){
		return mailingDetail;
	}

	public void setMailingDetail(String mailingDetail){
		if(mailingDetail != null){
			this.mailingDetail = mailingDetail;
		}
	}

	public String getPaymentTypeName(){
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName){
		if(paymentTypeName != null){
			this.paymentTypeName = paymentTypeName;
		}
	}

	public Long getCustId(){
		return custId;
	}

	public void setCustId(Long custId){
		this.custId = custId;
	}

	public String getPaymentMan(){
		return paymentMan;
	}

	public void setPaymentMan(String paymentMan){
		if(paymentMan != null){
			this.paymentMan = paymentMan;
		}
	}

	public List<ProdInstAcctRelAttr> getProdInstAcctRelAttrs(){
		return prodInstAcctRelAttrs;
	}

	public void setProdInstAcctRelAttrs(List<ProdInstAcctRelAttr> prodInstAcctRelAttrs){
		this.prodInstAcctRelAttrs = prodInstAcctRelAttrs;
	}

	public String getPayAcctName(){
		return payAcctName;
	}

	public void setPayAcctName(String payAcctName){
		this.payAcctName = payAcctName;
	}

	public String getAcctBillingTypeName(){
		return acctBillingTypeName;
	}

	public void setAcctBillingTypeName(String acctBillingTypeName){
		this.acctBillingTypeName = acctBillingTypeName;
	}

	public Long getAcctBillingType(){
		return acctBillingType;
	}

	public void setAcctBillingType(Long acctBillingType){
		this.acctBillingType = acctBillingType;
	}

}
