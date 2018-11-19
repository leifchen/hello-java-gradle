package com.chen.jms.spring.topic.producer;

/**
 * 主题模式：发布者接口
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public interface TopicProducerService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendMessage(String message);
}
