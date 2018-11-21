package com.chen.amqp.service;

import com.chen.amqp.constant.Constants;
import com.chen.amqp.model.BrokerMessageLog;
import com.chen.amqp.model.Order;
import com.chen.amqp.producer.OrderProducer;
import com.chen.amqp.repository.BrokerMessageLogRepository;
import com.chen.amqp.repository.OrderRepository;
import com.chen.amqp.util.JsonUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * OrderService
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BrokerMessageLogRepository brokerMessageLogRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository,
                        BrokerMessageLogRepository brokerMessageLogRepository,
                        OrderProducer orderProducer) {

        this.orderRepository = orderRepository;
        this.brokerMessageLogRepository = brokerMessageLogRepository;
        this.orderProducer = orderProducer;
    }

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @Transactional(rollbackOn = Exception.class)
    public void create(Order order) {
        // 业务数据入库
        orderRepository.save(order);
        // 消息日志入库
        BrokerMessageLog messageLog = new BrokerMessageLog();
        messageLog.setMessageId(order.getMessageId());
        messageLog.setMessage(JsonUtils.convertObjectToJson(order));
        messageLog.setTryCount(0);
        messageLog.setStatus(Constants.OrderSendStatus.SENDING);
        messageLog.setNextRetry(DateUtils.addMinutes(new Date(), Constants.ORDER_TIMEOUT));
        brokerMessageLogRepository.save(messageLog);
        // 发送消息
        orderProducer.send(order);
    }
}
