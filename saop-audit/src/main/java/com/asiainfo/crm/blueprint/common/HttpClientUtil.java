package com.asiainfo.crm.blueprint.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用HttpClient进行post请求的工具类
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 功能：	http post 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doPost(String url, String jsonstr, String charset, int outTimes) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpPost = new HttpPost(url);
			// 设置请求头
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(jsonstr, charset);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPost.setEntity(se);
			// 发送请求
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}
	
}