package com.chen.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ElasticSearch配置
 *
 * @Author LeifChen
 * @Date 2018-08-21
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {

    /**
     * 连接 Elastic Search 客户端
     *
     * @return
     */
    @Bean
    public TransportClient client() {
        InetSocketTransportAddress node;
        TransportClient client = null;
        try {
            node = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
            Settings settings = Settings.builder()
                    .put("cluster.name", "leifchen")
                    .build();
            client = new PreBuiltTransportClient(settings);
            client.addTransportAddress(node);
        } catch (UnknownHostException e) {
            log.error("ElasticSearch TransportClient create error!!!", e);
        }

        return client;
    }
}
