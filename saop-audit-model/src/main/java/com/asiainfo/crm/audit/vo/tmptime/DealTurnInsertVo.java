package com.asiainfo.crm.audit.vo.tmptime;

import java.io.Serializable;


/**
 * 客户订单查询返回结果对象
 * @author Administrator
 *
 */
public class DealTurnInsertVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4400715247814425210L;

	//业务类型
	private String type;

	//表名
	private String tableName;

	//表实体
	private Object tableObj;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public Object getTableObj() {
		return tableObj;
	}

	public void setTableObj(Object tableObj) {
		this.tableObj = tableObj;
	}
}
