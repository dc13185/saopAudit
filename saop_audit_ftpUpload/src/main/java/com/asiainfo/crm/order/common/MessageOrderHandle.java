package com.asiainfo.crm.order.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.crm.ftp.common.DateUtil;
import com.asiainfo.crm.order.constant.RegularConstant;
import com.asiainfo.crm.order.constant.SqlConstant;
import com.asiainfo.crm.order.constant.TransferStoreConstant;
import com.asiainfo.crm.order.constant.UrlConstant;
import com.asiainfo.crm.order.dao.CodeMappingDao;
import com.asiainfo.crm.order.dao.CpcpDao;
import com.asiainfo.crm.order.dao.MessageOrderDao;
import com.asiainfo.crm.order.dao.RuleDao;
import com.asiainfo.crm.order.util.FlieUtils;
import com.asiainfo.crm.order.util.HttpRemoteCallClient;
import com.asiainfo.crm.order.util.PropertiesUtil;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    @Autowired
    private CodeMappingDao codeMappingDao;

    @Autowired
    private CpcpDao cpcpDao;

    @Autowired
    private RuleDao ruleDao;

    String logFile = PropertiesUtil.getProperty("logFile");
    String logPath = PropertiesUtil.getProperty("logPath");

    /**
     * @Description: 定时处理C1没有报竣的单子
     * @Param: []
     * @return: void
     * @Author: dong.chao
     * @Date: 2019/12/15
     */
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


    /**
     * @Description: 定时处理堵单、E1状态订单、需要异常报竣订单
     * @Param: []
     * @return: void
     * @Author: dong.chao
     * @Date: 2019/12/15
     */
    public void messageOrderTimingProcessing(){
        //处理在途
        messageOrderDao.execute(SqlConstant.WALL_ORDER_SQL);
        //处理E1
        messageOrderDao.execute(SqlConstant.ON_WAY_ORDER_SQL);
    }


    /**
     * @Description: 处理被拦截订单
     * @Param: []
     * @return: void
     * @Author: dong.chao
     * @Date: 2019/12/15
     */
    public void interceptMessageOrder() throws Exception {
        List<Map<String, Object>> messageOrders = messageOrderDao.qryInterceptMessageOrder();
        for (Map<String, Object> stringObjectMap : messageOrders) {
            String result = (String) stringObjectMap.get("PROCESS_RESULT");
            String id = ((BigDecimal) stringObjectMap.get("ID")).toString();
            if (result.contains("互斥")) {
                //查出message_order
                Map messageOrderInfo = messageOrderDao.qryMessageOrderInfoById(id);
                String inParamMsg = (String)messageOrderInfo.get("INCEPT_MSG");
                //先进行异常报竣
                dealOneFeeBack(messageOrderInfo,stringObjectMap);
                //主套餐 可选包
                String mainOfferName = "" ,mainOfferNameStr="", offerName = "" ,offerNameStr = "";
                Matcher matcher = RegularConstant.QRY_OFFER_NAME_PATTERN.matcher(result);
                if(matcher.find()){
                    offerNameStr = matcher.group(0).replaceAll("可选包\\[","").replaceAll("]与","");
                    offerName = offerNameStr.substring(0,offerNameStr.length()/2);
                }
                matcher = RegularConstant.QRY_MAIN_OFFER_NAME_PATTERN.matcher(result);
                if(matcher.find()){
                    mainOfferNameStr = matcher.group(0).replaceAll("套餐\\[","").replaceAll("]互斥","");
                    mainOfferName = mainOfferNameStr.substring(0,mainOfferNameStr.length()/2);
                }
                //开始查找extOfferId
                String  offerExtOfferIdRegular = String.format(RegularConstant.INTERCEPTSTRFORMATE,offerName);
                Pattern offerExtOfferIdPattern = Pattern.compile(offerExtOfferIdRegular);
                String extOfferId = "",mainExtOfferId = "";
                //获取截取字符串
                Matcher offerMatcher = offerExtOfferIdPattern.matcher(inParamMsg);
                if(offerMatcher.find()){
                    String interceptStr = offerMatcher.group(0);
                    extOfferId = org.apache.commons.lang3.StringUtils.substringBetween(interceptStr,"<EXT_PROD_OFFER_ID>", "</EXT_PROD_OFFER_ID>");
                }
                //开始查找主套餐extOfferId
                String  mainOfferExtOfferIdRegular = String.format(RegularConstant.INTERCEPTSTRFORMATE,mainOfferName);
                Pattern mainOfferExtOfferIdPattern = Pattern.compile(mainOfferExtOfferIdRegular);
                offerMatcher = mainOfferExtOfferIdPattern.matcher(inParamMsg);
                if(offerMatcher.find()){
                    String interceptStr = offerMatcher.group(0);
                    mainExtOfferId = org.apache.commons.lang3.StringUtils.substringBetween(interceptStr,"<EXT_PROD_OFFER_ID>", "</EXT_PROD_OFFER_ID>");
                }
                //定义出可选包id和主套餐id
                String offerId = "", mainOfferId = "";
                if(StringUtils.isNotBlank(extOfferId)){
                    offerId = codeMappingDao.qryCodeMappingByHocde(extOfferId);
                }else {
                    offerId = getOfferId(offerNameStr,inParamMsg);
                }
                if(StringUtils.isNotBlank(mainOfferId)){
                    mainOfferId = codeMappingDao.qryCodeMappingByHocde(mainExtOfferId);
                }else{
                    mainOfferId = getOfferId(mainOfferNameStr,inParamMsg);
                }

                //开始往规则库中差入数据
                if (StringUtils.isNotBlank(offerId) && StringUtils.isNotBlank(mainOfferId)) {
                    //往规则库插数据
                    String mainOfferStrList = ruleDao.qryMainOffers(offerId);
                    if (StringUtils.isNotEmpty(mainOfferStrList)) {
                        //如果没有的话，进行补充
                        if (!mainOfferStrList.contains(mainOfferId)) {
                            mainOfferStrList = mainOfferStrList + "," + mainOfferId;
                            ruleDao.updateRule(mainOfferStrList, offerId);
                            String str = "规则库添加可选包配置offerName:"+offerNameStr+" offerId:"+offerId+"与主套餐offerName:"+mainOfferNameStr+" offerId:"+mainOfferId+"\r" ;
                            System.out.println(str);
                            FlieUtils.saveAsFileWriter(logPath+logFile,str);
                        }
                    }else {
                        String idStr = ruleDao.getRuleByLastId();
                        int ruleId = Integer.parseInt(idStr);
                        ruleDao.insertRule(ruleId,offerId,offerNameStr,mainOfferId);
                        String str = "规则库添加可选包配置offerName:"+offerNameStr+" offerId:"+offerId+"与主套餐offerName:"+mainOfferNameStr+" offerId:"+mainOfferId+"\r" ;
                        System.out.println(str);
                        FlieUtils.saveAsFileWriter(logPath+logFile,str);
                    }
                }
            }
        }
    }





    /**
     * @Description: 送集团报竣
     * @Param: [custOrderId]
     * @return: void
     * @Author: dong.chao
     * @Date: 2019/12/15
     */
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
        String url = UrlConstant.SAOP_URL;
        String responseStr = HttpRemoteCallClient.callRemote(url, saopMsgSB.toString(), 0, 0, null, null);
        System.out.println(responseStr);
        String nowTime = DateUtil.getNowTime(DateUtil.DATE_FORMATE_STRING_DEFAULT_D);
        String wirtStr="";
        if(responseStr.contains("撤单")){
            Map<String,Object> messageOrder = messageOrderDao.qyrMessageOrderByOlId(custOrderId);
            String massageOrderId = ((BigDecimal)messageOrder.get("ID")).toString();
            Map<String,Object> messageOrderInfo = messageOrderDao.qryMessageOrderInfoById(massageOrderId);
            boolean flag = dealOneFeeBack(messageOrderInfo,messageOrder);
            if(flag){
                wirtStr =  nowTime+" 订单:"+custOrderId+"已被撤单，已进行异常报竣\r";
            }else{
                wirtStr =  nowTime+" 订单:"+custOrderId+"已被撤单，请进行异常报竣\r";
            }
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
        String url = UrlConstant.ENDTODEN_URL;
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




    /**
     * @Description: 进行异常报竣
     * @Param: [messageOrderInfo, messageOrder]
     * @return: boolean
     * @Author: dong.chao
     * @Date: 2019/12/15
     */
    private boolean dealOneFeeBack(Map messageOrderInfo,Map messageOrder){
        try {
            String inParamMsg = (String)messageOrderInfo.get("INCEPT_MSG");
            String massageOrderId = ((BigDecimal)messageOrder.get("ID")).toString();
            String processResult = (String)messageOrder.get("PROCESS_RESULT");
            String transactionId = (String)messageOrder.get("TRANSACTION_ID");
            String olId = (String)messageOrder.get("OL_ID");
            //异常报竣，如果olId不为空的，需要先制空
            if (StringUtils.isNotEmpty(olId)){
                String sql = "update message_order mo set mo.ol_id = '' where mo.id = '"+olId+"' ";
                messageOrderDao.execute(sql);
            }
            // 拼接报文(将远来的ServiceCode中替换为SVC-YCBJ)
            String regular = "<ServiceCode>[0-9a-zA-Z]{8}</ServiceCode>";
            String requestStr = inParamMsg.replaceAll(regular,"<ServiceCode>SVC-YCBJ</ServiceCode>");
            // 请求异常报竣请求
            String result = HttpRemoteCallClient.callRemote(UrlConstant.SAOP_URL, requestStr, 0, 0, null, null);
            if (org.apache.commons.lang.StringUtils.isEmpty(result)) {
                System.out.println("调用saop异常报竣返回报文为空");
                return false;
            }
            Map<String, Object> transactInfo = new HashMap<>();
            transactInfo.put("transacstionId",transactionId);
            if (result.contains("<RspCode>0000</RspCode>")){
                //调用成功 修改mssageOrder 状态
                Matcher mat = TransferStoreConstant.CODE_PATTERN.matcher(result);
                String  resultMsg = "";
                while (mat.find()) {
                    resultMsg = mat.group(0);
                }
                resultMsg = "异常报竣：" + resultMsg+"。原始处理结果："+processResult;
                if (resultMsg.length() > 2000){
                    resultMsg = resultMsg.substring(0,1999);
                }
                transactInfo.put("processResult",resultMsg);
                transactInfo.put("massageOrderId",massageOrderId);
                //异常报竣成功
                transactInfo.put("processState", "C2");
                //修改massage_order 状态
                messageOrderDao.updMessageOrderByFeeBack(transactInfo);
                //删除listen_massage_order记录转储到 历史表
                int m =messageOrderDao.insertListenMessageOrderHis(massageOrderId);
                if (m > 0){
                    messageOrderDao.deleteListenMessageOrder(massageOrderId);
                }
            }else{
                //异常报竣失败
                System.out.println("调用报竣接口接口异常，返回结果为:"+result+"");
                return false;
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }




    private String getOfferId(String offerName,String inParamMsg){
        //去查offerId
        String offerId = backOfferId(offerName, inParamMsg);
        //查不到，去找特殊配置
        if(StringUtils.isEmpty(offerId)){
            String specialOfferNames = PropertiesUtil.getProperty("specialOfferName");
            if(specialOfferNames.contains(offerName)){
                JSONArray specialOfferNamesObj = JSONArray.parseArray(specialOfferNames);
                for (Object specialOfferNameObj : specialOfferNamesObj) {
                    JSONObject specialOfferName = (JSONObject)specialOfferNameObj;
                    String specialOfferId = specialOfferName.getString("offerId");
                    String specialExtOfferId = specialOfferName.getString("extOfferId");
                    if(specialExtOfferId.contains(specialExtOfferId)){
                        offerId = specialOfferId;
                    }
                }
            }
        }
        return offerId;
    }


    private String backOfferId(String offerName,String inParamMsg){
        //如果没有查到就需要特殊处理了
        Map<String,Object> offer = cpcpDao.qryOfferIdbyOfferName(offerName);
        //找到查出来的省内ID
        String qryOfferId = ((BigDecimal)offer.get("OFFER_ID")).toString();
        //找到查出来的省内ID
        String qryExtOfferId = ((String)offer.get("EXT_OFFER_ID"));
        if(StringUtils.isNotEmpty(qryExtOfferId) && inParamMsg.contains(qryExtOfferId)){
            return qryOfferId;
        }
        if(StringUtils.isNotEmpty(qryOfferId) && inParamMsg.contains(qryOfferId)){
            return qryOfferId;
        }
        //如果没查到，返回null
        return null;
    }


    public static void main(String[] args) {
        String specialOfferNames = PropertiesUtil.getProperty("logFile");
        JSONArray specialOfferNamesObj = (JSONArray)JSONObject.parse(specialOfferNames);
        System.out.println();
    }



}
