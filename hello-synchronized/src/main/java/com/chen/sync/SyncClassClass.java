package com.chen.sync;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * 类锁-synchronized(*.class)代码块
 * <p>
 * @Author LeifChen
 * @Date 2018-12-19
 */
public class SyncClassClass implements Runnable {

    static SyncClassClass instance1 = new SyncClassClass();
    static SyncClassClass instance2 = new SyncClassClass();

    @Override
    public void run() {
        doMethod();
    }

    private void doMethod() {
        synchronized (SyncClassClass.class) {
            System.out.println("Class Lock synchronized(*.class) Code Block: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        Thread t1 = threadFactory.newThread(instance1);
        Thread t2 = threadFactory.newThread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
            // wait
        }
        System.out.println("End");
    }
}
