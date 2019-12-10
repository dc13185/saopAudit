package com.asiainfo.crm.blueprint.smo;

import com.asiainfo.crm.blueprint.model.AuditInputEntity;

/**
 * 数据抽取接口
 * @author chenchao
 *
 */
public interface ISaopAuditSmo {
	/**
	 * 数据抽取
	 * @param auditInfo
	 */
	public void dataExtraction(AuditInputEntity auditInputEntity) throws Exception;
}
