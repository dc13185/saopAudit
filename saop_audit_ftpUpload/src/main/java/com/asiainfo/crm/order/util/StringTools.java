package com.asiainfo.crm.order.util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * String的工具类
 *
 * @author 吕振龙
 * @see StringTools
 * @since
 */

public class StringTools {
	/**
	 * Description: 格式化字符串(用户组建表使用) <br>
	 *
	 * @param name
	 * @return String
	 */
	public static String formatString(String name) {
		String s = "0000000000" + name;
		return s.substring(s.length() - 10, s.length());
	}

	/**
	 * Description:把GBK转码成ISO8859_1 <br>
	 *
	 * @param name
	 * @return String
	 */
	public static String getStringByISO(String name) {
		// 防止中文字符出现乱码
		try {
			name = new String(name.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
		}
		return name;
	}

	/**
	 * Description:获得格式化的url <br>
	 *
	 * @param name
	 * @return String
	 */
	public static String getFormatUrl(String url) {
		if (isNullOrNone(url)) {
			return "";
		}

		if (url.indexOf('?') == -1) {
			return url + '?';
		} else {
			return url + '&';
		}
	}

	/**
	 * Description: 获得字符(byte)的实际长度<br>
	 * 1、…<br>
	 * 2、…<br>
	 * 3、…<br>
	 * 4、…<br>
	 *
	 * @param s
	 * @return int
	 * @exception/throws
	 */
	public static int lengthByte(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= 127) {
				length++;
			} else {
				length = length + 2;
			}
		}
		return length;
	}

	/**
	 * Description: 在jdbc用thin客户端连接oracle里面中文算3个字符<br>
	 *
	 * @param s
	 * @return int
	 * @exception/throws
	 */
	public static int lengthByteInPrepared(String s) {
		int length = 0;

		if (s == null) {
			return length;
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= 127) {
				length++;
			} else {
				length = length + 3;
			}
		}
		return length;
	}

	/**
	 * Description:判断字段空null <br>
	 *
	 * @param s
	 * @return boolean
	 */
	public static boolean isNullOrNone(String s) {
		if (s == null || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		}

		return false;
	}



	/**
	 *
	 * Description: 判断对象是否为空<br>
	 *
	 * @param obj
	 * @return boolean
	 * @exception/throws [违例类型] [违例说明]
	 */
	public static boolean isNullOrNone(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			return isNullOrNone((String) obj);
		} else if (obj instanceof List) {
			return ((List) obj).isEmpty();
		} else if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}
		return false;
	}

	/**
	 * Description:判断字段空null <br>
	 *
	 * @param s
	 * @return boolean
	 */
	public static boolean isNullOrNone(String[] ss) {
		if (ss == null || ss.length == 0) {
			return true;
		}

		for (int i = 0; i < ss.length; i++) {
			if (ss[i] == null || "".equals(ss[i].trim())) {
				return true;
			}
		}

		return false;
	}

	public static int countChar(String src, char c) {
		if (isNullOrNone(src)) {
			return 0;
		}

		int k = 0;
		for (int i = 0; i < src.length(); i++) {
			if (src.charAt(i) == c) {
				k++;
			}
		}

		return k;
	}

	/**
	 * 把'变成 ''
	 * @param src
	 * @return
	 */
	public static String oracleString(String src) {
		if (isNullOrNone(src)) {
			return "";
		}

		return src.replaceAll("'", "''");
	}

	/**
	 * 保护截断
	 * @param s
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String truncate(String s, int begin, int end) {
		if (isNullOrNone(s)) {
			return "";
		}

		if (begin < 0) {
			return "";
		}

		if (begin >= end) {
			return "";
		}

		s = s.trim();
		if (begin >= s.length()) {
			return "";
		}

		if (end >= s.length()) {
			return s;
		} else {
			return s.substring(begin, end);
		}
	}

	/**
	 * 从指定字符串中截取两个指定字符串之间的字符串，主要应用于原始字符串中指定标识唯一的情况，标识不存在嵌套的情况，取到的片段不包含指定标识的情况
	 *
	 * @param s 原始字符串
	 * @param s1 第一个字符串
	 * @param s2 第二个字符串
	 * @return s中s1和s2之间的字符串
	 * @author 蒿凤廷 2014-5-6
	 */
	public static String truncateBetween(String s, String s1, String s2) {
		if (null == s || "".equals(s.trim())) {
			return "";
		}
		String r = "";
		int index = s.indexOf(s1);
		if (-1 != index) {
			s = s.substring(index + s1.length());
			index = s.indexOf(s2);
			if (-1 != index) {
				r = s.substring(0, index);
			}
		}
		return r;
	}

	/**
	 *
	 * Description: 判断是否为数字，负数也支持<br>
	 * 1、…<br>
	 * 2、…<br>
	 * 3、…<br>
	 * 4、…<br>
	 *
	 * @param str
	 * @return boolean
	 * @exception/throws [违例类型] [违例说明]
	 */
	public static boolean isNumber(String str) {
		if (null == str || "".equals(str)) {
			return false;
		} else {
			return Pattern.matches("^[0-9]+$", str);
		}
	}

	/**
	 * 判断是否为数值，包括负数等
	 *
	 * @param str
	 * @return
	 * @author 蒿鳳廷 2011-7-21
	 */
	public static boolean isNumberValue(String str) {
		if (null == str || "".equals(str)) {
			return false;
		} else {
			if (str.startsWith("-")) {
				str = str.substring(1);
			}
			return Pattern.matches("^[0-9]+$", str);
		}
	}

	/**
	 * Description: 获得字符(byte)的实际长度<br>
	 * 1、…<br>
	 * 2、…<br>
	 * 3、…<br>
	 * 4、…<br>
	 *
	 * @param s
	 * @return int
	 * @exception/throws
	 */
	public static int realLength(String s) {
		if (null == s || "".equals(s)) {
			return 0;
		} else {
			return s.getBytes().length;
		}
	}

	/**
	 * Description: 检查非法字符<br>
	 */
	public static boolean isUnlawfulChar(String s) {
		if (null == s || "".equals(s)) {
			return false;
		} else {
			return Pattern.matches("^[^`~@#\\$%\\^&\\*\\(\\)=\\!\\+\\\\/\\|<>\\?;\\:\\.'\"\\{\\}\\[\\]??, ]*$", s);
		}
	}

	/**
	 *
	 * Description: 判断是否为字母<br>
	 * 1、…<br>
	 * 2、…<br>
	 * 3、…<br>
	 * 4、…<br>
	 *
	 * @param str
	 * @return boolean
	 * @exception/throws [违例类型] [违例说明]
	 */
	public static boolean isLetter(String str) {
		if (null == str || "".equals(str)) {
			return false;
		} else {
			return Pattern.matches("^[A-Za-z]+$", str);
		}
	}

	/**
	 * (source != null) ? source : "";相当于oracle的nvl函数
	 *
	 * @param source
	 * @return String
	 */
	public static String nvl(String source) {
		return (source != null) ? source.trim() : "";
	}

	/**
	 * Method nvl.
	 *
	 * @param source
	 *            String
	 * @param defaultString
	 *            String
	 * @return String
	 */
	public static String nvl(String source, String defaultString) {
		return (source != null) ? source.trim() : defaultString;
	}

	/**
	 * 实现对符合条件的字符串的反转
	 *
	 * @param str
	 *            原始字符串
	 * @return 经过反转处理后的字符串
	 */
	public static String reverseString(String str) {
		StringBuffer resultStringBuffer = new StringBuffer();
		String[] allStr = StringUtils.split(str, ' ');
		StringBuffer sb = new StringBuffer(); // 保存需要进行反转的字符串
		for (int i = 0; i < allStr.length; i++) {
			if (meetReverseCondition(allStr[i])) {
				sb.delete(0, sb.length());

				// 将需要反转的字符串反转后添加到结果字符串
				resultStringBuffer.append(sb.append(allStr[i]).reverse()).append(" ");
			} else {
				resultStringBuffer.append(allStr[i]).append(" ");
			}
		}

		return resultStringBuffer.deleteCharAt(resultStringBuffer.length() - 1).toString();
	}

	/**
	 * 判断字符串是否符合反转条件 本函数被reverseString(String str)调用
	 *
	 * @param str
	 *            需要进行反转处理的字符串
	 * @return true 符合反转条件 false 不符合反转条件
	 */
	private static boolean meetReverseCondition(String str) {
		// 如果字符串是以'*'开头并且长度大于1,并且第二个字符是英文,汉字或数字
		return (str.charAt(0) == '*') && (str.length() > 1) && Character.isLetterOrDigit(str.charAt(1));
	}

	/**
	 *
	 * 取字符串的前maxLength长度的字串（一个中文字符为2个长度）
	 *
	 *
	 * @param str
	 * @param maxLength
	 * @return String
	 * throws
	 */
	public static String splitStr(String str, int maxLength) {
		if (maxLength <= 0) {
			return "";
		}
		String result = str;
		byte[] bytes = str.getBytes();
		if (bytes.length > maxLength) {

			result = new String(bytes, 0, maxLength);
			if (!result.substring(result.length() - 1).equals(str.substring(result.length() - 1, result.length()))) {
				result = new String(bytes, 0, maxLength - 1);
			}
		}

		return result;
	}

	/**
	 * 将CLOB类型字符串转成STRING类型字符串
	 *
	 * @param clob
	 * @return
	 * @author 蒿鳳廷 2011-7-12
	 */
	public static String clobToString(java.sql.Clob clob) throws Exception {
		String result = "";
		if (null != clob) {
			try {
				result = clob.getSubString(1, (int) clob.length());
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}
		return result;
	}

	/**
	 * 组装getCustIdByAccNbr辅助函数
	 * @param lanId
	 * @param areaNbr
	 * @param accNbr
	 * @param prodClass
	 * @return
	 */
	public static String getCustIdByAccNbr(String lanId, String areaNbr, String accNbr, String prodClass) {
		StringBuffer str = new StringBuffer();
		str.append(":getCustIdByAccNbr").append("(").append(lanId).append(",").append(areaNbr).append(",")
				.append(accNbr).append(",").append(prodClass).append(")");
		return str.toString();
	}

	/**
	 * 组装getCustIdByExtId辅助函数
	 * @param lanId
	 * @param extCustId
	 * @param custId
	 * @return
	 */
	public static String getCustIdByExtId(String lanId, String extCustId, String custId) {
		StringBuffer str = new StringBuffer();
		str.append(":getCustIdByExtId").append("(").append(lanId).append(",").append(extCustId).append(",")
				.append("$-1001$").append(")");
		return str.toString();
	}

	/**
	 * 组装getProdInstAcctIdByExtId辅助函数
	 * @param lanId
	 * @param extProdInstAcctId
	 * @param prodInstAcctId
	 * @return
	 */
	public static String getProdInstAcctIdByExtId(String lanId, String zoneNumber, String extProdInstAcctId,
												  String prodInstAcctId) {
		StringBuffer str = new StringBuffer();
		str.append(":getProdInstAcctIdByExtId").append("(").append(lanId).append(",").append(zoneNumber).append(",")
				.append(extProdInstAcctId).append(",").append("$-1001$").append(")");
		return str.toString();
	}

	/**
	 * 组装getProdInstIdByAccNbr辅助函数
	 * @param lanId
	 * @param zoneNumber
	 * @param accNbr
	 * @param ProdClass
	 * @return
	 */
	public static String getProdInstIdByAccNbr(String lanId, String zoneNumber, String accNbr, String ProdClass) {
		StringBuffer str = new StringBuffer();
		str.append(":getProdInstIdByAccNbr").append("(").append(lanId).append(",").append(zoneNumber).append(",")
				.append(accNbr).append(",").append(ProdClass).append(")");
		return str.toString();
	}

	/**
	 * 组装getProdInstIdByExtId辅助函数
	 * @param lanId
	 * @param extProdInstId
	 * @param prodInstId
	 * @return
	 */
	public static String getProdInstIdByExtId(String lanId, String extProdInstId, String prodInstId) {
		StringBuffer str = new StringBuffer();
		str.append(":getProdInstIdByExtId").append("(").append(lanId).append(",").append(extProdInstId).append(",")
				.append(prodInstId).append(")");
		return str.toString();
	}

}