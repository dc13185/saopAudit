package com.asiainfo.crm.order.common;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.crm.ftp.common.DateUtil;
import com.asiainfo.crm.order.dao.MessageOrderDao;
import com.asiainfo.crm.order.util.FlieUtils;
import com.asiainfo.crm.order.util.HttpRemoteCallClient;
import com.asiainfo.crm.order.util.PropertiesUtil;
import com.ctc.wstx.util.DataUtil;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author: dong.chao
 * @create: 2019-12-12 10:02
 * @description: 定时错单处理
 *
 **/

@Component
public class MessageOrderHandle {

    @Autowired
    private MessageOrderDao messageOrderDao;

    public void finalReport() throws Exception {
        List<Map<String, Object>> messageOrderIds = messageOrderDao.qryMessageOrderIdByState("C1");
        StringBuilder custOrderId = new StringBuilder();
        for (int i = 0; i < messageOrderIds.size(); i++) {
            if(i == messageOrderIds.size()-1){
                custOrderId.append("'"+messageOrderIds.get(i).get("OL_ID")+"'");

            }else{
                custOrderId.append("'"+messageOrderIds.get(i).get("OL_ID")+"',");
            }
        }

        List<Map<String, Object>>   customerOrders = messageOrderDao.qryCustOrderIdByState(custOrderId.toString());
        for (Map<String, Object> customerOrder : customerOrders) {
            String customerOrderId =   ((BigDecimal)customerOrder.get("CUST_ORDER_ID")).toString();
            //进行报竣
            freeBackOnError(customerOrderId);
        }

    }

    public void freeBackOnError(String custOrderId) throws Exception {
        String traceId = inTraceIdSaop(custOrderId);
        if(StringUtils.isEmpty(traceId)){
            String content =  messageOrderDao.qryContentByCustOrderId(custOrderId);
            DocumentContext jsonContext = JsonPath.parse(content);
            List<Object> transIds = jsonContext.read("$.orderAttrs[?(@.attrId == 909077)].attrValue");
            if (transIds.size() > 0){
                traceId = (String) transIds.get(0);
            }
        }
        //拼接入参
        StringBuilder saopMsgSB = new StringBuilder();
        saopMsgSB.append("{\"contractRoot\":{\"svcCont\":{\"feedbackReq\":{\"coId\":\"");
        saopMsgSB.append(custOrderId);
        saopMsgSB.append("\",\"lanId\":\"");
        saopMsgSB.append("8421000");
        saopMsgSB.append("\"}},\"tcpCont\":{\"appKey\":\"SF00000017\"");
        saopMsgSB.append(",\"dstSysId\":\"SF00000000\"");
        saopMsgSB.append(",\"reqTime\":\"");
        // reqTime 请求时间为当前时间
        String reqTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        saopMsgSB.append(reqTime);
        saopMsgSB.append("\",\"sign\":\"***\"");
        saopMsgSB.append(",\"svcCode\":\"usb.orderFeedback\"");
        saopMsgSB.append(",\"transactionId\":\"");
        // transactionId 唯一编号，格式为SF00000006（前固定）20170801(中日期)0000000001（后十位取时间戳）
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(3);
        String transactionId = "SF00000006" + currentDate + timestamp;
        saopMsgSB.append(transactionId);
        saopMsgSB.append("\",\"version\":\"V1.0\"");
        saopMsgSB.append(",\"httpHeader\":{");
        saopMsgSB.append("\"traceId\":\"");
        saopMsgSB.append(traceId);
        saopMsgSB.append("\"}}}}");
        String url = "http://133.0.208.1:9700/saop-service/service/saop_exchange";
        String responseStr = HttpRemoteCallClient.callRemote(url, saopMsgSB.toString(), 0, 0, null, null);
        System.out.println(responseStr);
        String logFile = PropertiesUtil.getProperty("logFile");
        String logPath = PropertiesUtil.getProperty("logPath");
        String nowTime = DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT_D);
        String wirtStr="";
        if(responseStr.contains("撤单")){
             wirtStr =  nowTime+" 订单:"+custOrderId+"已被撤单，请进行异常报竣\r";
        }else{
             wirtStr =  nowTime+" 订单:"+custOrderId+"已报竣，请进行异常报竣\r";
        }
        FlieUtils.saveAsFileWriter(logPath+logFile,wirtStr);
    }

    public String inTraceIdSaop(String custOrderId) {
        StringBuilder path = new StringBuilder();
        StringBuilder resu = new StringBuilder();
        HashMap<String, Object> reul = new HashMap<>();
        List<Object> reuls = new ArrayList<>();
        reul.put("busiSign", Long.parseLong(custOrderId));
        reuls.add(reul);
        String url = "http://133.0.208.1:9700/flowlog-service/service/mon_flow-log_queryTraceIdByCustOrderId";
        try {
            String responseStr = HttpRemoteCallClient.callRemote(url, JSONObject.toJSONString(reuls), 0, 0, null, null);
            if (responseStr == null || !responseStr.contains("resultCode")) {
                responseStr = HttpRemoteCallClient.callRemote(url, JSONObject.toJSONString(reuls), 0, 0, null, null);
            }
            DocumentContext jsonResponse = JsonPath.parse(responseStr);
            String resultCode = jsonResponse.read("$.resultCode");
            if (resultCode != null && resultCode.length() > 0) {
                if (resultCode.equals("0")) {
                    Object traceId = jsonResponse.read("$.resultObject.traceId");
                    return String.valueOf(traceId);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }




}
