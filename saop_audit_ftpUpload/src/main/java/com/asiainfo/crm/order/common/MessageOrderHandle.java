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
import com.asiainfo.crm.order.util.StringTools;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Clob;
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
        //处理异地销户的(88888)的不需要处理的
        messageOrderDao.execute(SqlConstant.WALL_ORDER_SQL);
        //处理E1
        messageOrderDao.execute(SqlConstant.ON_WAY_ORDER_SQL);
        //处理E1(电渠下的)
        messageOrderDao.execute(SqlConstant.ON_WAY_E1_ORDER_SQL);
        //处理A或W处理失败的
        messageOrderDao.execute(SqlConstant.ON_WAY_A_W_ORDER_SQL);
        //处理在途的单子
        messageOrderDao.qyrTheWayByIsale();
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
                    offerNameStr = matcher.group(0).replaceAll("可选包销售品\\[","").replaceAll("]与","");
                    offerName = offerNameStr.substring(0,offerNameStr.length()/2);
                }
                matcher = RegularConstant.QRY_MAIN_OFFER_NAME_PATTERN.matcher(result);
                if(matcher.find()){
                    mainOfferNameStr = matcher.group(0).replaceAll("套餐销售品\\[","").replaceAll("]互斥","");
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
                    offerId = getOfferId(offerNameStr,inParamMsg,"12");
                }
                if(StringUtils.isNotBlank(mainOfferId)){
                    mainOfferId = codeMappingDao.qryCodeMappingByHocde(mainExtOfferId);
                }else{
                    mainOfferId = getOfferId(mainOfferNameStr,inParamMsg,"11");
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
            try {
                String content =  messageOrderDao.qryContentByCustOrderId(custOrderId);
                DocumentContext jsonContext = JsonPath.parse(content);
                List<Object> transIds = jsonContext.read("$.orderAttrs[?(@.attrId == 909077)].attrValue");
                if (transIds.size() > 0){
                    traceId = (String) transIds.get(0);
                }
            }catch (Exception e){

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
                String sql = "update message_order mo set mo.ol_id = '' where mo.ol_id = '"+olId+"' ";
                messageOrderDao.update(sql);
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




    private String getOfferId(String offerName,String inParamMsg,String offerType){
        //去查offerId
        String offerId = backOfferId(offerName, inParamMsg,offerType);
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


    private String backOfferId(String offerName,String inParamMsg,String offerType){
        //如果没有查到就需要特殊处理了
        Map<String,Object> offer = cpcpDao.qryOfferIdbyOfferName(offerName,offerType);
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


    /** 
    * @Description: 强制竣工一批单子
    * @Param: [] 
    * @return: void 
    * @Author: dong.chao
    * @Date: 2019/12/30 
    */ 
    public void forceCompleted() throws Exception {
        List<Map<String, Object>> messageOrders = messageOrderDao.qyrErrorMessageOrder();
        for (Map<String, Object> messageOrder : messageOrders) {
            String  customerOrderId =  (String)messageOrder.get("OL_ID");
            String  transactionId =  (String)messageOrder.get("TRANSACTION_ID");
            String requestStr = "<manualFeedBack><orderInfo><transactionId>"+transactionId+"</transactionId></orderInfo></manualFeedBack>";
            String url = UrlConstant.SAOP_URL;
            String responseStr = HttpRemoteCallClient.callRemote(url, requestStr, 0, 0, null, null);
            if (responseStr.contains("成功")){
                String str = "订单流水"+transactionId+"已进行强制报竣，原始情况为:报竣环节处理失败！环节：8，错误信息：调受理查询报竣信息失败，存在未竣工的集团订单项!";
                messageOrderDao.insertLog(customerOrderId,transactionId,str);
            }
        }
        //重新触发合单的
        useOrderFeedback();
    }


    /**
     * @Description: 重新触发合单
     * @Param: []
     * @return: void
     * @Author: dong.chao
     * @Date: 2019/11/12
     */
    public void useOrderFeedback() throws Exception {
        List<Map<String, Object>> errorMessageOrderInfos =  messageOrderDao.qryErrorMessageOrderInfo();
        for (Map<String, Object> errorMessageOrderInfo : errorMessageOrderInfos) {
            String infoStr = (String)errorMessageOrderInfo.get("INCEPT_MSG");
            if(infoStr.contains("getProdOfferInstIdByAccNbr")){
                continue;
            }
            //拿这个去调转换模板
            infoStr = infoStr
                    .replaceAll("\"","\'").replaceAll("\n","")
                    .replaceAll("134146789","134146977")
                    .replaceAll("134146791","134146981")
                    .replaceAll("134146794","134146987")
                    .replaceAll("134146788","134146980")
                    .replaceAll("134146792","134146985")

            ;
            String format = "[\"%s\",\"0600000015\",\"\",\"false\"]";
            //格式化入参
            String reqStr = String.format(format,infoStr);
            String resultStr = HttpRemoteCallClient.callRemote(UrlConstant.MESSAGE_CONVERT_ENGINE_URL, reqStr, 0, 0, null, null);
            //返回格式化，修正出参，调合单接口
            JSONObject resultObj = JSONObject.parseObject(resultStr);
            JSONObject customerOrder = resultObj.getJSONObject("requestObject").getJSONObject("customerOrder");
            //isale流水
            String isaleNbr = customerOrder.getString("isaleNbr");
            JSONArray offerOrderItems = customerOrder.getJSONArray("offerOrderItems");
            //循环
            for (Object offerOrderItemObj : offerOrderItems) {
                JSONObject offerOrderItem = (JSONObject)offerOrderItemObj;
                //处理 ordOfferProdInstRels
                JSONArray ordOfferProdInstRels = offerOrderItem.getJSONArray("ordOfferProdInstRels");
                JSONArray ordOfferProdInstRelsClone = (JSONArray)ordOfferProdInstRels.clone();
                for (Object ordOfferProdInstRelObj : ordOfferProdInstRelsClone) {
                    JSONObject ordOfferProdInstRel = (JSONObject)ordOfferProdInstRelObj;
                    String prodId = ordOfferProdInstRel.getString("prodId");
                    if (StringUtils.isEmpty(prodId)){
                        ordOfferProdInstRels.remove(ordOfferProdInstRelObj);
                    }
                }

                //处理 ordOfferInst
                JSONObject ordOfferInst = offerOrderItem.getJSONArray("ordOfferInsts").getJSONObject(0);
                String offerId = ordOfferInst.getString("offerId");
                //offerId为空的则为需要修的
                if(!StringUtils.isEmpty(offerId)){
                    continue;
                }
                // 看这个值去 Code_mapping表去找 不存在说明 这个销售品没有了
                String assistExtOfferId = ordOfferInst.getString("assistExtOfferId");
                String offerName = ordOfferInst.getString("offerName");
                String pCode = "";
                try {
                    pCode = messageOrderDao.qryPcodeByHcode(assistExtOfferId);
                }catch (Exception e){

                }
                if(StringUtils.isEmpty(pCode)){
                    System.out.println("销售品名称["+offerName+"]，编码:"+pCode+"在Code_mapping表中找不到！！！！！");
                    continue;
                }
                //改掉offerId
                ordOfferInst.put("offerId",pCode);
            }
            String feedbackReqStr = resultObj.toJSONString();
            String responseStr =  HttpRemoteCallClient.callRemote(UrlConstant.SAVE_CUSTOMER_ORDER_URL, feedbackReqStr, 0, 0, null, null);
            JSONObject responseStrObj = JSONObject.parseObject(responseStr);
            //获取messageOrderId
            String  customerOrderId = responseStrObj.getJSONObject("resultObject").getString("customerOrderId");
            String  messageOrderId =  ((BigDecimal)errorMessageOrderInfo.get("ID")).toString();
            messageOrderDao.updateMessageOrderTemp(customerOrderId,messageOrderId);
            String transactionId = (String)errorMessageOrderInfo.get("TRANSACTION_ID");
            String str = "订单"+customerOrderId+"进行重新触发合单";
            if (responseStr.contains("成功")){
                messageOrderDao.insertLog(customerOrderId,transactionId,str);
            }
        }
        qryWrongOrder();
    }

    /**
    * @Description: 查询状态为E1 但是已经合单过了的
    * @Param: []
    * @return: void
    * @Author: dong.chao
    * @Date: 2019/12/31
    */
    public void qryWrongOrder(){
       messageOrderDao.qryWrongOrder();
    }






}
