package com.asiainfo.crm.blueprint.model;

import java.io.Serializable;
import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 * Created by chenchao on 2017/11/13.
 */
public class OrdProdResInstRel extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录关系标识，主键。
	 */
	private Long prodResInstRelId;

	/**
	 * 记录产品实例标识，外键。
	 */
	private Long prodInstId;

	/**
	 * 记录产品和营销资源实体区分。1000表示终端类型1100表示卡类型
	 */
	private String mktResType;

	/**
	 * 记录营销资源实例标识，外键。
	 */
	private Long mktResInstId;

	/**
	 * 记录营销资源标识，外键。
	 */
	private Long mktResId;

	/**
	 * 记录产品实例和营销类资源实例的关联方式:1,产品实例使用营销类资源;2,营销类资源实例作为产品实例
	 */
	private String relType;

	/**
	 * 记录终端的使用类型：自备、租用、购买等。LOVB=PRI-C-0010
	 */
	private String propertyType;

	/**
	 * 记录营销资源数量。
	 */
	private Integer mktResNum;

	/**
	 * 记录数据的行号，主键。
	 */
	private Long rowId;

	/**
	 * 记录订单项标识。
	 */
	private Long orderItemId;

	/**
	 * 记录变更或删除记录对应的原始行号。
	 */
	private Long oldRowId;

	/**
	 * 操作类型，新增/修改/删除/保持
	 */
	private String operType;

	/**
	 * 营销资源实例编码
	 */
	private String mktResInstNbr;
	/**
	 * 版本号
	 */
	private Integer verNum;
	/**
	 * 资源名称
	 */
	private String mktResName;

	private Long extMktResInstId;
	
	private Long mktPrice;
	
	//以下为拓展字段 
	private String gsmIMSI;
	private String cdmaIMSI;
	private String lteIMSI;
	//拓展字段结束
	private Long regionId;
	
	public String getGsmIMSI(){
		return gsmIMSI;
	}

	public void setGsmIMSI(String gsmIMSI){
		this.gsmIMSI = gsmIMSI;
	}

	public String getCdmaIMSI(){
		return cdmaIMSI;
	}

	public void setCdmaIMSI(String cdmaIMSI){
		this.cdmaIMSI = cdmaIMSI;
	}

	public String getLteIMSI(){
		return lteIMSI;
	}

	public void setLteIMSI(String lteIMSI){
		this.lteIMSI = lteIMSI;
	}
	public Long getExtMktResInstId(){
		return extMktResInstId;
	}

	public void setExtMktResInstId(Long extMktResInstId){
		this.extMktResInstId = extMktResInstId;
	}

	public Integer getVerNum(){
		return verNum;
	}

	public void setVerNum(Integer verNum){
		this.verNum = verNum;
	}

	public Long getProdResInstRelId(){
		return prodResInstRelId;
	}

	public void setProdResInstRelId(Long prodResInstRelId){
		this.prodResInstRelId = prodResInstRelId;
	}

	public Long getProdInstId(){
		return prodInstId;
	}

	public void setProdInstId(Long prodInstId){
		this.prodInstId = prodInstId;
	}

	public String getMktResType(){
		return mktResType;
	}

	public void setMktResType(String mktResType){
		this.mktResType = mktResType;
	}

	public Long getMktResInstId(){
		return mktResInstId;
	}

	public void setMktResInstId(Long mktResInstId){
		this.mktResInstId = mktResInstId;
	}

	public String getRelType(){
		return relType;
	}

	public void setRelType(String relType){
		this.relType = relType;
	}

	public String getPropertyType(){
		return propertyType;
	}

	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
	}

	public Integer getMktResNum(){
		return mktResNum;
	}

	public void setMktResNum(Integer mktResNum){
		this.mktResNum = mktResNum;
	}

	public Long getRowId(){
		return rowId;
	}

	public void setRowId(Long rowId){
		this.rowId = rowId;
	}

	public Long getOrderItemId(){
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId){
		this.orderItemId = orderItemId;
	}

	public Long getOldRowId(){
		return oldRowId;
	}

	public void setOldRowId(Long oldRowId){
		this.oldRowId = oldRowId;
	}

	public String getOperType(){
		return operType;
	}

	public void setOperType(String operType){
		this.operType = operType;
	}

	public String getMktResInstNbr(){
		return mktResInstNbr;
	}

	public void setMktResInstNbr(String mktResInstNbr){
		this.mktResInstNbr = mktResInstNbr;
	}

	public String getMktResName(){
		return mktResName;
	}

	public void setMktResName(String mktResName){
		this.mktResName = mktResName;
	}

	public Long getMktResId(){
		return mktResId;
	}

	public void setMktResId(Long mktResId){
		this.mktResId = mktResId;
	}
	
	public Long getMktPrice() {
		return mktPrice;
	}
	
	public void setMktPrice(Long mktPrice) {
		this.mktPrice = mktPrice;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	

}
