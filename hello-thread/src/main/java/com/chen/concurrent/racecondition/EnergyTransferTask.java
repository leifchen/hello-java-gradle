package com.chen.concurrent.racecondition;

/**
 * EnergyTransferTask
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class EnergyTransferTask implements Runnable {

    /**
     * 共享的能量世界
     */
    private EnergySystem energySystem;
    /**
     * 能量传递的源头
     */
    private int fromBox;
    /**
     * 单次能量转移的最大单元
     */
    private double maxAmount;
    /**
     * 最大休眠时间（毫秒）
     */
    private final static int DELAY = 10;

    public EnergyTransferTask(EnergySystem energySystem, int fromBox, double maxAmount) {
        this.energySystem = energySystem;
        this.fromBox = fromBox;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        while (true) {
            int toBox = (int) (energySystem.getBoxAmount() * Math.random());
            double amount = maxAmount * Math.random();
            energySystem.transfer(fromBox, toBox, amount);
            try {
                Thread.sleep((int) (DELAY * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
