package com.chen.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Stage
 *
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class Stage {

    public void run() {
        System.out.println("三国演义开始：");

        ArmyRunnable armyTaskOfWei = new ArmyRunnable();
        ArmyRunnable armyTaskOfShu = new ArmyRunnable();


        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()->{
            new Thread(armyTaskOfWei, "魏军").start();
            new Thread(armyTaskOfShu, "蜀军").start();
        });

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread zhuGeLiang = new HeroThread();
        zhuGeLiang.setName("诸葛亮");

        // 停止线程
        armyTaskOfWei.keepRunning = false;
        armyTaskOfShu.keepRunning = false;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zhuGeLiang.start();

        try {
            zhuGeLiang.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        singleThreadPool.shutdown();

        System.out.println("三国演义结束。");
    }
}
