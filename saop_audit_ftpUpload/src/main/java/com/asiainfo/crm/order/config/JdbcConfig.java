package com.asiainfo.crm.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.asiainfo.crm.order.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * @author: dong.chao
 * @create: 2019-12-12 10:24
 * @description: 数据源配置
 **/

@Component
public class JdbcConfig {


    @Bean(name="saopDataSource")
    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(PropertiesUtil.getProperty("saop.driverClass"));
        dataSource.setUrl(PropertiesUtil.getProperty("saop.url"));
        dataSource.setUsername(PropertiesUtil.getProperty("saop.user"));
        dataSource.setPassword(PropertiesUtil.getProperty("saop.password"));
        return dataSource;
    }


    @Bean(name="soDataSource")
    public DataSource createSoDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(PropertiesUtil.getProperty("so.driverClass"));
        dataSource.setUrl(PropertiesUtil.getProperty("so.url"));
        dataSource.setUsername(PropertiesUtil.getProperty("so.user"));
        dataSource.setPassword(PropertiesUtil.getProperty("so.password"));
        return dataSource;
    }


    @Bean(name="indivDataSource")
    public DataSource createIndivDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(PropertiesUtil.getProperty("indiv.driverClass"));
        dataSource.setUrl(PropertiesUtil.getProperty("indiv.url"));
        dataSource.setUsername(PropertiesUtil.getProperty("indiv.user"));
        dataSource.setPassword(PropertiesUtil.getProperty("indiv.password"));
        return dataSource;
    }

    @Bean(name="cpcpDataSource")
    public DataSource createCpcpDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(PropertiesUtil.getProperty("cpcp.driverClass"));
        dataSource.setUrl(PropertiesUtil.getProperty("cpcp.url"));
        dataSource.setUsername(PropertiesUtil.getProperty("cpcp.user"));
        dataSource.setPassword(PropertiesUtil.getProperty("cpcp.password"));
        return dataSource;
    }



    @Bean(name="saopJdbcTemplate")
    public JdbcTemplate createJdbcTemplate(@Qualifier("saopDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="soJdbcTemplate")
    public JdbcTemplate createSoJdbcTemplate(@Qualifier("soDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="indivJdbcTemplate")
    public JdbcTemplate createIndivJdbcTemplate(@Qualifier("indivDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="auditJdbcTemplate")
    public JdbcTemplate createAuditJdbcTemplate(@Qualifier("saopaudit")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="cpcpJdbcTemplate")
    public JdbcTemplate createCpcpJdbcTemplate(@Qualifier("cpcpDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


}
