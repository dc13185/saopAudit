package com.asiainfo.crm.order.constant;

import java.util.regex.Pattern;

/**
 * @author: dong.chao
 * @create: 2019-12-07 10:18
 * @description: 正则工具类
 **/
public class RegularConstant {

    /** 可选包 offerName */
    public  static final Pattern  QRY_OFFER_NAME_PATTERN = Pattern.compile("可选包\\[[\\s\\S]{1,30}]与");

    /** 主套餐的 */
    public  static final Pattern  QRY_MAIN_OFFER_NAME_PATTERN = Pattern.compile("套餐\\[[\\s\\S]{1,30}]互斥");

    /**截取*/
    public static String INTERCEPTSTRFORMATE = "[\\s\\S]{1,200}%s[\\s\\S]{1,300}";
}
