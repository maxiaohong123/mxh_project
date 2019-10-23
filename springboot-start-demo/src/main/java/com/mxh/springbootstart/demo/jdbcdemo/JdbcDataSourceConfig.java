package com.mxh.springbootstart.demo.jdbcdemo;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db1")
    public DataSourceProperties db1dataSourceProperties(){
         return  new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db2")
    public DataSourceProperties db2dataSourceProperties(){
        return  new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource db1dataSource()
    {
        return db1dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource db2dataSource()
    {
        return db2dataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean(name="db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(){
        return new JdbcTemplate(db1dataSource());
    }

    @Bean(name="db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(){
        return new JdbcTemplate(db2dataSource());
    }
}
