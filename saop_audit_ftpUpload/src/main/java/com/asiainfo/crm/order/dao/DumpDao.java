package com.asiainfo.crm.order.dao;

import com.asiainfo.crm.order.domain.DumpConfig;
import com.asiainfo.crm.order.domain.PartitionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author: dong.chao
 * @create: 2019-12-13 15:01
 * @description:
 **/

@Repository
public class DumpDao {

    @Autowired
    @Qualifier("saopJdbcTemplate")
    JdbcTemplate saopJdbcTemplate;


    public DumpConfig getDumpConfigByTableName(String tableName){
        String  sql = "SELECT T.TABLE_NAME,T.START_DAY,T.END_DAY FROM DUMP_CONFIG T WHERE UPPER(T.TABLE_NAME)=UPPER('"+tableName+"') and rownum = 1";
        return saopJdbcTemplate.queryForObject(sql, new RowMapper<DumpConfig>() {
            @Override
            public DumpConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
                DumpConfig dumpConfig = new DumpConfig()
                        .setStartDay(rs.getInt("START_DAY"))
                        .setEndDay(rs.getInt("END_DAY"))
                        .setTableName(rs.getString("TABLE_NAME"));
                return dumpConfig;
            }
        });
    }

    public List<PartitionEntity> qryPartitionName(String tableName){
        String sql = "select t.partition_name,t.high_value from user_tab_partitions t where t.table_name = '"+tableName.toUpperCase()+"' ";
        return saopJdbcTemplate.query(sql, new RowMapper<PartitionEntity>() {
            @Override
            public PartitionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                PartitionEntity partitionEntity = new PartitionEntity()
                        .setPartitionName(rs.getString("PARTITION_NAME"))
                        .setHighValue(rs.getString("HIGH_VALUE"));
                return partitionEntity;
            }
        });
    }


    public int dumpMessageOrder(String partitionName){
        String sql = "insert into MESSAGE_ORDER_STG  select * from MESSAGE_ORDER  partition("+partitionName+")  where  process_state = 'C2'";
        return saopJdbcTemplate.update(sql);
    }

    public int delMessageOrder(String partitionName){
        String sql = "delete from MESSAGE_ORDER mo where mo.id in (  select id from MESSAGE_ORDER  partition("+partitionName+") where process_state = 'C2'  )";
        return saopJdbcTemplate.update(sql);
    }
    
    /** 
    * @Description: 转储MessageOrder表，其中只转对应MessageOrder状态为C2的
    * @Param: [partitionName] 
    * @return: int 
    * @Author: dong.chao
    * @Date: 2019/12/15 
    */ 
    public int dumpMessageOrderInfo(String partitionName){
        String sql = "insert into MESSAGE_ORDER_INFO_STG " +
                " select * from message_order_info moi where moi.id in (   " +
                " select id from " +
                "(select id,process_state  from message_order_stg  union all select id,process_state  from message_order ) " +
                " where id in ( select id from MESSAGE_ORDER_info   partition("+partitionName+")) and process_state = 'C2' )" ;
        return saopJdbcTemplate.update(sql);
    }

    public int delMessageOrderInfo(String partitionName){
        String sql = "delete  message_order_info moi where moi.id in (  " +
                "select id  from " +
                " (select id,process_state  from message_order_stg  union all select id,process_state  from message_order ) " +
                " where id in ( select id from MESSAGE_ORDER_info   partition("+partitionName+")) and process_state = 'C2' )" ;
        return saopJdbcTemplate.update(sql);
    }


    /** 
    * @Description: 删除分区 ，重建索引
    * @Param: [tableName, partitionName] 
    * @return: void 
    * @Author: dong.chao
    * @Date: 2019/12/15 
    */ 
    public void deltePartition(String tableName,String partitionName){
        String sql = "alter table "+tableName+" drop partition "+partitionName+"   UPDATE GLOBAL INDEXES";
        saopJdbcTemplate.execute(sql);
    }



}
