package com.chen.guice.manager;

import com.chen.guice.service.AuthorService;
import com.chen.guice.service.BookService;
import com.chen.guice.service.LanguageService;
import com.chen.guice.service.PriceService;
import com.google.inject.Provider;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

/**
 * BookManager
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookManager {

    private final BookService bookService;
    private final PriceService priceService;
    private final LanguageService languageService;
    private final AuthorService authorService;

    @Inject
    @Named("supportedCurrencies")
    private Provider<List<String>> supportedCurrenciesProvider;

    @Inject
    public BookManager(BookService bookService, PriceService priceService,
                       LanguageService languageService, AuthorService authorService) {
        this.bookService = bookService;
        this.priceService = priceService;
        this.languageService = languageService;
        this.authorService = authorService;
    }

    public void test() {
        BigDecimal price = priceService.getPrice();
        System.out.println("Price: " + price);
        System.out.println("Currency: " + supportedCurrenciesProvider.get().toString());
        System.out.println("Language: " + languageService.getSupportedLanguages());
        System.out.println("Author: " + authorService.getAuthor());
        bookService.save();
    }
}
