package com.chen.concurrent.racecondition;

/**
 * EnergySystem
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class EnergySystem {

    private final double[] energyBoxes;

    private final Object lockObj = new Object();

    public EnergySystem(int n, double initialEnergy) {
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initialEnergy;
        }
    }

    /**
     * 传递能量
     * @param from   能量的传递源头
     * @param to     能量的传递目标
     * @param amount 能量值
     */
    public void transfer(int from, int to, double amount) {
        synchronized (lockObj) {
            // 保证条件不满足时任务都会被阻挡
            while (energyBoxes[from] < amount) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            System.out.printf("从%d转移%10.2f单位能量到%d", from, amount, to);
            energyBoxes[to] += amount;
            System.out.printf(" 能量总和：%10.2f%n", getTotalEnergies());

            // 唤醒所有在lockObj对象上等待的线程
            lockObj.notifyAll();
        }
    }

    /**
     * 返回能量盒子的长度
     * @return
     */
    public int getBoxAmount() {
        return energyBoxes.length;
    }

    /**
     * 获取能量总和
     * @return
     */
    private double getTotalEnergies() {
        double sum = 0;
        for (double amount : energyBoxes) {
            sum += amount;
        }
        return sum;
    }
}
