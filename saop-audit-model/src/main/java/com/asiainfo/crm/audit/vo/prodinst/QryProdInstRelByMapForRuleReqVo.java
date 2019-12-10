package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;
import com.asiainfo.crm.bcomm.base.vo.BasePageInfoVo;

/**
 * 查询产品实例关系列表入参
 * 
 * @author admin
 */
public class QryProdInstRelByMapForRuleReqVo extends BasePageInfoVo{

	private static final long serialVersionUID = -5212894446533530061L;


	private Long aProdInstId;


	private Long attrId;


	private List<Long> zProdIds;


	
    public List<Long> getzProdIds(){
    	return zProdIds;
    }


	
    
    public Long getaProdInstId(){
    	return aProdInstId;
    }



	
    public void setaProdInstId(Long aProdInstId){
    	this.aProdInstId = aProdInstId;
    }



	
    public Long getAttrId(){
    	return attrId;
    }



	
    public void setAttrId(Long attrId){
    	this.attrId = attrId;
    }



	public void setzProdIds(List<Long> zProdIds){
    	this.zProdIds = zProdIds;
    }

	
}
