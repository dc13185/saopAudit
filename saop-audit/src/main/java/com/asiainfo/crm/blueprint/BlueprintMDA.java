package com.asiainfo.crm.blueprint;


/**
 * @mdaMap=target/classes/com/asiainfo/crm/blueprint/MDA.xml
 */
public abstract class BlueprintMDA{
	
	/**
	 * 字符串常量
	 */
	public static final String DEF_CONST =  new String("OK");
	/**
	 * 验证密码几次不过后用户锁定
	 */
	public static final int USER_CHECK_LOCK_TIMES  = new Integer(3);
	
	/**
	 * 加密密钥 secret key
	 */
	public static final  String KEY = new String("password");

	public static final String CACHE_TOKEN_DIR = new String("bp/token");
	
	public static final Long[] IDS = new Long[]{1L,2L,3L,4L};
	
}
