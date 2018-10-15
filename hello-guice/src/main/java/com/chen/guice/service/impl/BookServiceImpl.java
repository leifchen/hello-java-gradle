package com.chen.guice.service.impl;

import com.chen.guice.dao.BookDao;
import com.chen.guice.service.BookService;

import javax.inject.Inject;

/**
 * BookServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Inject
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("Guice BookService method: save().");
        bookDao.save();
    }
}
