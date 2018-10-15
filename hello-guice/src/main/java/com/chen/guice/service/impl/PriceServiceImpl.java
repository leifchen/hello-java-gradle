package com.chen.guice.service.impl;

import com.chen.guice.service.PriceService;

import java.math.BigDecimal;

/**
 * PriceServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class PriceServiceImpl implements PriceService {

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.TEN;
    }
}
