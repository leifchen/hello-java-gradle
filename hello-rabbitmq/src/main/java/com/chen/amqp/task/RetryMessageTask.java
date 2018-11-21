package com.chen.amqp.task;

import com.chen.amqp.constant.Constants;
import com.chen.amqp.model.BrokerMessageLog;
import com.chen.amqp.model.Order;
import com.chen.amqp.producer.OrderProducer;
import com.chen.amqp.repository.BrokerMessageLogRepository;
import com.chen.amqp.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 重发消息任务
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
@Slf4j
@Component
public class RetryMessageTask {

    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private BrokerMessageLogRepository brokerMessageLogRepository;

    /**
     * 启动完成3秒后开始执行，每隔10秒执行一次
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void retrySend() {
        log.debug("重发消息定时任务开始：");
        // 查询 status = 0 和 timeout 的消息日志
        List<BrokerMessageLog> messageLogs = brokerMessageLogRepository.listSendFailureAndTimeoutMessage(new Date());
        for (BrokerMessageLog messageLog : messageLogs) {
            log.debug("处理消息日志：{}", messageLog);
            if (messageLog.getTryCount() >= Constants.MAX_RETRY_COUNT) {
                // 更新状态为失败
                messageLog.setStatus(Constants.OrderSendStatus.SEND_FAILURE);
                brokerMessageLogRepository.save(messageLog);
            } else {
                // 进行重试，重试次数+1
                messageLog.setTryCount(messageLog.getTryCount() + 1);
                brokerMessageLogRepository.save(messageLog);
                Order order = JsonUtils.convertJsonToObject(messageLog.getMessage(), Order.class);
                try {
                    orderProducer.send(order);
                } catch (Exception ex) {
                    // 异常处理
                    log.error("消息发送异常：{}", ex);
                }
            }
        }
        log.debug("重发消息定时任务结束.");
    }
}
