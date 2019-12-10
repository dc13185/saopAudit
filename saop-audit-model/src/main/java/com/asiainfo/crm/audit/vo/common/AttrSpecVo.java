package com.asiainfo.crm.audit.vo.common;

import java.io.Serializable;

/**
 * time :2017-06-02 11:32:31.
 */
public class AttrSpecVo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录属性的主键 .
	 */
	private Long attrId;
	/**
	 * 记录业务大类主键 .
	 */
	private Long busiTypeId;
	/**
	 * 记录父级属性的标识 .
	 */
	private Long parAttrId;
	/**
	 * 记录属性编码 .
	 */
	private String attrNbr;
	/**
	 * 记录业务对象属性规格名称 .
	 */
	private String attrName;
	/**
	 * 记录业务对象属性规格详细描述 .
	 */
	private String attrDesc;
	/**
	 * 记录属性规格默认取值 .
	 */
	private String defaultValue;
	/**
	 * 记录属性规格取值范围之最小值 .
	 */
	private String valueFrom;
	/**
	 * 记录属性规格取值范围之最大值 .
	 */
	private String valueTo;
	/**
	 * 记录属性值在业务对象实例中唯一，LOVB=PUB-C-0006 .
	 */
	private Integer isUnique;
	/**
	 * 记录是否可空，LOVB=PUB-C-0006 .
	 */
	private Integer isNullable;
	/**
	 * 记录属性值数据类型，LOVB=PUB-C-0014 .
	 */
	private String attrValueDataType;
	/**
	 * 记录是否动态属性，动态属性在横表，静态属性在纵表，LOVB=PUB-C-0006 .
	 */
	private Integer isDanyAttr;
	/**
	 * 记录属性值类型，LOVB=PUB-C-0007 .
	 */
	private String attrValueType;
	/**
	 * 记录属性规格值格式(正则表达式),用于属性值生成、合法性效验 .
	 */
	private String attrFormat;
	/**
	 * 记录属性规格值长度 .
	 */
	private Integer attrLength;
	/**
	 * 系统编码，用于与计费同步 .
	 */
	private String code;

	public Long getAttrId(){
		return attrId;
	}

	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}

	public Long getBusiTypeId(){
		return busiTypeId;
	}

	public void setBusiTypeId(Long busiTypeId){
		this.busiTypeId = busiTypeId;
	}

	public Long getParAttrId(){
		return parAttrId;
	}

	public void setParAttrId(Long parAttrId){
		this.parAttrId = parAttrId;
	}

	public String getAttrNbr(){
		return attrNbr;
	}

	public void setAttrNbr(String attrNbr){
		this.attrNbr = attrNbr;
	}

	public String getAttrName(){
		return attrName;
	}

	public void setAttrName(String attrName){
		this.attrName = attrName;
	}

	public String getAttrDesc(){
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc){
		this.attrDesc = attrDesc;
	}

	public String getDefaultValue(){
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue){
		this.defaultValue = defaultValue;
	}

	public String getValueFrom(){
		return valueFrom;
	}

	public void setValueFrom(String valueFrom){
		this.valueFrom = valueFrom;
	}

	public String getValueTo(){
		return valueTo;
	}

	public void setValueTo(String valueTo){
		this.valueTo = valueTo;
	}

	public Integer getIsUnique(){
		return isUnique;
	}

	public void setIsUnique(Integer isUnique){
		this.isUnique = isUnique;
	}

	public Integer getIsNullable(){
		return isNullable;
	}

	public void setIsNullable(Integer isNullable){
		this.isNullable = isNullable;
	}

	public String getAttrValueDataType(){
		return attrValueDataType;
	}

	public void setAttrValueDataType(String attrValueDataType){
		this.attrValueDataType = attrValueDataType;
	}

	public Integer getIsDanyAttr(){
		return isDanyAttr;
	}

	public void setIsDanyAttr(Integer isDanyAttr){
		this.isDanyAttr = isDanyAttr;
	}

	public String getAttrValueType(){
		return attrValueType;
	}

	public void setAttrValueType(String attrValueType){
		this.attrValueType = attrValueType;
	}

	public String getAttrFormat(){
		return attrFormat;
	}

	public void setAttrFormat(String attrFormat){
		this.attrFormat = attrFormat;
	}

	public Integer getAttrLength(){
		return attrLength;
	}

	public void setAttrLength(Integer attrLength){
		this.attrLength = attrLength;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

}
