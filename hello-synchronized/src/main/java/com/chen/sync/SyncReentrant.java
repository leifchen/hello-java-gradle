package com.chen.sync;

/**
 * synchronized的可重入
 * <p>
 * @Author LeifChen
 * @Date 2018-12-19
 */
public class SyncReentrant extends SuperSyncReentrant {

    static int count = 0;

    private synchronized void doMethod() {
        System.out.println("count = " + count);
        if (count == 0) {
            count++;
            doMethod();
        }
    }

    private synchronized void doMethod1() {
        System.out.println("Method1.");
        doMethod2();
    }

    private synchronized void doMethod2() {
        System.out.println("Method2.");
    }

    @Override
    public synchronized void run() {
        System.out.println("Sub Class Run");
        super.run();
    }

    public static void main(String[] args) {
        SyncReentrant instance = new SyncReentrant();

        // 1. 同一个方法的synchronized可重入
        instance.doMethod();
        // 2. 不同方法的synchronized可重入
        instance.doMethod1();
        // 3. 不同类的synchronized可重入
        instance.run();
    }
}

class SuperSyncReentrant {
    public synchronized void run() {
        System.out.println("Super Class Run");
    }
}
