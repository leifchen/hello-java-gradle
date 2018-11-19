package com.chen.jms.spring.queue.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 队列模式：生产者实现
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class QueueProducerServiceImpl implements QueueProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    Destination destination;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.send(destination, session -> session.createTextMessage(message));
        System.out.println("发送消息：" + message);
    }
}
