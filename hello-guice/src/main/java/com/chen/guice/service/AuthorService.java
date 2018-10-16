package com.chen.guice.service;

import java.util.Map;

/**
 * AuthorService
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public interface AuthorService {

    /**
     * 作者
     *
     * @return
     */
    Map<String, Integer> getAuthor();
}
