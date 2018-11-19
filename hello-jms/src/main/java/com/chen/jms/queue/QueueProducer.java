package com.chen.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列模式：生产者
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public class QueueProducer {
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "queue-test";
    private static final int COUNT = 100;

    public static void main(String[] args) throws JMSException {
        // 1.创建 ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

        // 2.创建 Connection
        Connection connection = connectionFactory.createConnection();

        // 3.启动连接
        connection.start();

        // 4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5.创建一个目标
        Destination destination = session.createQueue(QUEUE_NAME);

        // 6.创建一个发布者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < COUNT; i++) {
            // 7.创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            // 8.发布消息
            producer.send(textMessage);

            System.out.println("发送消息：" + textMessage.getText());
        }

        // 9.关闭连接
        connection.close();
    }
}
