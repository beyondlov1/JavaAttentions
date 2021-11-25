package com.example;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author beyond
 * @date 2021/11/25
 */
@Configuration
public class Config {

    @Autowired
    private DataSourceProperties properties;

    @Bean(name = "ds1")//第一个数据源
    @Primary
    public DataSource jamesDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setUniqueResourceName("ds1");
        ds.setPoolSize(5);
        ds.setXaProperties(properties.getDatasource().get("ds1"));
        return ds;
    }

    @Bean(name = "ds2") //第二个数据源
    public DataSource peterDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = properties.getDatasource().get("ds2");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setUniqueResourceName("ds2");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }


    @Bean//创建事务管理器
    public JtaTransactionManager jtaTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }


    @Bean("ds1Template")//数据源实例
    public JdbcTemplate jamesJdbcTemplate(@Qualifier("ds1") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean("ds2Template")//数据源实例
    public JdbcTemplate peterJdbcTemplate(@Qualifier("ds2") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
