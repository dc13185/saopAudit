package com.asiainfo.crm.audit.vo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class ScopeInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 分页查询请求页号
	 */
	private Integer pageIndex;

	private Integer pageSize;
	private String scope;
	private Long rowCount;

	public String getScope(){
		return scope;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public Integer getPageIndex(){
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex){
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize(){
		return pageSize;
	}

	public void setPageSize(Integer pageSize){
		this.pageSize = pageSize;
	}

	public Long getRowCount(){
		return rowCount;
	}

	public void setRowCount(Long rowCount){
		this.rowCount = rowCount;
	}

}
