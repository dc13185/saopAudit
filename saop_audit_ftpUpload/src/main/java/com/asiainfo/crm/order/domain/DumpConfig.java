package com.asiainfo.crm.order.domain;
import	java.security.KeyStore.Builder;

/**
 * @author: dong.chao
 * @create: 2019-12-13 15:00
 * @description:
 **/
public class DumpConfig {

    /** 表名 */
    String tableName;
    /** 转储开始天数 */
    int startDay;
    /** 转储结束天数 */
    int endDay;



    public String getTableName() {
        return tableName;
    }

    public DumpConfig setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public int getStartDay() {
        return startDay;
    }

    public DumpConfig setStartDay(int startDay) {
        this.startDay = startDay;
        return this;
    }

    public int getEndDay() {
        return endDay;
    }

    public DumpConfig setEndDay(int endDay) {
        this.endDay = endDay;
        return this;
    }
}
