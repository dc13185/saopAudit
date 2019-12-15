package com.asiainfo.crm.order.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: dong.chao
 * @create: 2019-12-13 14:55
 * @description:
 **/
public class TransferStoreConstant {


    // 查询单交互日志主表
    public static final String TRANSACT_LOG_QRY = "TRANSACT_LOG_QRY";

    // 查询单交互日志主表历史表
    public static final String TRANSACT_LOG_QRY_HIS = "TRANSACT_LOG_QRY_HIS";

    // 查询单交互日志info表
    public static final String TRANSACT_LOG_QRY_INFO = "TRANSACT_LOG_QRY_INFO";

    // 查询单交互日志info表历史表
    public static final String TRANSACT_LOG_QRY_INFO_HIS = "TRANSACT_LOG_QRY_INFO_HIS";

    // 校验单单交互日志主表
    public static final String TRANSACT_LOG_CHK = "TRANSACT_LOG_CHK";

    // 校验单单交互日志主表历史表
    public static final String TRANSACT_LOG_CHK_HIS = "TRANSACT_LOG_CHK_HIS";

    // 校验单单交互日志info表
    public static final String TRANSACT_LOG_CHK_INFO = "TRANSACT_LOG_CHK_INFO";

    // 校验单单交互日志info表历史表
    public static final String TRANSACT_LOG_CHK_INFO_HIS = "TRANSACT_LOG_CHK_INFO_HIS";

    // 生单表主表
    public static final String MESSAGE_ORDER = "MESSAGE_ORDER";

    // 生单表主表历史表
    public static final String MESSAGE_ORDER_HIS = "MESSAGE_ORDER_HIS";

    // 生单表暂存主表
    public static final String MESSAGE_ORDER_STG = "MESSAGE_ORDER_STG";

    // 生单表info表
    public static final String MESSAGE_ORDER_INFO = "MESSAGE_ORDER_INFO";

    // 生单表info表历史表
    public static final String MESSAGE_ORDER_INFO_HIS = "MESSAGE_ORDER_INFO_HIS";

    // 生单表暂存info表
    public static final String MESSAGE_ORDER_INFO_STG = "MESSAGE_ORDER_INFO_STG";

    // 交互日志主表
    public static final String TRANSACT_LOG = "TRANSACT_LOG";

    // 交互日志主表历史表
    public static final String TRANSACT_LOG_HIS = "TRANSACT_LOG_HIS";

    // 交互日志暂存主表
    public static final String TRANSACT_LOG_STG = "TRANSACT_LOG_STG";

    // 交互日志info表
    public static final String TRANSACT_LOG_INFO = "TRANSACT_LOG_INFO";

    // 交互日志info表历史表
    public static final String TRANSACT_LOG_INFO_HIS = "TRANSACT_LOG_INFO_HIS";

    // 交互日志暂存info表
    public static final String TRANSACT_LOG_INFO_STG = "TRANSACT_LOG_INFO_STG";

    // 调用外围接口日志表
    public static final String INVOKE_URL_LOG = "INVOKE_URL_LOG";

    // 调用外围接口日志表
    public static final String INVOKE_URL_LOG_INFO = "INVOKE_URL_LOG_INFO";

    // 调用外围接口日志历史表
    public static final String INVOKE_URL_LOG_HIS = "INVOKE_URL_LOG_HIS";

    // 调用外围接口日志历史表
    public static final String INVOKE_URL_LOG_INFO_HIS = "INVOKE_URL_LOG_INFO_HIS";

    // 调用外围接口日志暂存表
    public static final String INVOKE_URL_LOG_STG = "INVOKE_URL_LOG_STG";

    // 调用外围接口日志暂存表
    public static final String INVOKE_URL_LOG_INFO_STG = "INVOKE_URL_LOG_INFO_STG";

    // 集团校验单
    public static final String GROUP_ORDER_CHECKED = "GROUP_ORDER_CHECKED";

    // 集团校验单
    public static final String GROUP_ORDER_CHECKED_INFO = "GROUP_ORDER_CHECKED_INFO";


    // 主表每次最多提交数
    public static final int MAIN_TABLE_PRE_COMMIT = 1000;

    // 主表每次最多提交数
    public static final int INFO_TABLE_PRE_COMMIT = 100;


    public static List<String>  dumpDataTables;

    public static List<String>  allDataTables;

    /** 预编译正则 */
    public static   Pattern CODE_PATTERN = Pattern.compile("<Response>[\\w\\W]*</Response>");

    public static List<String> getDumpDataTables(){
        if( dumpDataTables == null){
            dumpDataTables = new ArrayList<>();
            dumpDataTables.add(MESSAGE_ORDER);
            dumpDataTables.add(MESSAGE_ORDER_INFO);
        }
        return  dumpDataTables;
    }

    public static List<String> getAllDumpDataTables(){
        if( allDataTables == null){
            allDataTables = new ArrayList<>();
            allDataTables.add(MESSAGE_ORDER);
            allDataTables.add(MESSAGE_ORDER_INFO);
            allDataTables.add(TRANSACT_LOG);
            allDataTables.add(TRANSACT_LOG_INFO);
            allDataTables.add(TRANSACT_LOG_CHK);
            allDataTables.add(TRANSACT_LOG_CHK_INFO);
        }
        return  allDataTables;
    }



}
