package com.chen.jms.spring.topic.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主题模式：发布者
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class TopicProducer {

    private static final int COUNT = 100;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer-topic.xml");
        TopicProducerService service = context.getBean(TopicProducerService.class);

        for (int i = 0; i < COUNT; i++) {
            service.sendMessage("test" + i);
        }
        context.close();
    }
}
