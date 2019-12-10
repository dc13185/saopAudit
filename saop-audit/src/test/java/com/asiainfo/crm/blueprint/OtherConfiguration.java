package com.asiainfo.crm.blueprint;

import java.io.IOException;

import org.springframework.context.annotation.Bean;

import com.al.crm.nosql.cache.ICache;

/**
 * 
 * @author shish
 *
 */
public class OtherConfiguration {
	
	@Bean(name="myRedis")
	public ICache getICache() throws IOException{
		return null;
//		return CacheFactory.getInstance().getCache("al.properties");
	}
}
