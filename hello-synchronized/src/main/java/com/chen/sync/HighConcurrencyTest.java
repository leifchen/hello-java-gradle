package com.chen.sync;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * 高并发测试
 * <p>
 * @Author LeifChen
 * @Date 2018-12-19
 */
public class HighConcurrencyTest implements Runnable {

    static HighConcurrencyTest instance = new HighConcurrencyTest();

    static int count = 0;
    static int TIMES = 100000;

    @Override
    public void run() {
        for (int i = 0; i < TIMES; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        Thread t1 = threadFactory.newThread(instance);
        Thread t2 = threadFactory.newThread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
