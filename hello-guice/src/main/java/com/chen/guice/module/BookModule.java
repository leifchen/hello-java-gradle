package com.chen.guice.module;

import com.chen.guice.dao.BookDao;
import com.chen.guice.dao.impl.BookDaoImpl;
import com.chen.guice.service.*;
import com.chen.guice.service.impl.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

/**
 * BookModule
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ChinaModule());
        install(new GlobalModule());

        // 实例绑定
        bind(BookDao.class).toInstance(new BookDaoImpl());
        // 类名绑定
        bind(BookService.class).to(BookServiceImpl.class);
        bind(LanguageService.class).to(LanguageServiceImpl.class);
        bind(AuthorService.class).to(AuthorServiceImpl.class);
        // 链式绑定
        bind(PriceService.class).to(PriceServiceImpl.class);
        bind(PriceServiceImpl.class).toInstance(new PriceServiceImpl() {
            @Override
            public BigDecimal getPrice() {
                return BigDecimal.ONE;
            }
        });
        // Provider绑定
        bind(CurrencyService.class).toProvider(CurrencyServiceProvider.class);
    }

    /**
     * 命名绑定
     *
     * @param currencyService
     * @return
     */
    @Provides
    @Named("supportedCurrencies")
    List<String> getSupportedCurrencies(CurrencyService currencyService) {
        return currencyService.getSupportedCurrency();
    }
}
