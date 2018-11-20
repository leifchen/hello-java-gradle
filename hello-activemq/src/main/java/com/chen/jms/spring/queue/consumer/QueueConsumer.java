package com.chen.jms.spring.queue.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 队列模式：消费者
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class QueueConsumer {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("consumer-queue.xml");
    }
}
