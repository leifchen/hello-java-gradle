package com.chen.guice.service;

import java.util.List;

/**
 * CurrencyService
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public interface CurrencyService {

    /**
     * 支持的币种
     *
     * @return
     */
    List<String> getSupportedCurrency();
}
