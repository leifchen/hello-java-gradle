package com.chen.proxy;

/**
 * 明星
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class Star implements Person {

    @Override
    public void sing(String name) {
        System.out.println("明星开始唱歌：" + name);
    }

    @Override
    public void dance(String name) {
        System.out.println("明星开始跳舞：" + name);
    }
}
