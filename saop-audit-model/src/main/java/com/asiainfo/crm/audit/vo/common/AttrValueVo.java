package com.asiainfo.crm.audit.vo.common;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 *
 */
public class AttrValueVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	 * 记录属性值的主键.
	 */
	private Long attrValueId;
	/**
	 * 记录业务对象属性规格业务编码.
	 */
	private Long attrId;
	/**
	 * 记录属性值名称.
	 */
	private String attrValueName;
	/**
	 * 记录属性值描述 .
	 */
	private String attrValueDesc;
	/**
	 * 记录属性值.
	 */
	private String attrValue;
	/**
	 * 记录同个属性不同的属性值在界面展现的顺序号.
	 */
	private String attrValueSort;

	public Long getAttrValueId(){
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId){
		this.attrValueId = attrValueId;
	}

	public Long getAttrId(){
		return attrId;
	}

	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}

	public String getAttrValueName(){
		return attrValueName;
	}

	public void setAttrValueName(String attrValueName){
		this.attrValueName = attrValueName;
	}

	public String getAttrValueDesc(){
		return attrValueDesc;
	}

	public void setAttrValueDesc(String attrValueDesc){
		this.attrValueDesc = attrValueDesc;
	}

	public String getAttrValue(){
		return attrValue;
	}

	public void setAttrValue(String attrValue){
		this.attrValue = attrValue;
	}

	public String getAttrValueSort(){
		return attrValueSort;
	}

	public void setAttrValueSort(String attrValueSort){
		this.attrValueSort = attrValueSort;
	}

}
