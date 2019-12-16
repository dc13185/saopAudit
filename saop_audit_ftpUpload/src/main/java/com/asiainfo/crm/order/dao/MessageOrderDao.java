package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: dong.chao
 * @create: 2019-12-12 19:47
 * @description:
 **/
@Repository
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



    public void execute(String sql){
        saopJdbcTemplate.execute(sql);
    }

    
    /** 
    * @Description: 查到所有错单集合
    * @Param: [] 
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>> 
    * @Author: dong.chao
    * @Date: 2019/12/15 
    */
    public List<Map<String, Object>> qryInterceptMessageOrder(){
        String sql = " select mo.* from message_order mo where mo.Process_State = 'E1' and mo.process_result   like '%互斥%'";
        return saopJdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> qryMessageOrderInfoById(String id){
        String sql = " select * from message_order_info moi where moi.id = '"+id+"' ";
        return saopJdbcTemplate.queryForMap(sql);
    }


    public int updMessageOrderByFeeBack(Map transactInfo){
        String processState = (String) transactInfo.get("processState");
        String processResult = (String) transactInfo.get("processResult");
        String massageOrderId = (String) transactInfo.get("massageOrderId");
        String transacstionId = (String) transactInfo.get("transacstionId");
        String sql = "UPDATE MESSAGE_ORDER SET PROCESS_STATE = '"+processState+"',PROCESS_RESULT = '"+processResult+"' WHERE TRANSACTION_ID = '"+transacstionId+"' and ID = '"+massageOrderId+"'";
        return saopJdbcTemplate.update(sql);
    }

    public int insertListenMessageOrderHis(String messageOrderId){
       String sql ="insert into listen_message_order_his ( select * from listen_message_order lmo  where lmo.msg_content_key  =  '"+messageOrderId+"')" ;
        return saopJdbcTemplate.update(sql);
    }

    public void deleteListenMessageOrder(String messageOrderId){
        String sql = "delete  listen_message_order lmo where lmo.msg_content_key =  '"+messageOrderId+"'";
        saopJdbcTemplate.execute(sql);
    }

}
