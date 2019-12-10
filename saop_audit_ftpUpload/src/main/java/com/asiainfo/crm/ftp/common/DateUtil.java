package com.asiainfo.crm.ftp.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


	/**
	 * 日期处理工具类
	 * 
	 * @author chenchao
	 * 
	 */
	public class DateUtil {
		public static final String DATE_FORMATE_STRING_DEFAULT = "yyyyMMddHHmmss";
		public static final String DATE_FORMATE_STRING_DEFAULT_A = "yyyyMMdd";
		public static final String DATE_FORMATE_STRING_DEFAULT_B = "yyyy-MM-dd HH:mm";
		public static final String DATE_FORMATE_STRING_DEFAULT_C = "yyyy-MM-dd";
		public static final String DATE_FORMATE_STRING_DEFAULT_D = "yyyy/MM/dd HH:mm:ss";
		public static final String DATE_FORMATE_STRING_DEFAULT_E = "yyyy/MM/dd";
		
		public static final String DATE_CONSTANT_A = " 00:00:00";
		public static final String DATE_CONSTANT_B = " 23:59:59";

		/**
		 *  获取时间 
		 */
		public  static String getNowTime(String dataFormatType) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormatType);
			str = sdf.format(new Date());
			return str;
		}


		/**
		 * 获取昨天日期
		 * @return
		 */
	    public static String getYestoday(){
	    	String str = "";
	    	Calendar ca = Calendar.getInstance();
	    	ca.add(Calendar.DAY_OF_MONTH, -1);
	    	Date d = ca.getTime();
	    	str = getDateByFormat(d,DATE_FORMATE_STRING_DEFAULT_A);
	    	return str;
	    }
		
		public static String getDateByFormat(Date d, String format) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			str = sdf.format(d);
			return str;
		}


		/**
		 * 获取上月1日得日期
		 * @return
		 */
		public static String getLastMonth(){
			String date = "";
			int month = 0;
			Calendar ca = Calendar.getInstance();
			month = ca.get(Calendar.MONTH);
			if(month <10){
				date = ca.get(Calendar.YEAR)+"0"+month+"01";
			}else{
			date = ca.get(Calendar.YEAR)+""+month+"01";
			}
			return date;
		}
		
		/**
		 * 获取本月1日得日期
		 * @return
		 */
		public static String getMonth(){
			String date = "";
			int month = 0;
			Calendar ca = Calendar.getInstance();
			month = ca.get(Calendar.MONTH)+1;
			if(month <10){
				date = ca.get(Calendar.YEAR)+"0"+month+"01";
			}else{
			date = ca.get(Calendar.YEAR)+""+month+"01";
			}
			return date;
		}
		
		public static  Date parseDate(String str, String format) throws ParseException{
			Date d = null;
			SimpleDateFormat sdf = new SimpleDateFormat(format);
		    d = sdf.parse(str);
			return d;
		}

		/**
		 * 计算当前时间离0分、15分、30分、45分的毫秒数
		 * @return
		 */
		public long getDiff(){
			long t = 0l;
			Date d = new Date();
			if( (d.getMinutes()+14) /15 == 0){
				return 0;
			}else{
				int difMin = (15 - (d.getMinutes() )%15);
				t = difMin * 60000;
			}
			return t;
		}
		
		/**
		 * 获取日期
		 * @return
		 */
		public static int getDayOfMonth(){
			Calendar cal = GregorianCalendar.getInstance();
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		
		/**
		 * 获取每天零点时间
		 * @param args
		 * @throws ParseException
		 */
		public static Date getDailyStartTime(){
			Date date = null;
			try {
				date = parseDate((getNowTime(DATE_FORMATE_STRING_DEFAULT_E) + DATE_CONSTANT_A), DATE_FORMATE_STRING_DEFAULT_D);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		
		/**
		 * 获取每天23点59分59秒
		 * @param args
		 * @throws ParseException
		 */
		public static Date getDailyEndTime(){
			Date date = null;
			try {
				date = parseDate((getNowTime(DATE_FORMATE_STRING_DEFAULT_E) + DATE_CONSTANT_B), DATE_FORMATE_STRING_DEFAULT_D);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		
	}
