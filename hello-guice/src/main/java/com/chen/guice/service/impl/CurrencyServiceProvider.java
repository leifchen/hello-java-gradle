package com.chen.guice.service.impl;

import com.chen.guice.service.CurrencyService;
import com.google.inject.Provider;

/**
 * CurrencyServiceProvider
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class CurrencyServiceProvider implements Provider<CurrencyService> {

    @Override
    public CurrencyService get() {
        return new CurrencyServiceImpl();
    }
}
