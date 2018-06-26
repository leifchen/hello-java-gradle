package com.chen.concurrent;

/**
 * HeroThread
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class HeroThread extends Thread {

    private final static int TIMES = 3;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "登场");
        for (int i = 0; i < TIMES; i++) {
            System.out.println(Thread.currentThread().getName() + "生平事迹[" + i + "]");
        }
        System.out.println(Thread.currentThread().getName() + "死亡");
    }
}
