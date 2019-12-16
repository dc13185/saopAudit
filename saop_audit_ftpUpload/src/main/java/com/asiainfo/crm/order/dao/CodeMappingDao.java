package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: dong.chao
 * @create: 2019-12-16 09:56
 * @description:
 **/
@Repository
public class CodeMappingDao {

    @Autowired
    @Qualifier("saopJdbcTemplate")
    JdbcTemplate saopJdbcTemplate;

    public String qryCodeMappingByHocde(String offerCode){
        String sql = "select cm.p_code from code_mapping cm where cm.h_code = '"+offerCode+"' and cm.map_domain = 'PROD_OFFER.EXT_OFFER_NBR_DOWN' and rownum = 1 ";
        return saopJdbcTemplate.queryForObject(sql,String.class);

    }




}
