package com.chen.amqp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 订单
 *
 * @Author LeifChen
 * @Date 2018-11-20
 */
@Getter
@Setter
@ToString
@Entity(name = "t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String messageId;

    public Order() {
    }

    public Order(String name, String messageId) {
        this.name = name;
        this.messageId = messageId;
    }
}
