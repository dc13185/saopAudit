package com.asiainfo.crm.order.common;

import com.asiainfo.crm.order.dao.AuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: dong.chao
 * @create: 2019-12-13 10:25
 * @description: 临时处理类
 **/

@Component
public class TemporaryProcessing {

    @Autowired
    private AuditDao auditDao;

    public void temporary(){
        auditDao.updateListenOrigin();
    }




}
