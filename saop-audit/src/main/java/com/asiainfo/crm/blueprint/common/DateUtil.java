package com.asiainfo.crm.blueprint.common;


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
		public static final String DATE_FORMATE_STRING_DEFAULT_F = "yyyy/MM/dd E";
		public static final String DATE_FORMATE_STRING_DEFAULT_G = "yyyymmddhh24miss";
		
		public static final String DATE_CONSTANT_A = " 00:00:00";
		public static final String DATE_CONSTANT_B = " 23:59:59";

		/**
		 *  获取当前时间 
		 */
		public static String getNowTime(String dataFormatType) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormatType);
			str = sdf.format(new Date());
			return str;
		}


		/**
		 * 获取昨天日期
		 * @return
		 */
	    public static String getYestoday(String dataFormatType){
	    	String str = "";
	    	Calendar ca = Calendar.getInstance();
	    	ca.add(Calendar.DAY_OF_MONTH, -1);
	    	Date d = ca.getTime();
	    	str = getDateByFormat(d,dataFormatType);
	    	return str;
	    }
		
	    /**
	     * 格式化
	     * @param d
	     * @param format
	     * @return String
	     */
		public static String getDateByFormat(Date d, String format) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			str = sdf.format(d);
			return str;
		}


		/**
		 * 获取上月1日的日期
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
		 * 获取本月1日的日期
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
		
		/**
		 * 格式化
		 * @param str
		 * @param format
		 * @return Date
		 * @throws ParseException
		 */
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
		@SuppressWarnings("deprecation")
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
		 * 获取昨天0点0分0秒
		 * @param args
		 * @throws ParseException
		 */
		public static String getLastDayStartTime() throws ParseException{
			String date = null;
			date = getYestoday(DATE_FORMATE_STRING_DEFAULT_F) + DATE_CONSTANT_A;
			return date;
		}
		
		/**
		 * 获取昨天23点59分59秒
		 * @param args
		 * @throws ParseException
		 */
		public static String getLastDayEndTime() throws ParseException{
			String date = null;
			date = getYestoday(DATE_FORMATE_STRING_DEFAULT_F) + DATE_CONSTANT_B;
			return date;
		}
	}
