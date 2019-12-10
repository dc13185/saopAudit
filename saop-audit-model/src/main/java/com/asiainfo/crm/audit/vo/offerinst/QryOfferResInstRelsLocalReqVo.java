package com.asiainfo.crm.audit.vo.offerinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryOfferResInstRelsLocalReqVo extends BasePageInfoVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 187410424125480923L;

	/**
	 * 记录销售品实例标识，外键。 
	 */
	   private Long offerInstId;

	 /**
	 * 记录该资源的使用产品实例，例如终端的的使用产品实例。 
	 */
	   private Long useProdInstId;
	 /**
	 * 记录关系类型，表达销售品同营销资源的关联方式，LOVB=PRI-C-0023 
	 */
	   private String relType;
		/**
		 * 营销资源实例编码 
		 */
		   private String mktResInstNbr;
	 
    public Long getOfferInstId(){
    	return offerInstId;
    }

	
    public void setOfferInstId(Long offerInstId){
    	this.offerInstId = offerInstId;
    }

	
    public Long getUseProdInstId(){
    	return useProdInstId;
    }

	
    public void setUseProdInstId(Long useProdInstId){
    	this.useProdInstId = useProdInstId;
    }

	
    public String getRelType(){
    	return relType;
    }

	
    public void setRelType(String relType){
    	this.relType = relType;
    }

	
    public String getMktResInstNbr(){
    	return mktResInstNbr;
    }

	
    public void setMktResInstNbr(String mktResInstNbr){
    	this.mktResInstNbr = mktResInstNbr;
    }


}
