package com.chen.sync;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * 对象锁-方法锁形式
 * <p>
 * @Author LeifChen
 * @Date 2018-12-19
 */
public class SyncObjectMethod implements Runnable {

    static SyncObjectMethod instance = new SyncObjectMethod();

    @Override
    public void run() {
        doMethod();
    }

    private synchronized void doMethod() {
        System.out.println("Object Lock Method: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        Thread t1 = threadFactory.newThread(instance);
        Thread t2 = threadFactory.newThread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
            // wait
        }
        System.out.println("End");
    }
}
