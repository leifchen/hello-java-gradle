package com.chen.guice.dao.impl;

import com.chen.guice.dao.BookDao;

/**
 * BookDaoImpl
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("Guice BookDao method: save().");
    }
}
