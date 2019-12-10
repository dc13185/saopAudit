package com.asiainfo.crm.audit.vo.prodinst;

import java.io.Serializable;

/**
 * 
 * 
 *
 */
public class QryRealNameInfoRspVo implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -4014750397947604138L;
    
    /**
     * 产权客户
     */
    private Long ownerCustId;
    
    /**
     * 使用客户
     */
    private Long useCustId;
    
    /**
     * 卡角色标识
     * MAJOR_CARD 主卡
     * MINOR_CARD 副卡
     * null 不存在主副卡角色
     */
    private String cardRole;

	public Long getOwnerCustId(){
	    return ownerCustId;
    }

	public void setOwnerCustId(Long ownerCustId){
	    this.ownerCustId = ownerCustId;
    }

	public Long getUseCustId(){
	    return useCustId;
    }

	public void setUseCustId(Long useCustId){
	    this.useCustId = useCustId;
    }

	public String getCardRole(){
	    return cardRole;
    }

	public void setCardRole(String cardRole){
	    this.cardRole = cardRole;
    }

}
