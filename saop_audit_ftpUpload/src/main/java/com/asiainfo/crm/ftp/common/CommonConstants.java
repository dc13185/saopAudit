/**
 * @author zero
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.asiainfo.crm.ftp.common;


import java.text.DecimalFormat;


public class CommonConstants {

    public static final String BCODE = "6001030001";//
	public static final String ESC_CODE = "1000000042";//
	public static final String Suffix = ".txt";
	public static final String Suffix_ing = ".txt.ing";
	public static final String spilt2 = "|#%#|";
	public static final String BR = "\n";//换行符
	public static final String BR2 = "\r\n";//换行符
	public static final String column_split = "|#|";
	public static final String CREATE_FILE = "C";//
	public static final String PARSE_FILE = "P";//
	public static final String UPLOAD_FILE = "U";//
	public static final String DOWNLOAD_FILE = "D";//
	public static final int BUFFER_SIZE = 1024;//FTP参数
	public static final DecimalFormat NUMBER_FORMAT_0001 = new DecimalFormat(PropertyUtil.getProperty("SEQ_0001")); //文件序列号格式化规则
	public static final DecimalFormat NUMBER_FORMAT_001 = new DecimalFormat(PropertyUtil.getProperty("SEQ_001"));//文件序列号格式化规则
	public static final DecimalFormat RESEND_NUMBER_FORMAT = new DecimalFormat("000");//文件序列号格式化规则
	public static final String SUCCESS_STATE = "C";
	public static final String ALL_SUCCESS_STATE = "A";
	public static final String FAIL_STATE = "F";
	public static final String DEAL_DONE = "D";
	public static  long MAX_ROWNUM_PRE_FILE = 1000000; //每个文件最大100W数据后分割成新的文件
	public static  long MAX_ROW_PER_TIME = 10000; //每次最大取1W数据
	
	public static final String ORDER_TYPE_ADD_OFFER = "";//订购销售品
	public static final String ORDER_TYPE_INSTALL = "1000";//新装
	
}
