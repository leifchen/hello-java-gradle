package com.chen.jms.spring.queue.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 队列模式：生产者
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class QueueProducer {

    private static final int COUNT = 100;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer-queue.xml");
        QueueProducerService service = context.getBean(QueueProducerService.class);

        for (int i = 0; i < COUNT; i++) {
            service.sendMessage("test" + i);
        }
        context.close();
    }
}
