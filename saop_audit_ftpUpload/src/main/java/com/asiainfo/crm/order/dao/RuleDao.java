package com.asiainfo.crm.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: dong.chao
 * @create: 2019-12-16 11:24
 * @description:
 **/

@Repository
public class RuleDao {

    @Autowired
    @Qualifier("indivJdbcTemplate")
    JdbcTemplate saopJdbcTemplate;

    public String qryMainOffers(String offerId){
        String sql = "select t.param4 from so_indiv.rule_param_hblocal t where t.rule_code='PACKET_OFFER_LIMIT'  and t.param1 = '"+offerId+"'";
        return  saopJdbcTemplate.queryForObject(sql,String.class);
    }


    public int updateRule(String mainOfferStr,String offerId){
        String sql = "update  so_indiv.rule_param_hblocal r set r.param4 = '"+mainOfferStr+"' where r.rule_code='PACKET_OFFER_LIMIT'  and r.param1 = '"+offerId+"'";
        return  saopJdbcTemplate.update(sql);
    }

    public String getRuleByLastId(){
        String sql = "select * from (select t.rule_param_id from so_indiv.rule_param_hblocal t where t.rule_code='PACKET_OFFER_LIMIT'  order by t.rule_param_id desc) where rownum = 1";
        return saopJdbcTemplate.queryForObject(sql,String.class);
    }


    public void insertRule(int id,String offerId,String offerNameStr,String mainOfferId){
        String insertSql = "insert into so_indiv.rule_param_hblocal (RULE_PARAM_ID, REGION_ID, RULE_CODE, PARAM_DESC, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, PARAM6, STATUS_CD, CREATE_DATE, REMARK, CONFIG_TYPE)" +
                "values ('" + (id + 1) + "', '8420000', 'PACKET_OFFER_LIMIT', '可选包与主套餐互斥关系配置', '" + offerId + "', '" + offerNameStr + "', null, '" + mainOfferId + "', null, null, '1000', to_date('02-11-2019', 'dd-mm-yyyy'), 'param1配置的可选包id，param4配置的主套餐id', null)";
        saopJdbcTemplate.execute(insertSql);
    }


}
