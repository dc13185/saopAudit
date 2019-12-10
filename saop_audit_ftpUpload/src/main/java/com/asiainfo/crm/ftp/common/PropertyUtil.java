package com.asiainfo.crm.ftp.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
	private static Map<String, Properties> proMap = new HashMap<String, Properties>();
	static String[] properFileNames = {"uni-ftp-saop-sql.properties", "uni-saop-ftpinfo.properties"};

	public static void loadProps() {
		logger.info("开始加载properties文件内容.......");
		InputStream in = null;
		try {
			// 获取properties文件流
			for(String fileName: properFileNames){
				in = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
				Properties props = new Properties();
				props.load(in);
				proMap.put(fileName, props);
				logger.info("配置文件：【"+fileName+"】成功加载至内存中..."); 
			}
		} catch (FileNotFoundException e) {
			logger.error("properties文件未找到");
		} catch (IOException e) {
			logger.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("properties文件流关闭出现异常");
			}
		}
	}

	public static String getProperty(String prokey, String key) {
		 String result = "";  
	        try {  
	            Properties pros = proMap.get(prokey);  
	            result = pros.getProperty(key.toUpperCase());  
	        }catch (Exception e) {  
	            e.printStackTrace();  
	            logger.warn(  
	                    "配置文件读取失败,请查询原因: " + e.getMessage(), e);  
	        }  
	        return result;  
	}
	
	public static String getProperty(String key) {
		String value = null;
		for (Map.Entry<String, Properties> entry : proMap.entrySet()) {
			value = entry.getValue().getProperty(key.toUpperCase());
			if(StringUtils.isNotEmpty(value)){
				break;
			}
		}
		return value;
	}
}
