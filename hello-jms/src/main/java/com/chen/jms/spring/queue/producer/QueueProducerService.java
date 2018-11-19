package com.chen.jms.spring.queue.producer;

/**
 * 队列模式：生产者接口
 *
 * @Author LeifChen
 * @Date 2018-11-16
 */
public interface QueueProducerService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendMessage(String message);
}
