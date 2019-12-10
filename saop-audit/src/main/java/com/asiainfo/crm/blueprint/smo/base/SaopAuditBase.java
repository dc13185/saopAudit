package com.asiainfo.crm.blueprint.smo.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.crm.blueprint.dao.ISaopAuditDao;

/**
 * 稽核基类
 * @author 陈超
 *
 */
@Component
public class SaopAuditBase {
	
	private static final Logger LOG = LoggerFactory.getLogger(SaopAuditBase.class);
	
	protected static final String DEAL_TYPE = "W";
	
	protected static final String PROVINCE_CODE = "600201";

	// 缓存codeMapping
	protected static Map<String, String> lanId2AreaCodeMap = new HashMap<String, String>();// 存放landid跟areaCode的映射关系，Key：lanId，value：areaCode

	protected static Map<String, String> areaCode2LanIdMap = new HashMap<String, String>();// 存放landid跟areaCode的映射关系，Key：areaCode，value：lanId

	protected static Map<String, String> lanIdV2Map = new HashMap<String, String>();// 8511100 跟 11000 （乐山）
	
	protected static Map<String, String> lanIdMap = new HashMap<String, String>();// 8511100 跟 11000 （乐山）

	@Autowired
	protected ISaopAuditDao saopAuditDao;
	
	public void init() {
		LOG.debug("=====初始化编码映射关系=====");

		try {
			List<Map<String, String>> lanId2ZumList = this.saopAuditDao.queryCodeMapping("LANCODE_MAPPING_ID_2_ZNUM");
			List<Map<String, String>> lanIdV2List = this.saopAuditDao.queryCodeMapping("LANID_V2");
			List<Map<String, String>> lanIdList = this.saopAuditDao.queryCodeMapping("LANID");
			if(lanId2ZumList != null){
				for (Map<String, String> map : lanId2ZumList) {
					//8511100 转 0833
					lanId2AreaCodeMap.put(map.get("H_CODE"), map.get("P_CODE"));
					//0833 转 8511100
					areaCode2LanIdMap.put(map.get("P_CODE"), map.get("H_CODE"));
				}
			}
			if(lanIdV2List != null){
				for(Map<String, String> map : lanIdV2List){
					//8511100 转 11000
					lanIdV2Map.put(map.get("H_CODE"), map.get("P_CODE"));
				}
			}
			if(lanIdList != null){
				for(Map<String, String> map : lanIdList){
					//11000 转 5111
					lanIdMap.put(map.get("P_CODE"), map.get("H_CODE"));
				}
			}

			LOG.debug("=====初始化结束=====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 省份id为地市id前三位+"0000"
	 * 
	 * @param lanId
	 * @return
	 */
	protected String getProvinceId(String lanId) {
		if (StringUtils.isNotBlank(lanId)) {
			return lanId.substring(0, 3) + "0000";
		}
		return null;
	}

	/**
	 * 统一稽核错误日志记录
	 * @param auditOriginDataId
	 * @param e
	 */
	protected void insertAuditFailLog(String auditOriginDataId, String errm) {
		Map<String, String> errorParam = new HashMap<String, String>();
		// 由于数据库字段长度的限制，所以错误信息长度大于600需要截取
		if (null != errm && errm.length() < 600) {
			errorParam.put("errM", errm);
		} else {
			errorParam.put("errM", errm.substring(1, 600));
		}
		errorParam.put("auditOriginDataId", auditOriginDataId);
		saopAuditDao.insertUniteAuditFailLog(errorParam);
		LOG.debug("保存信息稽核错误日志表成功 param=" + errorParam);
	}
	
	/**
	 *  稽核错误日志表(产销品稽核用)
	 * @param transactionID
	 * @param param
	 * @param e
	 */
	protected void insertAuditErrLog(String transactionID, Map<String, String> param, Exception e) {
		LOG.debug("数据库库操作异常：" + e.getMessage());
		Map<String, String> errParams = new HashMap<String, String>();
		errParams.put("I_TansID", transactionID);
		errParams.put("I_Params", transMapToString(param));
		errParams.put("I_ErrMsg", e.getMessage());
		saopAuditDao.insertAuditErrLog(errParams);
	}
	
	// map转string，稽核错误日志的错误信息
	@SuppressWarnings("rawtypes")
	protected static String transMapToString(Map<String, String> map) {
		Entry entry;
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			entry = (Entry) iterator.next();
			sb.append(null == entry.getValue() ? "" : entry.getValue().toString())
					.append(iterator.hasNext() ? "," : "");
		}
		return sb.toString();
	}
	
	/**
	 * 获取时间格式为yyyy-MM-dd HH:mm:ss的字符串
	 * @param date
	 * @return
	 */
	protected String getFormatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 *  统一处理auditTime
	 * @param auditTime
	 * @return
	 */
	protected String processAuditTime(String auditTime) {
		if(auditTime != null){
			return auditTime.substring(0, 10).replaceAll("/|-", "");
		}
		return "";
	}
	
	/**
	 * 去除毫秒
	 * @param time
	 * @return
	 */
	protected String removeMillisecond(String time){
		if(time != null){
			return time.replaceAll("\\.\\d{1,3}", "");
		}
		return "";
	}
	
	/**
	 *  手机号码前加“86”
	 * @param phoneNbr
	 * @return
	 */
	protected String processPhoneNbr(String phoneNbr){
		if(phoneNbr.startsWith("86")){
			return phoneNbr;
		}else{
			return "86"+phoneNbr;
		}
	}
}
