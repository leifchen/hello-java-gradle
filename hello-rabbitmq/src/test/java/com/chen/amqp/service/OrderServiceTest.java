package com.chen.amqp.service;

import com.chen.amqp.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * OrderServiceTest
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        Order order = new Order("测试订单1", System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.create(order);
    }
}