package com.asiainfo.crm.blueprint.dao;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("saopOldProvCenterDao")
public interface ISaopOldProvCenterDao {
	/**
	 * 保存C网稽核数据
	 * @param params
	 */
	public void saveCProdAudit(Map<String, String> params);

	/**
	 * 更新c网稽核数据
	 * @param params
	 */
	public void updateCProdAudit(Map<String, String> params);

	/**
	 * 删除c网稽核数据
	 * @param params
	 */
	public void delCProdAudit(Map<String, String> params);

	/**
	 * 查询c网稽核数据
	 * @param params
	 * @return
	 */
	public int queryCProdAudit(Map<String, String> params);
}
