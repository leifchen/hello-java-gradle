package com.chen.jms.spring.topic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 主题模式：发布者实现
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class TopicProducerServiceImpl implements TopicProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name = "topicDestination")
    Destination destination;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.send(destination, session -> session.createTextMessage(message));
        System.out.println("发送消息：" + message);
    }
}
