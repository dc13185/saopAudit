package com.asiainfo.crm.ftp.common;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.comm.seq.impls.IdGeneratorBaseImpl;

/**
 * 周勇
 *
 */
public class JdbcTempateUtil {

	
	private static ApplicationContext ctx = null;
	private static IdGeneratorBaseImpl idg;
	
	/**
	 * 获取jdbc
	 * @return
	 */
	public static synchronized JdbcTemplate getJdbcTemplate(){
		if(ctx == null){
		    ctx = ObjectUtils.getApplicationContext();
		}
		if(idg == null){
            idg = (IdGeneratorBaseImpl) ctx.getBean("idGenerator");
		}
        return idg.getJdbcTemplate();
	}
	
	
	
	/**
	 * 设置JDBC
	 * @param dataSource
	 * @return
	 */
	public static synchronized JdbcTemplate getJdbcTemplate(DataSource dataSource){
		if(ctx == null){
		    ctx = ObjectUtils.getApplicationContext();
		}
		if(idg == null){
            idg = (IdGeneratorBaseImpl) ctx.getBean("idGenerator");
		}
        idg.setDataSource(dataSource);
        return idg.getJdbcTemplate();
	}
	
	
}
