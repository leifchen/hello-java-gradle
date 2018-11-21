package com.chen.amqp.repository;

import com.chen.amqp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
