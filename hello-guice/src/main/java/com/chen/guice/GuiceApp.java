package com.chen.guice;

import com.chen.guice.manager.BookManager;
import com.chen.guice.module.BookModule;
import com.google.inject.Guice;

/**
 * GuiceApp主程序入口
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class GuiceApp {

    public static void main(String[] args) {
        Guice.createInjector(new BookModule()).getInstance(BookManager.class).test();
    }
}
