package com.chen.amqp.producer;

import com.chen.amqp.constant.Constants;
import com.chen.amqp.model.Order;
import com.chen.amqp.repository.BrokerMessageLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 订单：生产者
 *
 * @Author LeifChen
 * @Date 2018-11-20
 */
@Slf4j
@Component
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    private final BrokerMessageLogRepository brokerMessageLogRepository;

    public OrderProducer(RabbitTemplate rabbitTemplate,
                         BrokerMessageLogRepository brokerMessageLogRepositor) {

        this.rabbitTemplate = rabbitTemplate;
        this.brokerMessageLogRepository = brokerMessageLogRepositor;
    }

    /**
     * 回调函数：confirm确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData : {}" + correlationData);
            String messageId = correlationData.getId();
            if (ack) {
                // 如果confirm返回成功，则进行更新
                brokerMessageLogRepository.findById(messageId).ifPresent(brokerMessageLog -> {
                    brokerMessageLog.setStatus(Constants.OrderSendStatus.SEND_SUCCESS);
                    brokerMessageLogRepository.save(brokerMessageLog);
                });
            } else {
                // 失败则进行具体的后续操作：重试或者补偿等
                log.error("异常处理...");
            }
        }
    };

    /**
     * 发送消息
     *
     * @param order
     */
    public void send(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange", "order.100", order, correlationData);
    }
}
