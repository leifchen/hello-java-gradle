package com.chen.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

/**
 * GlobalModule
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public class GlobalModule extends AbstractModule {

    @Override
    protected void configure() {
        // 集合Set绑定
        Multibinder<String> currencyBinder = Multibinder.newSetBinder(binder(), String.class);
        currencyBinder.addBinding().toInstance("ZH");
        currencyBinder.addBinding().toInstance("EN");
        currencyBinder.addBinding().toInstance("DE");

        // 集合Map绑定
        MapBinder<String, Integer> authorBinder = MapBinder.newMapBinder(binder(),String.class,Integer.class);
        authorBinder.addBinding("Leif").toInstance(20);
        authorBinder.addBinding("Chen").toInstance(18);
    }
}
