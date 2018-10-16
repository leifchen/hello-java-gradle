package com.chen.guice.service.impl;

import com.chen.guice.service.AuthorService;

import javax.inject.Inject;
import java.util.Map;

/**
 * AuthorServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public class AuthorServiceImpl implements AuthorService {

    private final Map<String, Integer> authorMap;

    @Inject
    public AuthorServiceImpl(Map<String, Integer> authorMap) {
        this.authorMap = authorMap;
    }

    @Override
    public Map<String, Integer> getAuthor() {
        return authorMap;
    }
}
