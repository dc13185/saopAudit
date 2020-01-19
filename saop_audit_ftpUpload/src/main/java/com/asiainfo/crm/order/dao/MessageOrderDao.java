package com.asiainfo.crm.order.dao;

import com.al.common.utils.DateUtil;
import com.asiainfo.crm.order.constant.TransferStoreConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
        String sql = "select mo.ol_id from message_order mo where mo.process_state = ? and mo.ol_id is not null  ";
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


    public  Map<String, Object> qyrMessageOrderByOlId(String custOrderId){
        String sql = "select * from message_order mo where mo.ol_id = '"+custOrderId+"'";
        return saopJdbcTemplate.queryForMap(sql);
    }


    public void execute(String sql){
        saopJdbcTemplate.execute(sql);
    }

    public void update(String sql){
        saopJdbcTemplate.update(sql);
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


    public List<Map<String, Object>> qyrErrorMessageOrder(){
        String sql = "select mo.transaction_id,mo.ol_id from message_order mo where mo.process_state = 'E2' and mo.process_result like '%未竣工%'  and mo.process_result like '%未竣工%'";
        return saopJdbcTemplate.queryForList(sql);
    }

    public void insertLog(String customerOrderId,String transactionId,String log){
        if(StringUtils.isBlank(customerOrderId)){
            customerOrderId = "";
        }
        if(StringUtils.isBlank(transactionId)){
            transactionId = "";
        }
        String nowDate = DateUtil.getNow(DateUtil.DATE_FORMATE_STRING_A);
        String sql = "insert into temp_ycfreeback_data_log(cust_order_id,transaction_id,log,create_date) values ('"+customerOrderId+"','"+transactionId+"','"+log+"',to_date('"+nowDate+"','yyyy-mm-dd hh24:mi:ss'))";
        saopJdbcTemplate.update(sql);
    }


    public List<Map<String, Object>> qryErrorMessageOrderInfo(){
        String sql = "select mo.*,moi.incept_msg from message_order mo left join message_order_info moi on mo.id = moi.id where mo.process_result like '%NullPointerException%' and mo.process_state = 'E1'";
        return saopJdbcTemplate.queryForList(sql);
    }


    public String qryPcodeByHcode(String assistExtOfferId) {
        String sql = "select cm.p_code from code_mapping cm where cm.h_code = '"+assistExtOfferId+"' and cm.map_domain = 'PROD_OFFER.EXT_OFFER_NBR' and rownum = 1";
        return saopJdbcTemplate.queryForObject(sql,String.class);
    }

    public void updateMessageOrderTemp(String customerOrderId,String messageOrderId ) {
        String sql = " update   message_order mo   set mo.ol_id = '"+customerOrderId+"' , mo.process_state = 'C1' ,  mo.process_result = '成功' where mo.id = '"+messageOrderId+"'";
        saopJdbcTemplate.update(sql);
    }


    public void qryWrongOrder(){
        String sql = "select mo.ext_cust_order_id from message_order mo where mo.process_state = 'E1' and  mo.process_result like '%未归档%'";
        List<Map<String, Object>>  custOrderIds =  saopJdbcTemplate.queryForList(sql);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < custOrderIds.size(); i++) {
            String custOrderId = (String)custOrderIds.get(i).get("EXT_CUST_ORDER_ID");
            if(i != custOrderIds.size()-1){
                str.append("'").append(custOrderId).append("',");
            }else{
                str.append("'").append(custOrderId).append("'");
            }
        }
        String fackMessageSql = "select c.ext_cust_order_id,c.status_cd from customer_order c  where c.ext_cust_order_id in ("+str.toString()+") ";
        List<Map<String, Object>>  customOrders =  soJdbcTemplate.queryForList(fackMessageSql);
        StringBuffer hdStr = new StringBuffer();
        for (int i = 0; i < customOrders.size(); i++) {
            String custOrderId = (String)customOrders.get(i).get("EXT_CUST_ORDER_ID");
            if(i != customOrders.size()-1){
                hdStr.append("'").append(custOrderId).append("',");
            }else{
                hdStr.append("'").append(custOrderId).append("'");
            }
        }
        if (StringUtils.isNotBlank(hdStr.toString())){
            String exeSql = "begin\n" +
                    "  for rec in (\n" +
                    "     select * from message_order mo where  mo.ext_cust_order_id in ('') and  mo.process_state = 'E1' \n" +
                    "    ) loop\n" +
                    "    update message_order mo SET mo.process_state = 'W' where mo.id = rec.id;\n" +
                    "    update listen_message_order lmo set lmo.deal_flag = 'D' where  lmo.msg_content_key = rec.id;\n" +
                    "end loop;       \n" +
                    "end;";

            saopJdbcTemplate.execute(exeSql);
        }

    }

    public List<String> qryWayOrder(){
        String sql = "select mo.isale from message_order mo where mo.process_state = 'E1' and mo.isale is not null and  mo.process_result like '%未归档%'";
        List<String>  messageOrders =  saopJdbcTemplate.queryForList(sql,String.class);
        return messageOrders;
    }

    public void qyrTheWayByIsale(){
        String sql = "select mo.isale from message_order mo where mo.process_state = 'E1' and mo.isale is not null and  mo.process_result like '%未归档%'";
        List<String>  messageOrders =  saopJdbcTemplate.queryForList(sql,String.class);
        String messageOrderStr = TransferStoreConstant.convertListToString(messageOrders);
        //去受理查询customerOrderId
        String qyrCustomerOrderIdSql = "select c.cust_order_id from customer_order_group_rel c where c.PROVISALE in ("+messageOrderStr+")  and c.REMARK not like '%撤单%'";
        List<String> customerOrderIds = soJdbcTemplate.queryForList(qyrCustomerOrderIdSql,String.class);
        String customerOrderIdStr = TransferStoreConstant.convertListToString(customerOrderIds);
        String qryWrongIdSql = "SELECT MSG_KEY  FROM ASYN_MSG_INFO WHERE MSG_KEY in ("+customerOrderIdStr+") and MSG_TOPIC = 'so_order_archgrpfinish_10091_customer' and    MSG_CONTENT like '%未查到对应的侦听%'";
        customerOrderIds= soJdbcTemplate.queryForList(qryWrongIdSql,String.class);
        String  isales = TransferStoreConstant.convertListToString(customerOrderIds);
        //查到最终没有进行合单的单子
        String qryIsaleSql = "select c.PROVISALE from customer_order_group_rel c where c.cust_order_id in ("+isales+")";
        List<String> isalesList = soJdbcTemplate.queryForList(qryIsaleSql,String.class);
        String isaleStr = TransferStoreConstant.convertListToString(isalesList);
        reTriggerByIsale(isaleStr);
    }


    public List<String> qryErrorCustomerOrder(String customerOrderStr){
        String sql = "SELECT MSG_KEY  FROM ASYN_MSG_INFO WHERE MSG_KEY in ("+customerOrderStr+") and MSG_TOPIC = 'so_order_archgrpfinish_10091_customer' and    MSG_CONTENT like '%侦听不存在%'";
        return soJdbcTemplate.queryForList(sql,String.class);
    }



    /**
    * @Description: 重新触发合单
    * @Param: []
    * @Author: dong.chao
    */
    public void reTriggerByIsale(String isale){
        String exeSql = "begin\n" +
                "  for rec in (\n" +
                "     select * from message_order mo where  mo.isale in ("+isale+") \n" +
                "    ) loop\n" +
                "    update message_order mo SET mo.process_state = 'W' where mo.id = rec.id;\n" +
                "    update listen_message_order lmo set lmo.deal_flag = 'D' where  lmo.msg_content_key = rec.id;\n" +
                "end loop;\n" +
                "end;";
        soJdbcTemplate.execute(exeSql);
    }

}
