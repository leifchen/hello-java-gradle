package com.chen.dynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib 动态代理
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class CglibProxy implements MethodInterceptor {

    public CglibProxy() {
    }

    /**
     * 获取目标类的代理实例
     * @param targetInstanceClazz
     * @param <T>
     * @return
     */
    public static <T extends Target> Target newProxyInstance(Class<T> targetInstanceClazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxy());
        return (Target) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(obj, args);
    }
}
