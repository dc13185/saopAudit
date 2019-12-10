package com.asiainfo.crm.audit.vo.common;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 *
 */
public class CommonRegionVo extends BasePageInfoVo{

	private static final long serialVersionUID = 1L;

	/**
	*  
	*/
	private Long commonRegionId;
	/**
	 * 记录上级区域标识.
	 */
	private Long parRegionId;
	/**
	 * 记录区域名称.
	 */
	private String regionName;
	/**
	 * 记录区域的拼音.
	 */
	private String regionPyName;
	/**
	 * 记录区域编码.
	 */
	private String regionNbr;
	/**
	 * 记录区域类型。LOVB=LOC-0001.
	 */
	private String regionType;
	/**
	 * 记录区域描述.
	 */
	private String regionDesc;
	/**
	 * 记录区域的级别。LOVB=LOC-C-0004.
	 */
	private Integer regionLevel;
	/**
	 * 记录区域的排序.
	 */
	private Integer regionSort;
	/**
	 * 记录省分编码，冗余区域表省分的编码.
	 */
	private String provinceNbr;
	/**
	 * 记录是城市还是乡村，LOVB=PUB-C-0004.
	 */
	private Integer cityFlag;
	/**
	 * 区号.
	 */
	private String zoneNumber;
	/**
	 * 记录所属本地网地区ID.
	 */
	private Long lanId;
	/**
	 * 各地区生成的ID前缀.
	 */
	private String idPrefix;

	public Long getCommonRegionId(){
		return commonRegionId;
	}

	public void setCommonRegionId(Long commonRegionId){
		this.commonRegionId = commonRegionId;
	}

	public Long getParRegionId(){
		return parRegionId;
	}

	public void setParRegionId(Long parRegionId){
		this.parRegionId = parRegionId;
	}

	public String getRegionName(){
		return regionName;
	}

	public void setRegionName(String regionName){
		this.regionName = regionName;
	}

	public String getRegionPyName(){
		return regionPyName;
	}

	public void setRegionPyName(String regionPyName){
		this.regionPyName = regionPyName;
	}

	public String getRegionNbr(){
		return regionNbr;
	}

	public void setRegionNbr(String regionNbr){
		this.regionNbr = regionNbr;
	}

	public String getRegionType(){
		return regionType;
	}

	public void setRegionType(String regionType){
		this.regionType = regionType;
	}

	public String getRegionDesc(){
		return regionDesc;
	}

	public void setRegionDesc(String regionDesc){
		this.regionDesc = regionDesc;
	}

	public Integer getRegionLevel(){
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel){
		this.regionLevel = regionLevel;
	}

	public Integer getRegionSort(){
		return regionSort;
	}

	public void setRegionSort(Integer regionSort){
		this.regionSort = regionSort;
	}

	public String getProvinceNbr(){
		return provinceNbr;
	}

	public void setProvinceNbr(String provinceNbr){
		this.provinceNbr = provinceNbr;
	}

	public Integer getCityFlag(){
		return cityFlag;
	}

	public void setCityFlag(Integer cityFlag){
		this.cityFlag = cityFlag;
	}

	public String getZoneNumber(){
		return zoneNumber;
	}

	public void setZoneNumber(String zoneNumber){
		this.zoneNumber = zoneNumber;
	}

	public Long getLanId(){
		return lanId;
	}

	public void setLanId(Long lanId){
		this.lanId = lanId;
	}

	public String getIdPrefix(){
		return idPrefix;
	}

	public void setIdPrefix(String idPrefix){
		this.idPrefix = idPrefix;
	}

}
