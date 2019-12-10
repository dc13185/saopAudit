package com.asiainfo.crm.ftp.smo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.ftp.common.CommonUtil;
import com.asiainfo.crm.ftp.common.JdbcUtils;
import com.asiainfo.crm.ftp.dao.IResourceADao;
import com.asiainfo.crm.ftp.dao.IResourceBDao;
import com.asiainfo.crm.ftp.dao.ISaopFtpDao;

@Component
public class LteCardResAudit {
	private static final Logger logger = LoggerFactory.getLogger(LteCardResAudit.class);

	@Autowired
	IResourceADao resourceADao;

	@Autowired
	IResourceBDao resourceBDao;

	@Autowired
	ISaopFtpDao saopFtpDao;

	@Autowired
	ISaopFtpSmo saopFtpSmo;

	public static final long MAX_ROW_PER_TIME = 100;

	public void dataExtraction() {
		// truncateTable表
		try {
			CommonUtil.truncateTable("CEP_AUDIT_4G_CARD_RESOURCE");
			logger.debug("truncate table cep_audit_4g_card_resource successfully !");
		} catch (Exception e1) {
			logger.error("truncateTable cep_audit_4g_card_resource exception:" + e1.getMessage());
		}
		// logger.debug("Region A start writing the table
		// cep_audit_4g_card_resource !");
		// cardResource("A");
		// logger.debug("Region A writing table cep_audit_4g_card_resource
		// successfully !");
		//
		// logger.debug("Region B start writing the table
		// cep_audit_4g_card_resource !");
		// cardResource("B");
		// logger.debug("Region B writing table cep_audit_4g_card_resource
		// successfully !");
		ExecutorService executor = Executors.newFixedThreadPool(2);
		CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
		for (int j = 0; j < 2; j++) {
			final int i = j;
			cs.submit(new Callable<String>() {
				public String call() throws Exception {
					if (i == 0) {
						cardResource("A");
						return "A";
					}
					if (i == 1) {
						cardResource("B");
						return "B";
					}
					return "";
				}
			});
		}
		executor.shutdown();
		// 等待所有子线程完成
		for (int j = 0; j < 2; j++) {
			String areaId = "";
			try {
				areaId = cs.take().get();
				logger.debug(areaId + "，处理完成");
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(areaId + "，处理失败：" + e.getMessage());
			}
		}
		logger.debug("writing table cep_audit_4g_card_resource successfully !");
		// 调用ftp上传逻辑
		saopFtpSmo.execute("BUS60020");
		logger.debug("upload table cep_audit_4g_card_resource successfully !");
	}

	private void cardResource(final String regin) {
		int count = 0;
		if ("A".equals(regin)) {
			count = resourceADao.qryCount();
		} else {
			count = resourceBDao.qryCount();
		}

		logger.debug("Total is:" + count);
		if (count == 0) {
			return;
		}
		if (count <= MAX_ROW_PER_TIME) {
			try {
				saveDate(1, count, regin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			int times = (int) ((count - 1 + MAX_ROW_PER_TIME) / MAX_ROW_PER_TIME);
			ExecutorService executor = Executors.newFixedThreadPool(10);
			CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
			for (int j = 0; j < times; j++) {
				final int start = (int) (j * MAX_ROW_PER_TIME + 1);
				final int end = (int) ((j + 1) * MAX_ROW_PER_TIME);
				cs.submit(new Callable<String>() {
					public String call() throws Exception {
						saveDate(start, end, regin);
						return start + "--" + end;
					}
				});
			}
			executor.shutdown();
			// 等待所有子线程完成
			for (int j = 0; j < times; j++) {
				String areaId = "";
				try {
					areaId = cs.take().get();
					logger.debug(areaId + ",processing completed!");
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug(areaId + ",processing failed：" + e.getMessage());
				}
			}
		}
	}

	private void saveDate(int start, int end, String regin) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		List<Map<String, Object>> terminalDevIdList = null;
		if ("A".equals(regin)) {
			terminalDevIdList = resourceADao.qryterminalDevId(params);
		} else {
			terminalDevIdList = resourceBDao.qryterminalDevId(params);
		}
		if (null == terminalDevIdList) {
			return;
		}
		String status = "";
		String terminalDevId = "";
		PreparedStatement insertPs = null;
		Connection targetConn = null;
		try {
			// 遍历结果集，执行insert
			int i = 0;
			targetConn = JdbcUtils.getJdbcConn("saopaudit");
			targetConn.setAutoCommit(false);
			insertPs = targetConn
					.prepareStatement("INSERT INTO CEP_AUDIT_4G_CARD_RESOURCE VALUES(?,SYSDATE,?,?,?,?,?,?,?,?)");
			for (Map<String, Object> deviceNumberMap : terminalDevIdList) {
				if (null != deviceNumberMap.get("TERMINAL_DEV_ID")) {
					terminalDevId = deviceNumberMap.get("TERMINAL_DEV_ID").toString();
				}
				StringBuffer content = new StringBuffer();
				String splitFlag = "|#%#|";
				String iccId = "";
				String cimis = "";
				String gimis = "";
				String limis = "";
				String lanId = "";
				String areaId = "";
				List<Map<String, Object>> devNumMList = null;
				if ("A".equals(regin)) {
					devNumMList = resourceADao.qryDevNum(terminalDevId);
				} else {
					devNumMList = resourceBDao.qryDevNum(terminalDevId);
				}
				if (null == devNumMList) {
					continue;
				}
				for (Map<String, Object> devNumMap : devNumMList) {
					// 获取CIMIS
					if ("509".equals(devNumMap.get("AN_TYPE_CD").toString())) {
						if (null != devNumMap.get("DEV_NUM")) {
							cimis = devNumMap.get("DEV_NUM").toString();
						}
					}
					// 获取GIMIS
					if ("510".equals(devNumMap.get("AN_TYPE_CD").toString())) {
						if (null != devNumMap.get("DEV_NUM")) {
							gimis = devNumMap.get("DEV_NUM").toString();
						}
					}
					// 获取LIMSI
					if ("515".equals(devNumMap.get("AN_TYPE_CD").toString())) {
						if (null != devNumMap.get("DEV_NUM")) {
							limis = devNumMap.get("DEV_NUM").toString();
						}
					}
					if (StringUtils.isBlank(areaId) || StringUtils.isBlank(iccId) || StringUtils.isBlank(status)) {
						if (null != devNumMap.get("AREA_ID")) {
							areaId = devNumMap.get("AREA_ID").toString();
							lanId = CacheCodeMappingUtil.lanIdV2Map.get(areaId);
						}
						if (null != devNumMap.get("ICCID")) {
							iccId = devNumMap.get("ICCID").toString();
						}
						if (null != devNumMap.get("RSC_STATUS_CD")) {
							status = devNumMap.get("RSC_STATUS_CD").toString();
						}
					}
				}

				if (StringUtils.isBlank(cimis) || StringUtils.isBlank(limis)) {
					continue;
				}
				content.append(iccId).append(splitFlag).append(cimis).append(splitFlag).append(status).append(splitFlag)
						.append("8510000").append(splitFlag).append(lanId).append(splitFlag).append(gimis)
						.append(splitFlag).append(limis);
				insertPs.setString(1, content.toString());
				insertPs.setString(2, iccId);
				insertPs.setString(3, cimis);
				insertPs.setString(4, status);
				insertPs.setString(5, "8510000");
				insertPs.setString(6, lanId);
				insertPs.setString(7, gimis);
				insertPs.setString(8, limis);
				insertPs.setString(9, regin);
				insertPs.addBatch();
				// 设置每次提交条数，然后将批处理清除
				if (i % 1000 == 0 && insertPs != null) {
					insertPs.executeBatch();
					insertPs.clearBatch();
				}
			}
			// 批量insert
			if (insertPs != null) {
				insertPs.executeBatch();
			}
			targetConn.commit();
		} catch (SQLException e) {
			try {
				targetConn.rollback();
			} catch (SQLException e1) {
				throw new Exception("Audit - batch rollback exception:" + e1.getMessage());
			}
			throw new Exception("Auditing - batch processing anomalies:" + e.getMessage());
		} finally {
			try {
				if (insertPs != null) {
					insertPs.close();
				}
				if (targetConn != null) {
					targetConn.close();
				}
			} catch (Exception e) {
				throw new Exception(
						"Audit - batch processing closes database connection abnormality:" + e.getMessage());
			}
		}

	}
}
