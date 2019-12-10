package com.asiainfo.crm.ftp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("saopFtpDao")
public interface ISaopFtpDao {
	
	/**
	 * 新增ftp上传日志
	 * @param insertParams
	 * @throws Exception
	 */
	public void insertLog(Map<String, String> insertParams) throws Exception;

	/**
	 * 更新ftp上传日志
	 * @param params
	 * @throws Exception
	 */
	public void updateLog(Map<String, String> params) throws Exception;

	/**
	 * 把数据转到历史表
	 * @param transforSql
	 */
	public void insertHistory(Map<String, String> paras);

	/**
	 * 转到历史表后删除当前表
	 * @param paras
	 */
	public void deleteSql(Map<String, String> paras);

	/**
	 * 将由于上传未处理成功的侦听记录状态改为D
	 */
	public void updateListenAuditFlag();

	/**
	 * 更新稽核开关状态
	 * @param switchParams
	 */
	public void updateAuditSwitch(Map<String, String> switchParams);

	/**
	 * 查询稽核开关状态
	 * @param switchParams
	 * @return
	 */
	public String queryAuditSwitch(Map<String, String> switchParams);

	/**
	 * 新增稽核开关状态
	 * @param switchParams
	 */
	public void insertAuditSwitch(Map<String, String> switchParams);

	/**
	 * 省CRM受理集团卡。将imis为空的先转储到历史表
	 */
	public void transCardInfo2His();

	/**
	 * 省CRM受理集团卡。删除imis为空的卡信息
	 */
	public void deleteCardInfo();

	/**
	 * 将侦听调用失败并且稽核来源数据表状态为‘W’的数据刷为D状态
	 */
	public void refreshListenAudit();
	
	public List<Map<String, String>> qryAreaId();
	
	public List<Map<String, String>> qryLandId();

	public String qryZoneNbr(String areaId);
	
}
