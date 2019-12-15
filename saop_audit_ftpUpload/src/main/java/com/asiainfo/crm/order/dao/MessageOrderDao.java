package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: dong.chao
 * @create: 2019-12-12 19:47
 * @description:
 **/
@Component
public class MessageOrderDao {

    @Autowired
    @Qualifier("saopJdbcTemplate")
    JdbcTemplate saopJdbcTemplate;

    @Autowired
    @Qualifier("soJdbcTemplate")
    JdbcTemplate soJdbcTemplate;

    public List<Map<String, Object>>  qryMessageOrderIdByState(String state) {
        String sql = "select mo.ol_id from message_order mo where mo.process_state = ? ";
        return saopJdbcTemplate.queryForList(sql,new Object[]{state});
    }

    public List<Map<String, Object>>  qryCustOrderIdByState(String custOrderIds) {
        String sql = "select co.cust_order_id from customer_order co where co.CUST_ORDER_ID in ( "+custOrderIds+") and  co.STATUS_CD = '301200'";
        return soJdbcTemplate.queryForList(sql);
    }


    public  String qryContentByCustOrderId(String custOrderId){
        String sql = "select col.ord_content from customer_order_log col where col.CUST_ORDER_ID = ? and  col.CONTENT_TYPE = 1 limit 1";
        return soJdbcTemplate.queryForObject(sql,new Object[]{custOrderId},String.class);
    }
}
