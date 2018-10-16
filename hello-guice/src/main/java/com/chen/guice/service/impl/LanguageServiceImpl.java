package com.chen.guice.service.impl;

import com.chen.guice.service.LanguageService;

import javax.inject.Inject;
import java.util.Set;

/**
 * LanguageServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public class LanguageServiceImpl implements LanguageService {

    private final Set<String> supportedLanguages;

    @Inject
    public LanguageServiceImpl(Set<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return supportedLanguages;
    }
}
