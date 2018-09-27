package com.chen.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Target target;

    public JdkDynamicProxy(Target target) {
        this.target = target;
    }

    /**
     * 返回目标类的代理实例
     * @param target
     * @return
     */
    public static Target newProxyInstance(Target target) {
        return (Target) Proxy.newProxyInstance(JdkDynamicProxy.class.getClassLoader(),
                new Class<?>[]{Target.class},
                new JdkDynamicProxy(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
