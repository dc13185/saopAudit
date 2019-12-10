package com.asiainfo.crm.ftp.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.asiainfo.crm.ftp.smo.SaopFtpSmoImpl;	


public class RegxUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SaopFtpSmoImpl.class);
	
	/**
	 * 本地网的文件名生成规则
	 * @param buscode
	 * @param areacode
	 * @param seq
	 * @return
	 */
	public static String getFileNameByBuscode(String buscode,String areacode,String seq){
		String fileName = "";
		try{
			if (buscode.equals("ODSFTP")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String dateStr = sdf.format(new Date());
				fileName = PropertyUtil.getProperty("fileName_regx_"+buscode) + "_" + areacode + "_" + dateStr + ".txt";
			} else if("HAOBAI114".equals(buscode)){
				if (areacode.equals("025")){
					areacode = "0025";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateStr = sdf.format(new Date());
				fileName = PropertyUtil.getProperty("fileName_regx_"+buscode)+ "_" + areacode + "_" + dateStr + "_" + seq + ".dat";
			} else if ("CDMASTATS".equals(buscode) || "GHUASTATS".equals(buscode)){
				if (areacode.equals("025")){
					areacode = "0025";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateStr = sdf.format(new Date());
				fileName = PropertyUtil.getProperty("fileName_regx_"+ buscode) + "_" + areacode +"_" + dateStr + "_000.dat" ;
			} else if ("KHDWFTP".equals(buscode)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateStr = sdf.format(new Date());
				fileName = PropertyUtil.getProperty("fileName_regx_"+ buscode) + "_" + areacode +"_" + dateStr + ".txt";
			} else if ("SJHSPT".equals(buscode)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dateStr = sdf.format(new Date());
				fileName = PropertyUtil.getProperty("fileName_regx_"+ buscode) + "_"  + dateStr + ".txt" ;
			}
		}catch(Exception e){
			logger.error("",e);
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * 省中心和SAOP文件名生成规则
	 * @param buscode
	 * @param seq
	 * @return
	 */
	public static String getFileNameByBuscode(String buscode,int seq){
		String fileName = "";
		String regx = "\\{(.*?)\\}";
		try{
		String str = PropertyUtil.getProperty("fileName_regx_"+buscode);
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			//替换{}中的内容
			String key = matcher.group(1).trim();
			String val ="";
			if (key.equals("DATE")) {
				val = DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT_A);
			} else if (key.equals("TIME")) {
				val = DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT);
			} else if (key.equals("TYPE")){
				val = PropertyUtil.getProperty(key + "_" + buscode);
				//全量稽核type会跟日期有关系
				if(val.matches("\\{.+\\}")){
					//获取day
					int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					val = JSONPath.eval(JSON.parseObject(val), "$."+day).toString();
				}
			}else {
				val = PropertyUtil.getProperty(key + "_" + buscode);
				if(key.indexOf("SEQ")!=-1){
					int len = val.length();
					if(len == 3){
						val = CommonConstants.NUMBER_FORMAT_001.format(seq);
					}else if(len==4){
						val=CommonConstants.NUMBER_FORMAT_0001.format(seq);
					}else{
						val = CommonConstants.RESEND_NUMBER_FORMAT.format(seq);
					}
				}
			}
			str = str.replace("{"+key+"}", val);
		}
		logger.debug("create file name:"+str);
		fileName = str;
		}catch(Exception e){
			logger.error("",e);
		}
		return fileName;
	}
}
