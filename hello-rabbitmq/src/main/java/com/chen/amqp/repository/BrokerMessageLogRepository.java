package com.chen.amqp.repository;

import com.chen.amqp.model.BrokerMessageLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * BrokerMessageLogRepository
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
public interface BrokerMessageLogRepository extends CrudRepository<BrokerMessageLog, String> {

    /**
     * 查询消息状态为0(发送中)，且已经超时的消息集合
     *
     * @param date
     * @return
     */
    @Query("select o from #{#entityName} o where o.status = '0' and o.nextRetry <= :date")
    List<BrokerMessageLog> listSendFailureAndTimeoutMessage(@Param("date") Date date);
}
