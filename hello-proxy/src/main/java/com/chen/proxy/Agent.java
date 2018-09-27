package com.chen.proxy;

import java.lang.reflect.Proxy;

/**
 * 经纪人
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class Agent {

    private final static String SING = "sing";
    private final static String DANCE = "dance";

    private Person singer = new Star();

    public Person getProxy() {
        return (Person) Proxy.newProxyInstance(Agent.class.getClassLoader(), singer.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    if (SING.equals(method.getName())) {
                        System.out.println("我是经纪人，需要我家明星唱什么歌？");
                        return method.invoke(singer, args);
                    } else if (DANCE.equals(method.getName())) {
                        System.out.println("我是经纪人，需要我家明星跳什么舞？");
                        return method.invoke(singer, args);
                    }
                    return null;
                });
    }

    public static void main(String[] args) {
        Agent agent = new Agent();
        // 获得代理对象
        Person person = agent.getProxy();
        // 调用代理对象的sing方法
        person.sing("七里香");
        // 调用代理对象的dance方法
        person.dance("街舞");
    }

}
