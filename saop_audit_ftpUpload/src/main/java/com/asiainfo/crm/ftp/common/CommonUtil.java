package com.asiainfo.crm.ftp.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.crm.ftp.smo.SaopFtpSmoImpl;

public class CommonUtil {

	private static final Logger logger = LoggerFactory.getLogger(SaopFtpSmoImpl.class);
	/**
	 * @param str 要查找的字符串
	 * @param pattern 匹配的字符串
	 * @param fromIndex,倒数第几次出现
	 * @return
	 */
	public static int lastIndexOf(String str, String pattern, int fromIndex) {
		int index = -1;
		if (str.indexOf(pattern) == -1) {
			return -1;
		}
		if(fromIndex == 0 ){
			return str.length();
		}
		else if (fromIndex == 1) {
			index = str.lastIndexOf(pattern);
		} else {
			index = str.lastIndexOf(pattern);
			for (int i = 1; i < fromIndex; i++) {
				index = str.lastIndexOf(pattern, index - 1);
				if (index == -1) {
					return -1;
				}
			}
		}
		return index;
	}
	
	public static int parseInt(String str){
		int r = 0;
		try{
			r = Integer.parseInt(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 自定义分割字符串的函数，因为有些分隔符用JDK自带的分割方法有问题
	 * @param src
	 * @param split
	 * @return
	 */
	public static String[] split(String src,String split){
		String[] arr = null;
		StringTokenizer t = new StringTokenizer(src, split);
		arr = new String[t.countTokens()];
		int i = 0;
		while (t.hasMoreElements()) {
			arr[i] = t.nextElement().toString();
			i++;
		}
		return arr;
	}
	
	//关闭jdbc资源
	public static void closeConntion(Connection sourceConn, Connection targetConn) {
		try{
			if(null != sourceConn){
				sourceConn.close();
			}
			if(null != targetConn){
				targetConn.close();
			}
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}
	
	public static void truncateTable(String tableName) throws Exception {
		PreparedStatement delPs = null;
		Connection conn = null;
		String deleteSql = "";
		try {
			conn = JdbcUtils.getJdbcConn("saopaudit");
			deleteSql = "TRUNCATE TABLE " + tableName;
			logger.debug("truncate table sql：{}", deleteSql);
			delPs = conn.prepareStatement(deleteSql);
			delPs.execute();
		} catch (Exception e) {
			throw new Exception("truncate table异常-{}" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (delPs != null) {
					delPs.close();
				}
			} catch (Exception e) {
				throw new Exception("关闭连接异常-{}" + e.getMessage());
			}
		}

	}
}
