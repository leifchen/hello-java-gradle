package com.chen.concurrent;

/**
 * ArmyRunnable
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class ArmyRunnable implements Runnable {

    /**
     * volatile保证线程可以正确的读取其他线程写入的值
     */
    volatile boolean keepRunning = true;

    private final static int TIMES = 10;

    @Override
    public void run() {
        while (keepRunning) {
            for (int i = 0; i < TIMES; i++) {
                System.out.println(Thread.currentThread().getName() + "开始进攻[" + i + "]");
                // 线程礼让
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + "停止进攻!");
    }
}
