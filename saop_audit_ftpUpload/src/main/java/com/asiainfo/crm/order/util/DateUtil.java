package com.al.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/** 
* @Description: 时间工具类
* @Param:  
* @return:  
* @Author: dong.chao
* @Date: 2019/12/13 
*/ 
public class DateUtil {


    private static Map<String, SimpleDateFormat> formats;
    public static final String DATE_FORMATE_STRING_DEFAULT = "yyyyMMddHHmmss";
    public static final String DATE_FORMATE_STRING_A = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_STRING_B = "yyyy-MM-dd";
    public static final String DATE_FORMATE_STRING_C = "MM/dd/yyyy HH:mm:ss a";
    public static final String DATE_FORMATE_STRING_D = "yyyy-MM-dd HH:mm:ss a";
    public static final String DATE_FORMATE_STRING_E = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMATE_STRING_F = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String DATE_FORMATE_STRING_G = "yyyy-MM-dd'T'HH:mm:ssz";
    public static final String DATE_FORMATE_STRING_H = "yyyyMMdd";
    public static final String DATE_FORMATE_STRING_I = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMATE_STRING_J = "yyyyMMddHHmmss.SSS";
    public static final String DATE_FORMATE_STRING_K = "yyyyMMddHHmmssSSS";

    static {
        formats = new HashMap<String, SimpleDateFormat>();

        formats.put(DATE_FORMATE_STRING_DEFAULT, new SimpleDateFormat(DATE_FORMATE_STRING_DEFAULT));
        formats.put(DATE_FORMATE_STRING_A, new SimpleDateFormat(DATE_FORMATE_STRING_A));
        formats.put(DATE_FORMATE_STRING_B, new SimpleDateFormat(DATE_FORMATE_STRING_B));
        formats.put(DATE_FORMATE_STRING_C, new SimpleDateFormat(DATE_FORMATE_STRING_C));
        formats.put(DATE_FORMATE_STRING_D, new SimpleDateFormat(DATE_FORMATE_STRING_D));
        formats.put(DATE_FORMATE_STRING_E, new SimpleDateFormat(DATE_FORMATE_STRING_E));
        formats.put(DATE_FORMATE_STRING_F, new SimpleDateFormat(DATE_FORMATE_STRING_F));
        formats.put(DATE_FORMATE_STRING_G, new SimpleDateFormat(DATE_FORMATE_STRING_G));
        formats.put(DATE_FORMATE_STRING_H, new SimpleDateFormat(DATE_FORMATE_STRING_H));
        formats.put(DATE_FORMATE_STRING_I, new SimpleDateFormat(DATE_FORMATE_STRING_I));
        formats.put(DATE_FORMATE_STRING_J, new SimpleDateFormat(DATE_FORMATE_STRING_J));
        formats.put(DATE_FORMATE_STRING_K, new SimpleDateFormat(DATE_FORMATE_STRING_K));
    }

    /**
     * 将Date转换为 pattern 格式的字符串，格式为：
     * yyyyMMddHHmmss
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd
     * MM/dd/yyyy HH:mm:ss a
     * yyyy-MM-dd HH:mm:ss a
     * yyyy-MM-dd'T'HH:mm:ss'Z'
     * yyyy-MM-dd'T'HH:mm:ssZ
     * yyyy-MM-dd'T'HH:mm:ssz
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return String --格式化的日期字符串
     * @see java.util.Date
     */
    @Deprecated
    public static String getFormatTimeString(Date date, String pattern) {
        return format(date, pattern);
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 将date转换为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, DATE_FORMATE_STRING_A);
    }

    /**
     * 将Date转换为默认的YYYYMMDDHHMMSS 格式的字符串
     *
     * @param date
     * @return
     */
    @Deprecated
    public static String getDefaultFormateTimeString(Date date) {
        return format(date, DATE_FORMATE_STRING_DEFAULT);
    }

    /**
     * 根据pattern取得的date formate
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat sDateFormat = formats.get(pattern);
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat(pattern);
            formats.put(pattern, sDateFormat);
        }
        return sDateFormat;
    }

    /**
     * 将格式将日期字符串转换为Date对象
     *
     * @param date    字符串
     * @param pattern 格式如下：
     *                yyyyMMddHHmmss
     *                yyyy-MM-dd HH:mm:ss
     *                yyyy-MM-dd
     *                MM/dd/yyyy HH:mm:ss a
     *                yyyy-MM-dd HH:mm:ss a
     *                yyyy-MM-dd'T'HH:mm:ss'Z'
     *                yyyy-MM-dd'T'HH:mm:ssZ
     *                yyyy-MM-dd'T'HH:mm:ssz
     * @return 日期Date对象
     * @throws ParseException
     * @see java.util.Date
     */
    @Deprecated
    public static Date getDateFromString(String date, String pattern) throws ParseException {
        return parseDate(date, pattern);
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        return DateUtils.parseDate(date, pattern);
    }

    /**
     * 解析yyyy-MM-dd HH:mm:ss格式的日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return DateUtils.parseDate(date, DATE_FORMATE_STRING_A);
    }

    /**
     * 将日期字符串转化成默认格式YYYYMMDDHHMMSS的Date对象
     *
     * @param date
     * @return
     * @throws ParseException
     */
    @Deprecated
    public static Date getDefaultDateFromString(String date) throws ParseException {
        return parseDate(date, DATE_FORMATE_STRING_DEFAULT);
    }

    /**
     * 取当前时间,格式为YYYYMMDDHHMMSS的日期字符串
     *
     * @return 当前日期字符串
     * @throws ParseException
     * @see java.util.Date
     */
    public static String getNowDefault() {
        return getNow(DATE_FORMATE_STRING_DEFAULT);
    }

    /**
     * 按照pattern格式取当前日期字符串
     *
     * @param pattern 日期字符串格式
     * @return 格式化后的当前日期字符串
     */
    public static String getNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 取当前时间,格式为YYYYMMDD
     *
     * @return 当前日期字符串
     * @throws ParseException
     * @see java.util.Date
     */
    public static String getNowII() {
        return getFormatTimeString(new Date(), DATE_FORMATE_STRING_H);
    }

    /**
     * 将输入pattern格式的日期字符串转换为取时间的毫秒数 since 1970
     *
     * @return 时间毫秒数
     * @throws ParseException
     * @see java.util.Date
     */
    @Deprecated
    public static long dateString2Long(String str, String pattern) throws ParseException {
        return date2Long(str, pattern);
    }

    /**
     * 将输入pattern格式的日期换为取时间的毫秒数
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static long date2Long(String str, String pattern) throws ParseException {
        return parseDate(str, pattern).getTime();
    }

    /**
     * 把since1970的毫秒数转成默认格式yyyyMMddHHmmss的String日期字符串
     *
     * @param time
     * @return
     */
    @Deprecated
    public static String longToDateStringDefault(long time) {
        return format(new Date(time), DATE_FORMATE_STRING_DEFAULT);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String longToDateString(long time) {
        return longToDateString(time, DATE_FORMATE_STRING_A);
    }

    /**
     * 将时间的毫秒数 since 1970转换为按照pattern格式的日期字符串
     *
     * @return 日期字符串
     * @see java.util.Date
     */
    public static String longToDateString(long time, String pattern) {
        return format(new Date(time), pattern);
    }

    /**
     * 将Date对象转成since 1970的毫秒数
     *
     * @param date
     * @return since1970的毫秒数
     */
    public static long date2Long(Date date) {
        return date.getTime();
    }

    /**
     * 将since1970毫秒数转成Date对象
     *
     * @param time
     * @return
     */
    public static Date longToDate(long time) {
        return new Date(time);
    }

    /**
     * 自动适配两种格式的日期字符串转换为date对象
     * A格式	:	yyyy-MM-dd HH:mm:ss
     * B格式	:	yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDateFromStringAdaptTwoPattern(String date) throws ParseException {
        try {
            return parseDate(date, DATE_FORMATE_STRING_A);
        } catch (ParseException e) {
            return parseDate(date, DATE_FORMATE_STRING_B);
        }
    }


    /**
     * 将指定字符串格式的时间串转化为需要的格式时间串
     *
     * @param datestr   入参时间
     * @param inFormat  入参格式
     * @param outFormat 出参格式
     * @return
     * @throws ParseException
     */
    @Deprecated
    public static String changeNumDateToDate(String datestr, String inFormat, String outFormat) throws ParseException {
        return convertDate(datestr, inFormat, outFormat);
    }

    /**
     * 将一种日期格式转为另外种日期格式
     * @param date
     * @param inFormat
     * @param outFormat
     * @return
     * @throws ParseException
     */
    public static String convertDate(String date, String inFormat, String outFormat) throws ParseException {
        Date d = parseDate(date, inFormat);
        return format(d, outFormat);
    }


    /**
     * 获取后一个月的第一天
     * @param nowdate
     * @param inFormat
     * @param outFormat
     * @return
     * @throws ParseException
     */
    @Deprecated
    public static String getNextMonthFistDay(String nowdate, String inFormat, String outFormat) throws ParseException {
        Date date = parseDate(nowdate, inFormat);
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, 1);
        cl.set(Calendar.DAY_OF_MONTH, 1);
        date = cl.getTime();
        return format(date, outFormat);
    }

    /**
     * 在date日期上增加num天
     * @param date
     * @param num
     * @return
     */
    public static Date addDays(Date date, int num) {
        return DateUtils.addDays(date, num);
    }

    /**
     * 在date日期上增加num天,以pattern格式输出
     * @param date
     * @param num
     * @param pattern
     * @return
     */
    public static String addDays(Date date, int num, String pattern) {
        return format(addDays(date, num), pattern);
    }

    /**
     * 当前系统时间的下一天，明天
     * @return
     */
    public static Date nextDay() {
        return nextDay(new Date());
    }

    /**
     * 日期date的下一天
     * @return
     */
    public static Date nextDay(Date date) {
        return addDays(date, 1);
    }

    /**
     * 当前系统时间的下一天，明天,以pattern格式输出
     * @param pattern
     * @return
     */
    public static String nextDayStr(String pattern) {
        return nextDayStr(nextDay(), pattern);
    }

    /**
     * 日期date的下一天,以pattern格式输出
     * @param date
     * @param pattern
     * @return
     */
    public static String nextDayStr(Date date, String pattern) {
        return format(date, pattern);
    }

    /**
     * inPattern格式的输入日期inDate，增加num天，以outPattern格式输出
     * @param inDate
     * @param inPattern
     * @param outPattern
     * @param num
     * @return
     * @throws ParseException
     */
    public static String addDays(String inDate, String inPattern, String outPattern, int num) throws ParseException {
        Date date = parseDate(inDate, inPattern);
        return addDays(date, num, outPattern);
    }

    /**
     * 在date日期上增加num毫秒
     * @param date
     * @param num
     * @return
     */
    public static Date addMilliseconds(Date date, int num) {
        return DateUtils.addMilliseconds(date, num);
    }

    /**
     * 在date日期上增加num秒
     * @param date
     * @param num
     * @return
     */
    public static Date addSeconds(Date date, int num) {
        return DateUtils.addSeconds(date, num);
    }

    /**
     * 在date日期上增加num分钟
     * @param date
     * @param num
     * @return
     */
    public static Date addMinute(Date date, int num) {
        return DateUtils.addMinutes(date, num);
    }

    /**
     * 在date日期上增加num小时
     * @param date
     * @param num
     * @return
     */
    public static Date addHours(Date date, int num) {
        return DateUtils.addHours(date, num);
    }

    /**
     * 在date日期上增加num周
     * @param date
     * @param num
     * @return
     */
    public static Date addWeeks(Date date, int num) {
        return DateUtils.addWeeks(date, num);
    }

    /**
     * 在date日期上增加num月
     * @param date
     * @param num
     * @return
     */
    public static Date addMonths(Date date, int num) {
        return DateUtils.addMonths(date, num);
    }

    /**
     * 在date日期上增加num年
     * @param date
     * @param num
     * @return
     */
    public static Date addYears(Date date, int num) {
        return DateUtils.addYears(date, num);
    }

    /**
     * 日期date所在月的最后一天
     * @param date
     * @return
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    /**
     * 日期date的下num个月的的最后一天
     * @param date
     * @param num
     * @return
     */
    public static Date lastDayOfNextMonth(Date date, int num) {
        Date d = addMonths(date, num);
        return lastDayOfMonth(d);
    }

    /**
     * 日期date的上num个月的最后一天
     * @param date
     * @param num
     * @return
     */
    public static Date lastDayOfPrevMonth(Date date, int num) {
        return lastDayOfNextMonth(date, num * -1);
    }

    /**
     * 当前月的最后一天，以pattern格式输出
     * @param pattern
     * @return
     */
    public static String lastDayOfMonth(String pattern) {
        Date d = lastDayOfMonth(new Date());
        return format(d, pattern);
    }

    /**
     * 输入inPattern格式的date日期的所在月最后一天，以outPattern格式输出
     * @param date
     * @param inPattern
     * @param outPattern
     * @return
     * @throws ParseException
     */
    public static String lastDayOfMonth(String date, String inPattern, String outPattern) throws ParseException {
        Date d = parseDate(date, inPattern);
        return format(d, outPattern);
    }

    /**
     * date所在年的最后一天
     * @param date
     * @return
     */
    public static Date lastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        cal.set(Calendar.DAY_OF_YEAR, 0);
        return cal.getTime();
    }

    /**
     * date所在年的第一天
     * @param date
     * @return
     */
    public static Date firstDayOfYear(Date date) {
        return setDate(date,null,1,1);
    }

    /**
     * 重置日期的年月日，不需要的重置的参数传null<br/>
     * eg: 2017-05-23 改为2017-06-23 使用setDate(date,null,6,null)
     * @param date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date setDate(Date date, Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (year != null) {
            cal.set(Calendar.YEAR, year);
        }
        if (month != null) {
            cal.set(Calendar.MONTH, month);
        }
        if (day != null) {
            cal.set(Calendar.DAY_OF_MONTH, day);
        }
        return cal.getTime();
    }

    /**
     * 重置日期的年月日，不需要的重置的参数传null<br/>
     * eg: 2017-05-23 11:25:17 改为2017-05-23 11:25:30使用setTime(date,null,null,30,null)
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static Date setTime(Date date, Integer hour, Integer minute, Integer second, Integer millisecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (hour != null) {
            cal.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != null) {
            cal.set(Calendar.MINUTE, minute);
        }
        if (second != null) {
            cal.set(Calendar.SECOND, second);
        }
        if (millisecond != null) {
            cal.set(Calendar.MILLISECOND, millisecond);
        }
        return cal.getTime();
    }

    /**
     * 修改日期的天
     * @param date
     * @param day
     * @return
     */
    public static Date setDay(Date date, int day) {
        return setDate(date, null, null, day);
    }

    /**
     * 修改日期的小时
     * @param date
     * @param hour
     * @return
     */
    public static Date setHour(Date date, int hour) {
        return setTime(date, hour, null, null, null);
    }

    /**
     * date日期的起始时间,比如2017-05-23 00:00:00.000
     * @param date
     * @return
     */
    public static Date beginDate(Date date) {
        return setTime(date, 0, 0, 0, 0);
    }

    /**
     * date日期的结束时间,比如2017-05-23 23:59:59.999
     * @param date
     * @return
     */
    public static Date endDate(Date date) {
        return setTime(date, 23, 59, 59, 999);
    }

    /**
     * date日期的起始时间,比如2017-05-23 00:00:00.000，以pattern格式输出
     * @param date
     * @param pattern
     * @return
     */
    public static String beginDateStr(Date date, String pattern) {
        return format(beginDate(date), pattern);
    }

    /**
     * date日期的起始时间,比如2017-05-23 00:00:00.000，以yyyy-MM-dd HH:mm:ss格式输出
     * @param date
     * @return
     */
    public static String beginDateStr(Date date) {
        return format(endDate(date));
    }

    /**
     * date日期的结束时间,比如2017-05-23 23:59:59.999,以pattern格式输出
     * @param date
     * @param pattern
     * @return
     */
    public static String endDateStr(Date date, String pattern) {
        return format(endDate(date), pattern);
    }

    /**
     * date日期的结束时间,比如2017-05-23 23:59:59.999，以yyyy-MM-dd HH:mm:ss格式输出
     * @param date
     * @return
     */
    public static String endDateStr(Date date) {
        return format(endDate(date));
    }

    /**
     * 判断闰年
     * <p>
     * 详细设计：
     * 1.被400整除是闰年，否则：
     * 2.不能被4整除则不是闰年
     * 3.能被4整除同时不能被100整除则是闰年
     * 4.能被4整除同时能被100整除则不是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {

        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取一个月的最后一天
     * @param nowdate
     * @param inFormat
     * @param outFormat
     * @return
     * @throws ParseException
     */
    @Deprecated
    public static String getLastDay(String nowdate, String inFormat, String outFormat) throws ParseException {
        return lastDayOfMonth(nowdate, inFormat, outFormat);
    }


    /**
     * 获取一个月的最后一天
     *
     * @param fmt
     * @return
     */
    @Deprecated
    public static String getMonthLastDay(String fmt) {
        return lastDayOfMonth(fmt);
    }

    /**
     * 获取后一个月的第一天
     * @param fmt
     * @return
     */
    @Deprecated
    public static String getNextMonthFirstDay(String fmt) {
        return firstDayOfMonth(fmt);
    }

    /**
     * 日期date所在月的第一天，以为pattern格式输出
     * @param date
     * @param pattern
     * @return
     */
    public static String firstDayOfMonth(Date date, String pattern) {
        Date d = setDate(date, null, null, 1);
        return format(d, pattern);
    }

    /**
     * 当前系统时间的所在月的第一天，以为pattern格式输出
     * @param pattern
     * @return
     */
    public static String firstDayOfMonth(String pattern) {
        Date d = setDate(new Date(), null, null, 1);
        return format(d, pattern);
    }

    /**
     * 判断两个时间大小(fistDate 在 secondDate 之前 返回true，否则返回false)
     *
     * @param format
     * @return
     * @throws ParseException
     */
    public static boolean compareDate(String fistDate, String secondDate, String format) throws ParseException {
        Date fist = parseDate(fistDate, format);
        Date second = parseDate(secondDate, format);
        return fist.before(second);
    }

    /**
     * 日期检查,判断是否是合法日期
     *
     * @param value    要验证的值
     * @param varValue xml 规则上的值,日期格式 yyyy-MM-dd HH:mm:s
     * @return
     */
    public static boolean isRightDate(String value, String varValue) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(varValue);
            format.setLenient(false);//若为true 2-31会自动转换为3-3
            format.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param date
     * @param field
     * @return
     */
    public static int getField(Date date, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    /**
     * 获取date上的年
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return getField(date, Calendar.YEAR);
    }

    /**
     * 获取date上的月
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return getField(date, Calendar.MONTH);
    }

    /**
     * 获取date上的日
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        return getField(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取date上的小时
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return getField(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取date上的分钟
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return getField(date, Calendar.MINUTE);
    }

    /**
     * 获取date上的秒数
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return getField(date, Calendar.SECOND);
    }


    /**
     * 比较是否同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 两个日期相差几年
     * @param date1
     * @param date2
     * @return
     */
    public static int diffYear(Date date1, Date date2) {
        return Math.abs(getYear(date1) - getYear(date2));
    }

    /**
     * 两个日期相差几个月
     * @param date1
     * @param date2
     * @return
     */
    public static int diffMonth(Date date1, Date date2) {
        int month1 = getYear(date1) * 12 + getMonth(date1);
        int month2 = getYear(date2) * 12 + getMonth(date2);
        return Math.abs(month1 - month2);
    }

    /**
     * 两个日期相差几天
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDay(Date date1, Date date2) {
        date1 = setTime(date1, 1, 0, 0, 0);
        date2 = setTime(date2, 1, 0, 0, 0);
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    /**
     * 两个日期相差几个小时
     * @param date1
     * @param date2
     * @return
     */
    public static long diffHour(Date date1, Date date2) {
        date1 = setTime(date1, null, 0, 0, 0);
        date2 = setTime(date2, null, 0, 0, 0);
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return diff / (60 * 60 * 1000);
    }

    /**
     * 两个日期相册几分钟
     * @param date1
     * @param date2
     * @return
     */
    public static long diffMinute(Date date1, Date date2) {
        date1 = setTime(date1, null, null, 0, 0);
        date2 = setTime(date2, null, null, 0, 0);
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return diff / (60 * 1000);
    }

    /**
     * 两个日期相差几秒
     * @param date1
     * @param date2
     * @return
     */
    public static long diffSecond(Date date1, Date date2) {
        date1 = setTime(date1, null, null, null, 0);
        date2 = setTime(date2, null, null, null, 0);
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return diff / 1000;
    }

}
