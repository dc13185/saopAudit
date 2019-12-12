package com.asiainfo.crm.ftp.smo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.crm.ftp.common.CommonConstants;
import com.asiainfo.crm.ftp.common.CommonUtil;
import com.asiainfo.crm.ftp.common.DateUtil;
import com.asiainfo.crm.ftp.common.FtpUtil;
import com.asiainfo.crm.ftp.common.PropertyUtil;
import com.asiainfo.crm.ftp.common.RegxUtil;
import com.asiainfo.crm.ftp.dao.ISaopFtpDao;

@Service("saopFtpSmo")
public class SaopFtpSmoImpl implements ISaopFtpSmo {

	private static final Logger logger = LoggerFactory.getLogger(SaopFtpSmoImpl.class);

	// 配置全量稽核有哪些，全量稽核在做上传时对应的稽核项不在做数据抽取
	public final static HashMap<String, String> WHOLE_AUDIT_BEAN_NAME_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			// 产品月稽核
			//put("BUS60025", "depNewAudits4G");
			// 销售品月稽核
			//put("BUS60027", "depNewAudits4G");
			// 一点收费全量稽核
			put("BUS23001", "ydsfAudit");
			// 国际漫游
			put("BUS36001", "cgInternationalRoamAudit");
			// 政企稽核
			put("BUS50004", "governmentEnterpriseAudit");
			// c网全量稽核
			put("BUS24001", "cProdAudit");
			// IVPN全量稽核
			put("BUS23001_1", "ivpnAudit");
			// 带宽型全量稽核
			put("BUS35000", "bandwidthAddAudit");
			// 终端补贴串码信息定期上传
			put("BUS16108", "terminalSubsidyAllAudit");
		}
	};

	@Autowired
	private ISaopFtpDao saopFtpDao;

	// 配置文件中的SQL入参分隔符
	private static final String PARAM_SPLIT = ";";

	private static final String THREAD_SWITCH_1 = "1";

	private static final String THREAD_SWITCH_N = "N";

	@PostConstruct
	public void init() {
		PropertyUtil.loadProps();
	}

	/**
	 * ftp上传入口
	 * 
	 * @param busCode
	 */
	@Override
	public void execute(String busCode) {
		// 根据配置的规则生成文件名
		String fileName = RegxUtil.getFileNameByBuscode(busCode, 1);
		if (fileName.equals("")) {
			logger.debug("获取文件名失败");
		}
		try {
			//省crm受理集团卡信息，先转储imis为空的数据
			if("BUS80009".equals(busCode)){
				saopFtpDao.transCardInfo2His();
				saopFtpDao.deleteCardInfo();
			}
			createAndUploadFile2(busCode, fileName);
			// 上传成功后转储
			transferHistory(busCode);
		} catch (Exception e) {
			logger.error("创建并上传文件失败：", e);
			e.printStackTrace();
		}
	}

	/**
	 * 生成文件并上传
	 * 
	 * @param localFilePath
	 *            文件物理路径
	 * @throws InterruptedException
	 * @throws DAOException
	 */
	public void createAndUploadFile2(final String busCode, String fileAbsoutePath) throws Exception {
		String fileName = "";
		String files[] = null;
		String reSendTime = "000";// 默认
		String seqNum = PropertyUtil.getProperty("SEQ_" + busCode);
		String state = "";
		String errorMsg = "";
		FtpUtil ftpClient = null;
		String threadSwitch = PropertyUtil.getProperty("THREAD_SWITCH");
		// 读取配置文件的全量稽核接口编码
		String wholeAuditBusCode = PropertyUtil.getProperty("WHOLE_AUDIT_BUSCODE");

		Map<String, String> params = new HashMap<String, String>();
		params.put("busCode", busCode);
		params.put("platCode", PropertyUtil.getProperty("PLATCODE_" + busCode));
		params.put("fileDate", DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT_A));
		params.put("reSendTime", reSendTime);
		if (PropertyUtil.getProperty("FTP_SERVER_IP_" + busCode) == null
				|| PropertyUtil.getProperty("FTP_SERVER_PORT_" + busCode) == null
				|| PropertyUtil.getProperty("FTP_USER_NAME_" + busCode) == null
				|| PropertyUtil.getProperty("FTP_PWD_" + busCode) == null
				|| PropertyUtil.getProperty("FTPPATH_" + busCode) == null) {
			throw new Exception("请在uni-saop-ftpinfo.properties文件中配置" + busCode + "FTP相关参数");
		}
		try {
			try {
				String wholeAuditbeanName = "";
				Map<String, String> switchParams = new HashMap<String, String>();
				// 判断是否全量稽核接口编码。如果是，则在ftp上传是，对应稽核项停止抽取抽数，ftp上传结束后即可继续抽数
				if (wholeAuditBusCode.contains(busCode)) {
					wholeAuditbeanName = WHOLE_AUDIT_BEAN_NAME_MAP.get(busCode);
					switchParams.put("wholeAuditbeanName", wholeAuditbeanName);
					switchParams.put("status", "1");
					// 设置稽核抽数关闭
					if (StringUtils.isNotBlank(saopFtpDao.queryAuditSwitch(switchParams))) {
						saopFtpDao.updateAuditSwitch(switchParams);
					} else {
						saopFtpDao.insertAuditSwitch(switchParams);
					}
				}
				// 通过配置文件配置的参数，选择是单线程还是多线程（全量稽核数据量大）
				if (THREAD_SWITCH_1.equals(threadSwitch)) {
					// 单线程
					files = this.writeFiles(busCode);
				} else if (THREAD_SWITCH_N.equals(threadSwitch)) {
					// 多线程
					files = this.multiWriteFiles(busCode);
				}
				// 全量稽核上传结束后，对应的稽核项继续数据抽取
				if (wholeAuditBusCode.contains(busCode)) {
					switchParams.put("status", "0");
					// 设置稽核抽数开启
					saopFtpDao.updateAuditSwitch(switchParams);
					/*
					 * 由于全量稽核文件在上传过程中，会停止对应的稽核项解析入表，此时侦听表会处理失败，所以在文件上传成功后，
					 * 将稽核侦听表的F状态改成D状态，重新处理一次。
					 */
					saopFtpDao.updateListenAuditFlag();
				}
				state = CommonConstants.SUCCESS_STATE;
				for (int i = 0; i < files.length; i++) {
					fileName = files[i];
					params.put("seqNum", fileName.substring(fileName.indexOf(".") - 3, fileName.indexOf(".")));
					params.put("fileName", fileName);
					params.put("createState", state);
					params.put("createDesc", "SUCCESS");
					this.saopFtpDao.insertLog(params);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				params.put("seqNum", seqNum);
				params.put("fileName", fileName);
				params.put("createState", CommonConstants.FAIL_STATE);
				errorMsg = e.getMessage();
				if (errorMsg.length() >= 1000) {
					errorMsg = errorMsg.substring(0, 999);
				}
				params.put("createDesc", errorMsg);
				this.saopFtpDao.insertLog(params);
			}
			if (state.equals(CommonConstants.SUCCESS_STATE)) {
				String ftpErrorMsg = "";
				String folder = PropertyUtil.getProperty("localUploadPath");// 文件保存路径
				int bufferSize = 1024;// 默认
				String bufferSizeStr = PropertyUtil.getProperty("BUFFER_SIZE_" + busCode);
				if(StringUtils.isNotBlank(bufferSizeStr)){
					String[] bufferSizeStrArray = bufferSizeStr.split("\\*");
					for (int i = 0; i < bufferSizeStrArray.length; i++) {
						if(i == 0){
							bufferSize = 1 * Integer.valueOf(bufferSizeStrArray[i]);
						}else{
							bufferSize = bufferSize * Integer.valueOf(bufferSizeStrArray[i]);
						}
					} 
				}
				System.out.println("----------------ftpClient开始初始化-------------------");
				ftpClient = new FtpUtil(PropertyUtil.getProperty("FTP_SERVER_IP_" + busCode),
						PropertyUtil.getProperty("FTP_USER_NAME_" + busCode),
						PropertyUtil.getProperty("FTP_PWD_" + busCode),
						Integer.valueOf(PropertyUtil.getProperty("FTP_SERVER_PORT_" + busCode)), bufferSize);
				for (int i = 0; i < files.length; i++) {
					boolean isUploadSuccess = false;
					fileName = files[i];
					try {
						isUploadSuccess = ftpClient.uploadFileToFtpServ(folder,
								PropertyUtil.getProperty("FTPPATH_" + busCode), fileName);
						params.put("seqNum", fileName.substring(fileName.indexOf(".") - 3, fileName.indexOf(".")));
						params.put("fileName", fileName);
						params.put("sendState", CommonConstants.SUCCESS_STATE);
						params.put("sendDesc", "SUCCESS");
						this.saopFtpDao.updateLog(params);
					} catch (Exception e) {
						logger.debug("上传文件异常", e);
						ftpErrorMsg = e.getMessage();
						if (ftpErrorMsg.length() >= 1000) {
							ftpErrorMsg = ftpErrorMsg.substring(0, 999);
						}

						params.put("seqNum", fileName.substring(fileName.indexOf(".") - 3, fileName.indexOf(".")));
						params.put("sendState", CommonConstants.FAIL_STATE);
						params.put("sendDesc", ftpErrorMsg);
						this.saopFtpDao.updateLog(params);
						throw new Exception(e);
					}
					// ftp上传成功后写日志表，供监控使用
					params.put("busCode", busCode);
					params.put("platCode", PropertyUtil.getProperty("PLATCODE_" + busCode));
					params.put("fileDate", DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT_A));
					params.put("sendState", CommonConstants.ALL_SUCCESS_STATE);
					params.put("sendDesc", "稽核文件全部上传成功");
					this.saopFtpDao.insertLog(params);
					// 上传成功后移动到备份文件件下
					if (isUploadSuccess) {
						// 新路径
						String newPath = PropertyUtil.getProperty("backPath") + fileName;
						moveFile(folder + fileName, newPath);
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new Exception(e);
		} finally {
			try {
				if (ftpClient != null) {
					FtpUtil.closeFtpConnection(ftpClient);
				}
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}
	
	/**
	 * 当记录太多时，需要生产几个文件
	 * 
	 * @param sql
	 * @param path
	 * @param tableName
	 * @return
	 * @throws BMOException
	 */
	public String[] writeFiles(String busCode) throws Exception {
		String sql = PropertyUtil.getProperty("SQL_DATA_INIT_" + busCode);
		logger.debug("查询sql:" + sql);
		if (sql == null || sql.equals("")) {
			throw new Exception("SQL_DATA_INIT_" + busCode + "对应的sql不存在");
		}
		String[] files = null;
		long rownum = getCount(busCode);
		logger.debug(
				"数量总数=" + rownum + "   CommonConstants.MAX_ROWNUM_PRE_FILE=" + CommonConstants.MAX_ROWNUM_PRE_FILE);
		if (rownum >= CommonConstants.MAX_ROWNUM_PRE_FILE) {
			Long fileCount = (rownum - 1 + CommonConstants.MAX_ROWNUM_PRE_FILE) / CommonConstants.MAX_ROWNUM_PRE_FILE; // 计算要分割成多少个文件
			long leftCount = (rownum) % CommonConstants.MAX_ROWNUM_PRE_FILE;// 最后一个文件中的记录数
			if (leftCount == 0) {
				leftCount = CommonConstants.MAX_ROWNUM_PRE_FILE;
			} // 如果恰好为0，则说明正好相等
			files = new String[fileCount.intValue()];
			for (int i = 0; i < fileCount; i++) {
				String fileName = "";
				if (i == (fileCount - 1)) {// 最后一次循环，rownum可能不足maxCount
					fileName = writeFile(sql, busCode, (i + 1), leftCount);
				} else {
					fileName = writeFile(sql, busCode, (i + 1), CommonConstants.MAX_ROWNUM_PRE_FILE);
				}
				files[i] = fileName;
				logger.debug("生产文件：" + fileName);
			}
		} else {
			files = new String[1];
			logger.debug("表示记录数没有超过限制，只生成一个文件");
			String fileName = writeFile(sql, busCode, 1, rownum);// 1表示记录数没有超过限制，只生成一个文件
			files[0] = fileName;
		}
		return files;
	}

	/**
	 * 写文件 用来计算数据总量以及文件分割情况
	 * 
	 * @param busCode
	 * @return
	 * @throws SQLException
	 */
	public long getCount(String busCode) throws SQLException {
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		// SQL的参数，可空属性
		String sqlParamStr = PropertyUtil.getProperty("SQL_DATA_PARAM_" + busCode);
		String sql = PropertyUtil.getProperty("SQL_DATA_CNT_" + busCode);
		String[] param = null;
		if (sqlParamStr != null && !"".equals(sqlParamStr)) {
			param = sqlParamStr.split(PARAM_SPLIT);// 入参分割
		}
		conn = getJdbcConn();
		ps = conn.prepareStatement(sql);
		if (param != null) {// where的条件
			for (int k = 1; k <= param.length; k++) {
				// 动态设置条件
				ps.setString(k, param[k - 1]);
			}
		}
		rs = ps.executeQuery();
		rs.next();
		long count = rs.getLong(1);
		closeJdbcAll(conn, ps, rs);
		return count;
	}

	/**
	 * 关闭jdbc
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	private void closeJdbcAll(Connection conn, java.sql.PreparedStatement ps, java.sql.ResultSet rs)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 获取saop_audit数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getJdbcConn() throws SQLException {
		Connection conn;
		ApplicationContext ctx = ObjectUtils.getApplicationContext();
		conn = ((DataSource) ctx.getBean("saopaudit")).getConnection();
		return conn;
	}

	public String writeFile(String sql, String buscode, int fileIndex, long rownum) throws Exception {
		logger.debug("开始生成文件");
		String path = PropertyUtil.getProperty("localUploadPath") + this.getFileNameBySeq(buscode, fileIndex);
		File f = new File(PropertyUtil.getProperty("localUploadPath"));
		if (!f.exists()) {
			f.mkdir();
		}
		FileOutputStream fos = null;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		java.sql.ResultSetMetaData rsm = null;
		java.sql.PreparedStatement updatePs = null;
		String tempStr = "";
		// 列分隔符 比如|#| |#%|，每个业务的分隔符可能不一样
		String columnSplit = PropertyUtil.getProperty("COLUMN_SPLIT_" + buscode);
		String br = PropertyUtil.getProperty("HHF_" + buscode);
		String tablename = PropertyUtil.getProperty("TABLE_NAME_" + buscode);
		// SQL的参数，可空属性
		String sqlParamStr = PropertyUtil.getProperty("SQL_DATA_PARAM_" + buscode);
		String updateSql = PropertyUtil.getProperty("UPDATE_SQL_" + buscode);
		String[] param = null;
		if (sqlParamStr != null && !"".equals(sqlParamStr)) {
			param = sqlParamStr.split(PARAM_SPLIT);// 入参分割
		}
		if (tablename == null || "".equals(tablename)) {
			throw new Exception("TABLE_NAME必须要配置");
		}
		if (br == null || br.equals("0")) {
			br = "\r\n";
		} else {
			br = "\n";
		}
		if (columnSplit == null) {
			columnSplit = CommonConstants.column_split;
		}
		long times = (rownum + CommonConstants.MAX_ROW_PER_TIME - 1) / CommonConstants.MAX_ROW_PER_TIME;// 每次处理10000条记录，看需要循环几次
		try {
			fos = new FileOutputStream(path);
			if (!buscode.equals("BUS23001") || !buscode.equals("BUS23001_1") 
					|| !buscode.equals("BUS16102") || !buscode.equals("BUS16108")) {// 一点收费稽核、IVPN全量稽核没有写行数
				fos.write((String.valueOf(rownum) + br).getBytes());// 写行数
			}
			if (PropertyUtil.getProperty("COLUMN_" + buscode) != null) {
				fos.write((PropertyUtil.getProperty("COLUMN_" + buscode) + br).getBytes());
			}

			conn = getJdbcConn();

			if (StringUtils.isNotBlank(updateSql)) {
				updatePs = conn.prepareStatement(updateSql);
			}

			for (int j = 0; j < times; j++) {
				ps = conn.prepareStatement(sql);
				if (param != null) {// where的条件
					for (int k = 1; k <= param.length; k++) {
						// 动态设置条件
						ps.setString(k, param[k - 1]);
					}
				}
				rs = ps.executeQuery();
				rsm = ps.getMetaData();
				int columnCount = rsm.getColumnCount();
				byte[] b = null;
				while (rs.next()) {
					// 取出所有列
					for (int i = 1; i < columnCount + 1; i++) {
						// 第一列rowid
						if (i == 1) {
							continue;
						}
						if (i == columnCount) {
							tempStr += rs.getString(i);// 最后一列不用加分隔符
						} else {
							tempStr += rs.getString(i) + columnSplit;
						}
						tempStr = tempStr.replaceAll("null|\\r|\\n", "");
					}
					b = (tempStr + CommonConstants.BR).getBytes("UTF-8");
					fos.write(b);// 将一列数据填入
					tempStr = "";
					fos.flush();
					if (updatePs != null) {
						updatePs.setString(1, rs.getString(1));
						updatePs.addBatch();
					}
				}
				if (updatePs != null) {
					updatePs.executeBatch();
				}
			}
			if (updatePs != null) {
				updatePs.executeBatch();
			}
		} catch (IOException e) {
			logger.error("创建文件失败", e);
			throw new Exception("IO创建文件失败" + e.getMessage());
		} catch (SQLException e) {
			logger.error("数据库操作异常", e);
			throw new Exception("数据库操作异常" + e.getMessage());
		} finally {
			try {
				closeJdbcAll(fos, conn, ps, rs, updatePs);
			} catch (IOException e) {
				logger.error("关闭IO失败", e);
				throw new Exception("关闭IO失败" + e.getMessage());
			} catch (SQLException e) {
				logger.error("关闭数据库失败", e);
				throw new Exception("关闭数据库失败" + e.getMessage());
			}
		}
		return this.getFileNameBySeq(buscode, fileIndex);
	}

	// 多线程处理
	public String[] multiWriteFiles(final String busCode) throws Exception {
		System.out.println("------------------多线程开始处理-------------------");
		String tableName = PropertyUtil.getProperty("TABLE_NAME_" + busCode);
		if(isFullTable(busCode)){
			// truncate 全量稽核记录快照表
			CommonUtil.truncateTable(tableName + "_snapshot");
		}
		final String sql = PropertyUtil.getProperty("SQL_DATA_INIT_" + busCode);
		final int maxRowPerFile = Integer.valueOf(PropertyUtil.getProperty("MAXROWPERFILE_" + busCode));
		logger.debug("查询sql:" + sql);
		// 默认多线程文件生成线程数目
		int creatFileThreadCount = Integer.valueOf(PropertyUtil.getProperty("FILE_THREAD_COUNT"));
		if (sql == null || sql.equals("")) {
			throw new Exception("SQL_DATA_INIT_" + busCode + "对应的sql不存在");
		}
		String[] files = null;
		long rownum = getCount(busCode);
		logger.debug("数量总数=" + rownum + "   每个文件最大行数=" + maxRowPerFile);
		if (rownum >= maxRowPerFile) {
			final Long fileCount = (rownum - 1 + maxRowPerFile) / maxRowPerFile; // 计算要分割成多少个文件
			final long leftCount = (rownum) % maxRowPerFile;// 最后一个文件中的记录数
			files = new String[fileCount.intValue()];
			/*
			 * creatFileThreadCount为设置的线程数。 如果fileCount（要生成的文件数目）
			 * 大于creatFileThreadCount则线程数为creatFileThreadCount；
			 * 如果fileCount（要生成的文件数目）小于creatFileThreadCount则线程数为fileCount；
			 */
			creatFileThreadCount = (int) (creatFileThreadCount < fileCount ? creatFileThreadCount : fileCount);
			// 如果fileCount等于0，则将线程数设置为1
			creatFileThreadCount = creatFileThreadCount == 0 ? 1 : creatFileThreadCount;
			logger.debug("文件生成线程数目:" + creatFileThreadCount);
			ExecutorService executor = Executors.newFixedThreadPool(creatFileThreadCount);
			CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
			for (int j = 0; j < fileCount; j++) {
				final int i = j;
				cs.submit(new Callable<String>() {
					String fileName;

					public String call() throws Exception {
						if (i == (fileCount - 1)) {
							// 最后一次循环，rownum可能不足maxCount
							fileName = multiThreadWriteFile(sql, busCode, i,
									leftCount == 0 ? maxRowPerFile : leftCount);
						} else {
							fileName = multiThreadWriteFile(sql, busCode, i, maxRowPerFile);
						}
						return fileName;
					}
				});
			}
			executor.shutdown();
			// 等待所有子线程完成
			for (int i = 0; i < fileCount; i++) {
				try {
					String fileName = cs.take().get();
					files[i] = fileName;
					logger.debug(fileName + "，处理完成");
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("处理失败：" + e.getMessage());
				}
			}
		} else {
			files = new String[1];
			logger.debug("表示记录数没有超过限制，只生成一个文件");
			String fileName = multiThreadWriteFile(sql, busCode, 0, rownum);// 1表示记录数没有超过限制，只生成一个文件
			files[0] = fileName;
		}
		return files;
	}

	// 多线程处理
	public String multiThreadWriteFile(final String sql, final String busCode, final int fileIndex, final long rownum)
			throws Exception {
		logger.debug("开始生成文件");
		File f = new File(PropertyUtil.getProperty("localUploadPath"));
		if (!f.exists()) {
			f.mkdir();
		}
		int maxRowPerTime = Integer.valueOf(PropertyUtil.getProperty("MAXROWPERTIME_" + busCode));
		long times = (rownum + maxRowPerTime - 1) / maxRowPerTime;// 每次处理maxRowPerTime条记录，看需要循环几次
		int threadCount = (int) times;
		threadCount = threadCount == 0 ? 1 : threadCount;
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
		String path = PropertyUtil.getProperty("localUploadPath") + this.getFileNameBySeq(busCode, fileIndex + 1);
		String br = "\r\n";
		final FileOutputStream fos = new FileOutputStream(path);
		// 一点收费稽核、C网稽核、终端补贴日增上传没有写行数
		if (!busCode.equals("BUS23001") && !busCode.equals("BUS24001") && !busCode.equals("BUS16102")) {
			fos.write((String.valueOf(rownum) + br).getBytes());// 写行数
		}
		if (PropertyUtil.getProperty("COLUMN_" + busCode) != null) {
			fos.write((PropertyUtil.getProperty("COLUMN_" + busCode) + br).getBytes());
		}
		for (int j = 0; j < threadCount; j++) {
			final int i = j;
			cs.submit(new Callable<String>() {
				public String call() throws Exception {
					return writeLocalFile(sql, busCode, fileIndex, rownum, i, fos);
				}

			});
		}
		executor.shutdown();

		// 等待所有子线程完成
		for (int i = 0; i < threadCount; i++) {
			try {
				String info = cs.take().get();
				logger.debug("SQL:" + info + "处理成功！");
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("处理失败：" + e.getMessage());
			}
		}

		fos.flush();
		closeJdbcAll(fos, null, null, null, null);
		return this.getFileNameBySeq(busCode, fileIndex + 1);
	}
	
	//判断是否是全量表
	private boolean isFullTable(String busCode) {
		if(StringUtils.isNotBlank(WHOLE_AUDIT_BEAN_NAME_MAP.get(busCode))){
			return true;
		}
		return false;
	}

	// 全量稽核记录快照表
	private void saveFullAuditSnapshot(String sql, String busCode) throws Exception {
		sql = PropertyUtil.getProperty("SNAPSHOT_" + busCode) + sql;
		sql = sql.replace("ROWID AS RID,", "").replace("RID,", "");
		PreparedStatement ps = null;
		Connection conn = getJdbcConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			logger.error("创建文件失败", e);
			throw new Exception("IO创建文件失败" + e.getMessage());
		} finally {
			try {
				closeJdbcAll(null, conn, ps, null, null);
			} catch (IOException e) {
				logger.error("关闭IO失败", e);
				throw new Exception("关闭IO失败" + e.getMessage());
			} catch (SQLException e) {
				logger.error("关闭数据库失败", e);
				throw new Exception("关闭数据库失败" + e.getMessage());
			}
		}
		
	}
	
	// 多线程处理
	private String writeLocalFile(String sql, String busCode, int fileIndex, long rownum, int threadCount,
			FileOutputStream fos) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;
		PreparedStatement updatePs = null;
		String tempStr = "";
		String updateSql = PropertyUtil.getProperty("UPDATE_SQL_" + busCode);
		// 列分隔符 比如|#| |#%|，每个业务的分隔符可能不一样
		String columnSplit = PropertyUtil.getProperty("COLUMN_SPLIT_" + busCode);
		// 最大行数
		Long maxLowTime = Long.valueOf(PropertyUtil.getProperty("MAXROWPERTIME_" + busCode));
		Long maxLowFile = Long.valueOf(PropertyUtil.getProperty("MAXROWPERFILE_" + busCode));
		// 拼接字符串
		Long startRowNum = threadCount * maxLowTime + fileIndex * maxLowFile + 1;
		Long endRowNum = (threadCount + 1) * maxLowTime + fileIndex * maxLowFile;
		String sqlTemp = sql.replace("ROWID", "ROWID AS RID").replace("FROM", ",ROWNUM AS NUM FROM");
		sql = sql.replace("ROWID", "RID").substring(0, sql.indexOf("FROM") + 2);
		if(sql.contains("TO_CHAR") || sql.contains("TO_DATE")){
			sql = sql.replaceAll("TO_CHAR\\(|TO_DATE\\(|,'[^,]*'\\)", "");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sql).append("(").append(sqlTemp).append(")").append("WHERE NUM BETWEEN ").append(startRowNum)
				.append(" AND ").append(endRowNum);
		sql = sb.toString();
		if(isFullTable(busCode)){
			// 全量稽核记录快照表
			saveFullAuditSnapshot(sql, busCode);
		}
		// 国际漫游截取手机号码前的“86”
		if("BUS36001".equals(busCode)){
			sql = sql.replaceFirst("MDN", "decode(length(MDN),13,substr(MDN,3,13),MDN)");
		}
		final Connection conn = getJdbcConn();
		logger.debug("查询的sql：" + sql);
		if (columnSplit == null) {
			columnSplit = CommonConstants.column_split;
		}
		if (StringUtils.isNotBlank(updateSql)) {
			updatePs = conn.prepareStatement(updateSql);
		}

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rsm = ps.getMetaData();
			int columnCount = rsm.getColumnCount();
			byte[] b = null;
			while (rs.next()) {
				// 取出所有列
				for (int i = 1; i < columnCount + 1; i++) {
					// 第一列rowid
					if (i == 1) {
						continue;
					}
					if (i == columnCount) {
						tempStr += rs.getString(i);// 最后一列不用加分隔符
					} else {
						tempStr += rs.getString(i) + columnSplit;
					}
					tempStr = tempStr.replaceAll("null|\\r|\\n", "");
				}
				b = (tempStr + CommonConstants.BR).getBytes("UTF-8");
				fos.write(b);// 将一列数据填入
				tempStr = "";
				if (updatePs != null) {
					updatePs.setString(1, rs.getString(1));
					updatePs.addBatch();
				}
			}
			if (updatePs != null) {
				updatePs.executeBatch();
			}
			return sql;
		} catch (Exception e) {
			logger.error("创建文件失败", e);
			throw new Exception("IO创建文件失败" + e.getMessage());
		} finally {
			try {
				closeJdbcAll(null, conn, ps, rs, updatePs);
			} catch (IOException e) {
				logger.error("关闭IO失败", e);
				throw new Exception("关闭IO失败" + e.getMessage());
			} catch (SQLException e) {
				logger.error("关闭数据库失败", e);
				throw new Exception("关闭数据库失败" + e.getMessage());
			}
		}
	}
	
	/**
	 * 关闭打开的资源
	 * 
	 * @param fos
	 * @param conn
	 * @param ps
	 * @param rs
	 * @param updatePs
	 * @throws SQLException
	 * @throws IOException
	 */
	private void closeJdbcAll(FileOutputStream fos, Connection conn, java.sql.PreparedStatement ps,
			java.sql.ResultSet rs, java.sql.PreparedStatement updatePs) throws SQLException, IOException {
		if (updatePs != null) {
			updatePs.close();
		}
		if (rs != null) {
			rs.close();
		}

		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
		if (fos != null) {
			fos.close();
		}
	}

	/**
	 * 删除本地文件
	 * 
	 * @param fs
	 */
	public void delLocalFile(String[] fs) {
		for (int i = 0; i < fs.length; i++) {
			File file = new File(fs[i]);
			logger.debug("delete file" + fs[i]);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * seq默认为001，如果文件很大的话，每天会生成几个文件，最后的序列号不同，比如20110903U001.txt,20110903U002.txt
	 * 
	 * @param seq
	 * @return
	 */
	public String getFileNameBySeq(String buscode, int seq) {
		logger.debug("create file name");
		String fileName = "";
		fileName = RegxUtil.getFileNameByBuscode(buscode, seq);
		return fileName;
	}

	/**
	 * 将上传成功的文件归档到文件夹中
	 * 
	 * @param 原文件地址
	 * @param 新文件地址
	 */
	public static void moveFile(String oldPath, String newPath) {
		File f = new File(oldPath);
		File newFile = new File(newPath);
		File parent = newFile.getParentFile();
		if (!parent.exists()) {
			parent.mkdir();
		}
		f.renameTo(newFile);
	}

	/**
	 * 数据转储
	 * 
	 * @param busCode
	 */
	private void transferHistory(String busCode) {
		// 文件上传后转储
		String transferHisSql = PropertyUtil.getProperty("TRANSFER_HISTORY_" + busCode);
		String deleteSql = PropertyUtil.getProperty("DELETE_SQL_" + busCode);
		if (StringUtils.isNotEmpty(transferHisSql) && StringUtils.isNotEmpty(deleteSql)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("transforSql", transferHisSql);
			params.put("deleteSql", deleteSql);
			//saopFtpDao.insertHistory(params);
			executeUpdateSql(transferHisSql);
			saopFtpDao.deleteSql(params);
		}
	}





	/**
	 * 执行insert或update
	 * @param conn
	 * @param sql
	 * @param params
	 */
	public  void executeUpdateSql(String sql){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJdbcConn();
			ps = conn.prepareStatement(sql);
			int result = ps.executeUpdate();
			logger.info("save databus result = "+result);
		} catch (SQLException e) {
			logger.error("执行SQL【"+sql+"】异常",e);
		} finally {
			try {
				closeJdbcAll(conn,ps,null);
			} catch (SQLException e) {
				logger.error("关闭数据库连接异常",e);
				e.printStackTrace();
			}
		}
	}

}
