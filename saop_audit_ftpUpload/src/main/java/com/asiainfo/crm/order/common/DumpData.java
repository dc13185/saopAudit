package com.asiainfo.crm.order.common;

import com.al.common.utils.DateUtil;
import com.asiainfo.crm.order.constant.TransferStoreConstant;
import com.asiainfo.crm.order.dao.DumpDao;
import com.asiainfo.crm.order.domain.DumpConfig;
import com.asiainfo.crm.order.domain.PartitionEntity;
import com.asiainfo.crm.order.util.FlieUtils;
import com.asiainfo.crm.order.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: dong.chao
 * @create: 2019-12-13 14:53
 * @description: 转储数据
 **/

@Component
public class DumpData {

    @Autowired
    private DumpDao dumpDao;

    /** 
    * @Description: 转储messageOrder 系列表
    * @Param: [] 
    * @return: void 
    * @Author: dong.chao
    * @Date: 2019/12/13 
    */ 
    public void dumpMessageOrderData() throws ParseException {
          //获取所有table
          List<String> tableNames =  TransferStoreConstant.getAllDumpDataTables();
          for (String table : tableNames) {
              //获取指定时间，以Message_Order主表为主
              DumpConfig dumpConfig = dumpDao.getDumpConfigByTableName(table);
              //获取分区集合
              List<String> partitonNames = getPartitonNames(dumpConfig.getTableName(),dumpConfig.getStartDay(),dumpConfig.getEndDay());
              if(TransferStoreConstant.getDumpDataTables().contains(table)){
                  //如果table需要转储，就进行转储
                  dumpDataMethod(table,partitonNames);
              }else{
                  //不需要转储的 直接删除分区
                  deleteDataMethod(table,partitonNames);
              }
          }

    }
    
    
    /** 
    * @Description: 获取分区
    * @Param: [tableNames, startDay, endDay] 
    * @return: java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>> 
    * @Author: dong.chao
    * @Date: 2019/12/13 
    */ 
    protected List<String> getPartitonNames(String tableName, int startDay, int endDay) throws ParseException {
        List<String> partitionNames = new ArrayList<String> ();
        //获取所有分区
        List<PartitionEntity> partitionEntityList = dumpDao.qryPartitionName(tableName);
        for (PartitionEntity partitionEntity : partitionEntityList) {
            String highValue = partitionEntity.getHighValue().substring(10, 20);
            String partitionName = partitionEntity.getPartitionName();
            //获取前一天后一天
            Calendar calStart = Calendar.getInstance();
            calStart.add(Calendar.DATE, -startDay + 1);
            Date startTime = calStart.getTime();
            Calendar calEnd = Calendar.getInstance();
            calEnd.add(Calendar.DATE, -endDay + 1);
            Date endTime = calEnd.getTime();
            System.out.println();
            Date highValueDate = null;
            Date startTemp = null;
            Date endTemp = null;
            startTemp = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(startTime));
            endTemp = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(endTime));
            highValueDate = new SimpleDateFormat("yyyy-MM-dd").parse(highValue);
            //获取指定日期范围
            if (endTemp.compareTo(highValueDate) >= 0 && startTemp.compareTo(highValueDate) <= 0 && !"PARTITION_BEFORE".equals(partitionName)) {
                partitionNames.add(partitionName);
            }
        }
        
        return partitionNames;
    }



    /**
    * @Description: 转储数据
    * @Param: [tableName:表名, partitonNames:分区集合]
    * @return: void
    * @Author: dong.chao
    * @Date: 2019/12/13
    */
    public void dumpDataMethod(String tableName,List<String> partitonNames){
        String logFile = PropertiesUtil.getProperty("logFile");
        String logPath = PropertiesUtil.getProperty("logPath");
        String format = DateUtil.getNow(DateUtil.DATE_FORMATE_STRING_A)+ ":%s转储分区%s:转储%s条数据,删除%s条数据";
        //获取历史表
        for (String partitonName : partitonNames) {
            int i = 0 , y = 0;
            if(tableName.equals(TransferStoreConstant.MESSAGE_ORDER)){
                i = dumpDao.dumpMessageOrder(partitonName);
                y = dumpDao.delMessageOrder(partitonName);
            }else if(tableName.equals(TransferStoreConstant.MESSAGE_ORDER_INFO)){
                i = dumpDao.dumpMessageOrderInfo(partitonName);
                y = dumpDao.delMessageOrderInfo(partitonName);
            }
            String str =  String.format(format,tableName,partitonName,i,y);
            //FlieUtils.saveAsFileWriter(logPath+logFile,str);
        }


    }


    public void deleteDataMethod(String table,List<String> partitonNames){
        String logFile = PropertiesUtil.getProperty("logFile");
        String logPath = PropertiesUtil.getProperty("logPath");
        String format = DateUtil.getNow(DateUtil.DATE_FORMATE_STRING_A)+ ":%s删除分区%s";
        //直接删除分区
        for (String partitonName : partitonNames) {
            String str = String.format(format,table,partitonName);
            dumpDao.deltePartition(table,partitonName);
            FlieUtils.saveAsFileWriter(logPath+logFile,str);
        }

    }

}
