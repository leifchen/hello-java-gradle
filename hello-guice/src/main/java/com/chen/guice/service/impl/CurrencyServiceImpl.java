package com.chen.guice.service.impl;

import com.chen.guice.service.CurrencyService;

import java.util.Arrays;
import java.util.List;

/**
 * CurrencyServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public List<String> getSupportedCurrency() {
        return Arrays.asList("CNY", "USD", "EUR");
    }
}
