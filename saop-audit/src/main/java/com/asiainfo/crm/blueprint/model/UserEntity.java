package com.asiainfo.crm.blueprint.model;

import java.util.Date;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

/**
 * 用户实体
 * @author shish
 */
public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969950282809510470L;
	
	private String id;
	
	private String name;
	
	private String accountId;
	
	private String passWord;
	
	private String idCard;
	
	private Date createDate;
	/**
	 * 状态 0:正常 -1:锁定
	 */
	private int status;
	
	private int wrongTimes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getWrongTimes() {
		return wrongTimes;
	}

	public void setWrongTimes(int wrongTimes) {
		this.wrongTimes = wrongTimes;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", accountId=" + accountId + ", passWord=" + passWord
				+ ", idCard=" + idCard + ", createDate=" + createDate + ", status=" + status + ", wrongTimes="
				+ wrongTimes + "]";
	}
}
