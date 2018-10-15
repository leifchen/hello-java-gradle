package com.chen.guice.manager;

import com.chen.guice.service.BookService;
import com.chen.guice.service.PriceService;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * BookManager
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookManager {

    private final BookService bookService;
    private final PriceService priceService;

    @Inject
    public BookManager(BookService bookService, PriceService priceService) {
        this.bookService = bookService;
        this.priceService = priceService;
    }

    public void test() {
        BigDecimal price = priceService.getPrice();
        System.out.println("Price: " + price);
        bookService.save();
    }
}
