package com.chen.guice.module;

import com.chen.guice.dao.BookDao;
import com.chen.guice.dao.impl.BookDaoImpl;
import com.chen.guice.service.BookService;
import com.chen.guice.service.PriceService;
import com.chen.guice.service.impl.BookServiceImpl;
import com.chen.guice.service.impl.PriceServiceImpl;
import com.google.inject.AbstractModule;

import java.math.BigDecimal;

/**
 * BookModule
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookModule extends AbstractModule {

    @Override
    protected void configure() {
        // 实例绑定
        bind(BookDao.class).toInstance(new BookDaoImpl());
        // 类名绑定
        bind(BookService.class).to(BookServiceImpl.class);
        // 链式绑定
        bind(PriceService.class).to(PriceServiceImpl.class);
        bind(PriceServiceImpl.class).toInstance(new PriceServiceImpl() {
            @Override
            public BigDecimal getPrice() {
                return BigDecimal.ONE;
            }
        });
    }
}
