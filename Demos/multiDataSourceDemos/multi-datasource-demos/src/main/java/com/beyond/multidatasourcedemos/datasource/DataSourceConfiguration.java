package com.beyond.multidatasourcedemos.datasource;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "db1.spring.datasource")
    public DataSource db1(){
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "db2.spring.datasource")
    public DataSource db2(){
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public DataSource multiDataSource(@Qualifier("db1") DataSource db1,@Qualifier("db2") DataSource db2){
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.MySQL,db1);
        dataSourceMap.put(DataSourceType.H2,db2);
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setTargetDataSources(dataSourceMap);
        return multiDataSource;
    }


    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("multiDataSource") DataSource multiDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multiDataSource);
        return sqlSessionFactoryBean.getObject();
    }

}
