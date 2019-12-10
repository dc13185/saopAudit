package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * @author admin
 */
public class QryProdInstListByAccNumInfoReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;

	private String accNum;
	private Integer anTypeCd;
	private Long prodId;
	private Long regionId;

	public String getAccNum(){
		return accNum;
	}

	public void setAccNum(String accNum){
		this.accNum = accNum;
	}

	public Integer getAnTypeCd(){
		return anTypeCd;
	}

	public void setAnTypeCd(Integer anTypeCd){
		this.anTypeCd = anTypeCd;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setProdId(Long prodId){
		this.prodId = prodId;
	}

	public Long getRegionId(){
		return regionId;
	}

	public void setRegionId(Long regionId){
		this.regionId = regionId;
	}

}
