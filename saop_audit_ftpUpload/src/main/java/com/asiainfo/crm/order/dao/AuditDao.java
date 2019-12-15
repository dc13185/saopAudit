package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: dong.chao
 * @create: 2019-12-13 10:31
 * @description: 稽核SAOP
 **/

@Component
public class AuditDao {

    @Autowired
    @Qualifier("auditJdbcTemplate")
    JdbcTemplate auditJdbcTemplate;

    public void updateListenOrigin(){
        String sql = "update listen_audit_data_origin la set la.deal_flag = 'D' where la.msg_id in  (select * from  ( select l.msg_id from listen_audit_data_origin l where l.deal_flag = 'F'  and l.remark like '%正在上传文件%' order by l.sort_dt desc ) where rownum <= 20)";
        int i = auditJdbcTemplate.update(sql);
        System.out.println(i);
    }

}
