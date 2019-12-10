package com.asiainfo.crm.blueprint.smo;

import org.springframework.httpservice.annotation.Center;
import org.springframework.httpservice.annotation.MethodAttr;

@Center("saopAudit")
public interface DataExtractServiceSmo {
	/**
	 * 数据抽取入口方法
	 * @param entity
	 * @return
	 */ 
	@MethodAttr(resolveParam = false,handleReturn = false)
	public String execute(String auditOriginDataXml);
}
