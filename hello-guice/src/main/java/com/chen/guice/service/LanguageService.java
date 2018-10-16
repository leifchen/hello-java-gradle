package com.chen.guice.service;

import java.util.Set;

/**
 * LanguageService
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public interface LanguageService {

    /**
     * 支持的语言
     *
     * @return
     */
    Set<String> getSupportedLanguages();
}
