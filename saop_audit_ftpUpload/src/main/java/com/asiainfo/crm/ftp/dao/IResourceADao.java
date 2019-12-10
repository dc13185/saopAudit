package com.asiainfo.crm.ftp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("resourceADao")
public interface IResourceADao {

	public List<Map<String, String>> qryCardRes(String areaId);

	public List<Map<String, Object>> qryPhoneNumber(Map<String, Object> params);

	// iccId
	public String qryTerminalCode(String terminalDevId);

	// 卡资源查imis status_cd areaId
	public List<Map<String, Object>> qryDevNum(String terminalDevId);

	// 查记录总数
	public int qryCount();

	// 查terminal_dev_item的 TERMINAL_DEV_ID  
	public List<Map<String, Object>> qryterminalDevId(Map<String, Object> params);

	// 通过terminalDevId查出DevNumId
	public List<Map<String, Object>> qryDevNumId(String terminalDevId);

	// 查询4G卡总数
	public int qryPhoneNumberCount();
	
}
