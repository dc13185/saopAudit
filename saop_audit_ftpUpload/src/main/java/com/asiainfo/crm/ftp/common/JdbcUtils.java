package com.asiainfo.crm.ftp.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.asiainfo.angel.utils.ObjectUtils;

/**
 * jdbc工具类
 * @author chenchao 2018/3/8
 *
 */
public class JdbcUtils {

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getJdbcConn(String dataBaseName) throws Exception {
		Connection conn = null;
		ApplicationContext ctx = ObjectUtils.getApplicationContext();
		try {
			conn = ((DataSource) ctx.getBean(dataBaseName)).getConnection();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭jdbc
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	public static void closeJdbcAll(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception {
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
}
