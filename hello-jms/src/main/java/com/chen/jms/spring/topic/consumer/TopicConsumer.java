package com.chen.jms.spring.topic.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主题模式：订阅者
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class TopicConsumer {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("consumer-topic.xml");
    }
}
