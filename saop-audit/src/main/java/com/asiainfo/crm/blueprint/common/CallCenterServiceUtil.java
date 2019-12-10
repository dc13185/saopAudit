package com.asiainfo.crm.blueprint.common;


import com.alibaba.fastjson.JSONObject;

/**
 * 调用中心服务工具类
 * @author chenchao
 *
 */
public class CallCenterServiceUtil {

	private static String DEFAULE_CHARSET = "UTF-8";
	private static int DEFAULT_OUTTIMES = 5;
	
	public static JSONObject getResultByCenterService(String url, String reqJson){
		return getResultByCenterService(url, reqJson, DEFAULE_CHARSET, DEFAULT_OUTTIMES);
	}
	
	public static JSONObject getResultByCenterService(String url, String reqJson, String charset, int outTimes){
		return JSONObject.parseObject(HttpClientUtil.doPost(url, reqJson, charset, outTimes));
	}
}
