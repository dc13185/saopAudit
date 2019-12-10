package com.asiainfo.crm.audit.vo.prodinst;

import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;
/**
 *
 */
public class AccNumRelVo extends  BasePageInfoVo{
	
	private static final long serialVersionUID = 1L;

	 /**
	 * 记录号码关系标识，主键。 
	 */
	   private Long accNumRelId;
	 /**
	 * 记录A端号码标识。 
	 */
	   private Long aAccNumId;
	 /**
	 * 记录Z端号码标识。 
	 */
	   private Long zAccNumId;
	 /**
	 * 记录关系类型，LOVB=PRI-C-0035 
	 */
	   private String relType;
	 /**
	 * 记录上一次维护记录的订单项标识。 
	 */
	   private Long lastOrderItemId;
	
	public Long getAccNumRelId(){
		return accNumRelId;
	}
	public void setAccNumRelId(Long accNumRelId){
		this.accNumRelId = accNumRelId;
	}
	public Long getaAccNumId(){
		return aAccNumId;
	}
	public void setaAccNumId(Long aAccNumId){
		this.aAccNumId = aAccNumId;
	}
	public Long getzAccNumId(){
		return zAccNumId;
	}
	public void setzAccNumId(Long zAccNumId){
		this.zAccNumId = zAccNumId;
	}
	public String getRelType(){
		return relType;
	}
	public void setRelType(String relType){
		this.relType = relType;
	}
	public Long getLastOrderItemId(){
		return lastOrderItemId;
	}
	public void setLastOrderItemId(Long lastOrderItemId){
		this.lastOrderItemId = lastOrderItemId;
	}
	
}