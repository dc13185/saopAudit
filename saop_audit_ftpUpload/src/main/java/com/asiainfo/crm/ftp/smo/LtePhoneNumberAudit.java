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

import javax.annotation.PostConstruct;

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
public class LtePhoneNumberAudit {
	private static final Logger logger = LoggerFactory.getLogger(LtePhoneNumberAudit.class);

	@Autowired
	IResourceADao resourceADao;
	
	@Autowired
	IResourceBDao resourceBDao;

	@Autowired
	ISaopFtpDao saopFtpDao;

	@Autowired
	ISaopFtpSmo saopFtpSmo;
	
	@Autowired
	CacheCodeMappingUtil cacheCodeMappingUtil;
	
	public static final long MAX_ROW_PER_TIME = 100;
	
	@PostConstruct
	public void init() {
		cacheCodeMappingUtil.loadCache();
	}
	
	public void dataExtraction() {
		// truncateTable表
		try {
			CommonUtil.truncateTable("CEP_AUDIT_4G_PHONE_NUMBER");
			logger.debug("truncate table cep_audit_4g_phone_number successfully !");
		} catch (Exception e1) {
			logger.error("truncateTable cep_audit_4g_phone_number exception:" + e1.getMessage());
		}
//		logger.debug("Region A start writing the table cep_audit_4g_phone_number !");
//		phoneResource("A");
//		logger.debug("Region A writing table cep_audit_4g_phone_number successfully !");
//		
//		logger.debug("Region B start writing the table cep_audit_4g_phone_number !");
//		phoneResource("B");
//		logger.debug("Region B writing table cep_audit_4g_phone_number successfully !");
		ExecutorService executor = Executors.newFixedThreadPool(2);
		CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
		for (int j = 0; j < 2; j++) {
			final int i = j;
			cs.submit(new Callable<String>() {
				public String call() throws Exception {
					if(i == 0){
						phoneResource("A");
						return "A";
					}
					if(i == 1){
						phoneResource("B");
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
		logger.debug("writing table cep_audit_4g_phone_number successfully !");
		// 调用ftp上传逻辑
		saopFtpSmo.execute("BUS60019");
		logger.debug("upload table cep_audit_4g_phone_number successfully !");
	}

	private void phoneResource(final String regin) {
		int count = 0;
		if("A".equals(regin)){
			count = resourceADao.qryPhoneNumberCount();
		}else{
			count = resourceBDao.qryPhoneNumberCount();
		}
		logger.debug("Total is:" + count);
		if (count == 0) {
			return;
		}
		if (count <= MAX_ROW_PER_TIME) {
			try {
				saveData(1, count, regin);
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
						saveData(start, end, regin);
						return regin + ":" + start + "--" + end;
					}
				});
			}
			executor.shutdown();
			// 等待所有子线程完成
			for (int j = 0; j < times; j++) {
				String result = "";
				try {
					result = cs.take().get();
					logger.debug(result + ",processing completed!");
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug(result + ",processing failed:" + e.getMessage());
				}
			}
		}
	}

	private void saveData(int start, int end, String regin) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		List<Map<String, Object>> phoneNumberList = null;
		if("A".equals(regin)){
			phoneNumberList = resourceADao.qryPhoneNumber(params);
		}else{
			phoneNumberList = resourceBDao.qryPhoneNumber(params);
		}
		if(null == phoneNumberList){
			return;
		}
		PreparedStatement insertPs = null;
		Connection targetConn = null;
		try {
			targetConn = JdbcUtils.getJdbcConn("saopaudit");
			targetConn.setAutoCommit(false);
			insertPs = targetConn
					.prepareStatement("INSERT INTO CEP_AUDIT_4G_PHONE_NUMBER VALUES(?,SYSDATE,?,?,?,?,?,?)");
			// 遍历结果集，执行insert
			int i = 0;
			for (Map<String, Object> cardRes : phoneNumberList) {
				StringBuffer content = new StringBuffer();
				String splitFlag = "|#%#|";
				String areaId = cardRes.get("AREA_ID").toString();
				String lanId = CacheCodeMappingUtil.lanIdV2Map.get(areaId);
				String zoneNbr = cardRes.get("ZONE_NUMBER").toString();
				content.append(cardRes.get("PHONE_NUMBER")).append(splitFlag).append(cardRes.get("STATUS"))
						.append(splitFlag).append("8510000").append(splitFlag).append(lanId).append(splitFlag)
						.append(zoneNbr);
				insertPs.setString(1, content.toString());
				insertPs.setString(2, cardRes.get("PHONE_NUMBER").toString());
				insertPs.setString(3, cardRes.get("STATUS").toString());
				insertPs.setString(4, "8510000");
				insertPs.setString(5, lanId);
				insertPs.setString(6, zoneNbr);
				insertPs.setString(7, regin);
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
				throw new Exception("Audit - batch processing closes database connection abnormality:" + e.getMessage());
			}
		}
	}
}
