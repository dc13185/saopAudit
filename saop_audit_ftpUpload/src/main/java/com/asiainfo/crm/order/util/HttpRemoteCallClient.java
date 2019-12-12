package com.asiainfo.crm.order.util;

import com.asiainfo.crm.bcomm.exception.BError;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http访问方式调用远端系统
 * @author yanwh
 * @datetime 2017年5月31日 下午2:51:23
 */
public class HttpRemoteCallClient {
	private static Logger logger = LoggerFactory.getLogger(HttpRemoteCallClient.class);

	/**
	 * 功能：	以HTTP访问方式调用远端的系统
	 * 参数：	remoteUrl - 远程url
	 *      xml - xml内容
	 *      waitTimes - 等待时间
	 *      retryCount - 重试次数
	 *      systemFlag - 系统标记
	 * 返回：	String 
	 * @throws Exception 
	 */
	public static String callRemote(String remoteUrl, String xml, int waitTimes, int retryCount, String systemFlag,
			Map<String, String> headDatas) {
		HttpClient httpclient = new HttpClient();
		logger.debug("远程系统访问路径(remoteUrl)：{},入参：{}", remoteUrl, xml);
		// 设置访问地址remoteUrl
		PostMethod post = new PostMethod(remoteUrl);
		String retxml = null;
		try {
			// 设置代理报头
			List<Object> headers = new ArrayList<>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					headers.add(new Header(entry.getKey(), entry.getValue()));
				}
			}
			httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			// 设置一些访问http服务的属性
			post.setRequestEntity(new StringRequestEntity(xml, "text/xml; charset=UTF-8", "UTF-8"));
			// 设置访问后台HTTP服务超时 60秒
			httpclient.getParams().setSoTimeout(waitTimes * 1000);
			// 设置失败的时候，重试次数, 一般不重试,填0即可
			httpclient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(retryCount, false));
			// 调用远端的HTTP服务
			int result = httpclient.executeMethod(post);
			logger.debug("HTTP返回码:" + result);
			// 获取返回字符串信息
			InputStream inputStream = post.getResponseBodyAsStream();
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			retxml = FileCopyUtils.copyToString(reader);
			logger.debug("HTTP返回结果:{},的url：{}", retxml, remoteUrl);
		} catch (Exception e) {
			logger.error("HttpRemoteCallClient======>HttpService======>remoteUrl：{},error:{}", remoteUrl, e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(remoteUrl).append("，异常：").append(e.getMessage());
			throw new BError("100012", sb.toString());
		} finally {
			// 关闭连接
			post.releaseConnection();
		}
		return retxml;
	}

	/**
	 * 功能：	以HTTP访问方式调用远端的系统(get)
	 * 参数：	remoteUrl - 远程url
	 *      waitTimes - 等待时间
	 *      retryCount - 重试次数
	 *      systemFlag - 系统标记
	 * 返回：	String 
	 * @throws Exception 
	 */
	public static String callRemoteToGet(String remoteUrl, int waitTimes, int retryCount, String systemFlag) {
		HttpClient httpclient = new HttpClient();
		logger.debug("远程系统访问路径(remoteUrl):" + remoteUrl);
		// 设置访问地址remoteUrl
		GetMethod get = new GetMethod(remoteUrl);
		String retxml = null;
		try {
			// 设置代理报头
			List<Object> headers = new ArrayList<>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			// 设置一些访问http服务的属性
			// 设置访问后台HTTP服务超时 60秒
			httpclient.getParams().setSoTimeout(waitTimes * 1000);
			// 设置失败的时候，重试次数, 一般不重试,填0即可
			httpclient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(retryCount, false));
			// 调用远端的HTTP服务
			int result = httpclient.executeMethod(get);
			logger.debug("HTTP返回码:" + result);
			// 获取返回字符串信息
			InputStream inputStream = get.getResponseBodyAsStream();
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			retxml = FileCopyUtils.copyToString(reader);
			logger.debug("HTTP返回结果:{},的url：{}", retxml, remoteUrl);
		} catch (Exception e) {
			logger.error("HttpRemoteCallClient======>HttpService======>remoteUrl：{},error:{}", remoteUrl, e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(remoteUrl).append("，异常：").append(e.getMessage());
			throw new BError("100012", sb.toString());
		} finally {
			// 关闭连接
			get.releaseConnection();
		}
		return retxml;
	}
	
	/**
	 * 功能：	以HTTP访问方式调用远端的系统
	 * 参数：	remoteUrl - 远程remoteUrl
	 *      message - json内容
	 * 返回：	String 
	 * @throws Exception 
	 */
	public static String callRemote(String remoteUrl,String message){
		logger.debug("远程系统访问路径(remoteUrl):" + remoteUrl);
		HttpClient httpclient = new HttpClient();
		// 设置访问地址remoteUrl
		PostMethod post = new PostMethod(remoteUrl);
		String retxml = null;
		try {
			// 设置代理报头
			List<Object> headers = new ArrayList<>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			// 设置一些访问http服务的属性
			post.setRequestEntity(new StringRequestEntity(message, "application/json; charset=UTF-8", "UTF-8"));
			// 调用远端的HTTP服务
			int result = httpclient.executeMethod(post);
			// 获取返回字符串信息
			InputStream inputStream = post.getResponseBodyAsStream();
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			retxml = FileCopyUtils.copyToString(reader);
			logger.debug("HTTP返回码:" + result + "返回结果:" + retxml);
		} catch (Exception e) {
			logger.error("HttpRemoteCallClient======>HttpService======>remoteUrl：{},error:{}", remoteUrl, e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(remoteUrl).append("，异常：").append(e.getMessage());
			throw new BError("100012", sb.toString());
		} finally {
			// 关闭连接
			post.releaseConnection();
		}
		return retxml;
	}
}