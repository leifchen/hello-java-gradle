package com.chen.amqp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息日志
 *
 * @Author LeifChen
 * @Date 2018-11-20
 */
@Getter
@Setter
@ToString
@Entity(name = "broker_message_log")
public class BrokerMessageLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String messageId;

    private String message;

    private Integer tryCount;

    private String status;

    private Date nextRetry;

    private Date gmtCreate;

    private Date gmtModified;
}
