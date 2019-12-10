package com.asiainfo.crm.ftp.common;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Webservice辅助工具类
 * @author yanwh
 * @datetime 2017年5月31日 下午3:43:31
 */
public class WebServiceAxisClient {
	private static Logger logger = LoggerFactory.getLogger(WebServiceAxisClient.class);

	/**
	 * 功能：  调用Webservice接口通用客户端方法
	 * 参数：  url - Web服务所在的URL
	 * 		function - Web服务提供的接口
	 * 		obj - Web服务接口入参
	 * 返回：	Object
	 */
	public static Object callWebService(String url, String function, Object[] obj) {
		return callWebService(url, function, obj, 0, "", 0, "");
	}

	/**
	 * 功能：	调用Webservice接口通用客户端方法
	 * 参数：	url - Web服务所在的URL
	 * 		function - Web服务提供的接口
	 * 		obj - Web服务接口入参
	 * 		systemFlag - 系统标识，里面包括了“错误编码”和“错误信息前缀”，主要是两类错误信息：超时错误、通用错误
	 * 返回：	Object
	 */
	public static Object callWebService(String url, String function, Object[] obj, String systemFlag) {
		return callWebService(url, function, obj, 0, "", 0, systemFlag);
	}

	/**
	 * 功能：	调用Webservice接口通用客户端方法
	 * 参数：	url - Web服务所在的URL
	 * 		function - Web服务提供的接口
	 * 		waitTimes - 超时时间（秒）
	 * 		retryCount - 重发次数
	 * 		obj - Web服务接口入参
	 * 返回：	Object
	 */
	public static Object callWebService(String url, String function, int waitTimes, int retryCount, Object[] obj) {
		return callWebService(url, function, obj, waitTimes, "", retryCount, "");
	}

	/**
	 * 功能：	调用Webservice接口通用客户端方法
	 * 参数：	url - Web服务所在的URL
	 * 		function - Web服务提供的接口
	 * 		obj - Web服务接口入参
	 * 		waitTimes - 超时时间（秒）
	 * 		exceptionName - 异常重发
	 * 		retryCount - 异常重发次数
	 * 返回：	Object
	 */
	public static Object callWebService(String url, String function, Object[] obj, int waitTimes, String exceptionName,
			int retryCount, String systemFlag) {
		Object result = null;
		try {
			logger.debug("开始调用Web Service");
			Map<String, String> exceptionRetryConfigMap = new HashMap<String, String>();
			//判断初始化
			if (retryCount == 0) {
				retryCount = 1;
			}
			//创建Service对象，Service对用用于创建Call对象
			Service service = new Service();
			//创建Call对象，Call对象用于调用服务
			Call call = (Call) service.createCall();
			//为Call对象设置WebService的url
			call.setTargetEndpointAddress(new java.net.URL(url));
			//为Call对象设置调用的方法名
			call.setOperationName(function);
			//调用WebService的方法，并获得返回值
			call.setTimeout(waitTimes * 1000);
			for (int i = 1; i <= retryCount; i++) {
				try {
					logger.debug("调用Web Service正常开始(第" + i + "调用)");
					result = (Object) call.invoke(obj);
				} catch (Exception e) {
					boolean retryFlag = false;
					//判断配置异常常量
					for (String ex : exceptionRetryConfigMap.keySet()) {
						if (e.getMessage() != null && e.getMessage().contains(ex)) {
							retryCount = Integer.valueOf(exceptionRetryConfigMap.get(ex));
							retryFlag = true;
							break;
						}
					}
					if (e.getMessage() != null && (e.getMessage().contains(exceptionName) || retryFlag)) {
						//异常符合，循环重调
						continue;
					} else {
						//异常不符合，结束循环
						break;
					}
				}
				//没有异常，结束循环
				break;
			}
			logger.debug("调用Web Service正常结束");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WebServiceAxisClient======>callWebService======>error{}", e);
		}
		return result;
	}

	/**
	 * 功能：	调用OssWebservice接口通用客户端方法
	 * 参数：	url - url
	 * 		targetNameSpace - web服务目标名空间
	 * 		function - Web服务提供的接口
	 * 		obj1 - web服务接口入参名
	 * 		obj2 - Web服务接口入参
	 * 返回：	Object
	 */
	public static Object callOssWebService(String url, String targetNameSpace, String function, Object[] obj1,
			Object[] obj2) {
		Object result = null;
		try {
			logger.debug("开始调用Web Service");
			//创建Service对象，Service对用用于创建Call对象
			Service service = new Service();
			//创建Call对象，Call对象用于调用服务
			Call call = (Call) service.createCall();
			//为Call对象设置WebService的url
			call.setTargetEndpointAddress(new java.net.URL(url));
			//为Call对象设置调用的方法名
			call.setOperationName(new QName(targetNameSpace, function));
			for (int i = 0; i < obj1.length; i++) {
				String inParameter = (String) obj1[i];
				call.addParameter(inParameter, XMLType.XSD_STRING, ParameterMode.IN);
			}
			call.setReturnType(XMLType.SOAP_STRING);
			//调用WebService的方法，并获得返回值
			call.setTimeout(30000);
			result = (Object) call.invoke(obj2);
			logger.debug("调用Web Service正常结束：{}", result);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WebServiceAxisClient======>callOssWebService======>error{}", e);
		}
		return result;
	}

}