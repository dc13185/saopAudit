package com.asiainfo.crm.ftp.smo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.ftp.dao.ISaopFtpDao;

@Component
public class CacheCodeMappingUtil {

	@Autowired
	ISaopFtpDao saopFtpDao;
	
	public static Map<String, String> lanIdV2Map = new HashMap<String, String>();

	public void loadCache() {
		// 加载
		List<Map<String, String>> lanIdV2List = saopFtpDao.qryLandId();
		if (lanIdV2List != null) {
			for (Map<String, String> map : lanIdV2List) {
				// 11000转 8511100
				lanIdV2Map.put(map.get("P_CODE"), map.get("H_CODE"));
			}
		}
	}
}
