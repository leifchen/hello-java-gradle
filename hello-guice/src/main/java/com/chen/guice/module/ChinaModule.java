package com.chen.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

/**
 * ChinaModule
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
public class ChinaModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder.newSetBinder(binder(), String.class)
                .addBinding().toInstance("ZH");

        MapBinder.newMapBinder(binder(), String.class, Integer.class)
                .addBinding("LeifChen").toInstance(28);
    }
}
