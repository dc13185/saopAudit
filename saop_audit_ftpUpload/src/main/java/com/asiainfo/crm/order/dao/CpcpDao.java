package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author: dong.chao
 * @create: 2019-12-16 10:19
 * @description:
 **/
@Repository
public class CpcpDao {

    @Autowired
    @Qualifier("cpcpJdbcTemplate")
    JdbcTemplate cpcpJdbcTemplate;

    public Map<String, Object> qryOfferIdbyOfferName(String offerName,String offerType){
        String sql = "select o.OFFER_ID,o.EXT_OFFER_ID from offer o where o.offer_name = '"+offerName+"' and offer_type = '"+offerType+"' limit 1";
        return cpcpJdbcTemplate.queryForMap(sql);

    }





}
