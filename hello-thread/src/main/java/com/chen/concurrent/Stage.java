package com.chen.concurrent;

/**
 * Stage
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class Stage {

    public void run() {
        System.out.println("三国演义开始：");

        ArmyRunnable armyTaskOfWei = new ArmyRunnable();
        ArmyRunnable armyTaskOfShu = new ArmyRunnable();
        Thread armyOfWei = new Thread(armyTaskOfWei, "魏军");
        Thread armyOfShu = new Thread(armyTaskOfShu, "蜀军");

        // 开始线程
        armyOfWei.start();
        armyOfShu.start();

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

        System.out.println("三国演义结束。");
    }
}
