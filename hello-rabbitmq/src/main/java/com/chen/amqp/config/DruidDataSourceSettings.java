package com.chen.amqp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Druid 连接池配置信息
 *
 * @Author LeifChen
 * @Date 2018-11-20
 */
@Getter
@Setter
@Component
@PropertySource("classpath:druid.properties")
public class DruidDataSourceSettings {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${druid.initialSize}")
    private Integer initialSize;

    @Value("${druid.minIdle}")
    private Integer minIdle;

    @Value("${druid.maxActive}")
    private Integer maxActive;

    @Value("${druid.maxWait}")
    private Integer maxWait;

    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;

    @Value("${druid.validationQuery}")
    private String validationQuery;

    @Value("${druid.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${druid.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${druid.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;

    @Value("${druid.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    @Value("${druid.filters}")
    private String filters;

    @Value("${druid.connectionProperties}")
    private String connectionProperties;

    @Value("${druid.useGlobalDataSourceStat}")
    private Boolean useGlobalDataSourceStat;

    public Properties getConnectionProperties() {
        Properties properties = new Properties();
        String[] entrys = connectionProperties.split(";");
        for (String entry : entrys) {
            String[] split = entry.split("=");
            properties.setProperty(split[0], split[1]);
        }
        return properties;
    }
}
