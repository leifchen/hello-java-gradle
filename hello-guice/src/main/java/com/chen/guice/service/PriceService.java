package com.chen.guice.service;

import java.math.BigDecimal;

/**
 * PriceService
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public interface PriceService {

    /**
     * 获取价格
     *
     * @return
     */
    BigDecimal getPrice();
}
