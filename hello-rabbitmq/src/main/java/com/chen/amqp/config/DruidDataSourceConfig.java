package com.chen.amqp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid 连接池配置
 *
 * @Author LeifChen
 * @Date 2018-11-20
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    private final DruidDataSourceSettings druidSettings;

    public DruidDataSourceConfig(DruidDataSourceSettings druidSettings) {
        this.druidSettings = druidSettings;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 配置 Druid 连接池属性
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidSettings.getDriverClassName());
        dataSource.setUrl(druidSettings.getUrl());
        dataSource.setUsername(druidSettings.getUsername());
        dataSource.setPassword(druidSettings.getPassword());
        dataSource.setInitialSize(druidSettings.getInitialSize());
        dataSource.setMinIdle(druidSettings.getMinIdle());
        dataSource.setMaxActive(druidSettings.getMaxActive());
        dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidSettings.getValidationQuery());
        dataSource.setTestWhileIdle(druidSettings.getTestWhileIdle());
        dataSource.setTestOnBorrow(druidSettings.getTestOnBorrow());
        dataSource.setTestOnReturn(druidSettings.getTestOnReturn());
        dataSource.setPoolPreparedStatements(druidSettings.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxOpenPreparedStatements());
        dataSource.setFilters(druidSettings.getFilters());
        dataSource.setConnectProperties(druidSettings.getConnectionProperties());
        return dataSource;
    }

    /**
     * 配置 JPA 事务
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
}
