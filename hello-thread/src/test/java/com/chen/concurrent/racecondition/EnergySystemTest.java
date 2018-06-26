package com.chen.concurrent.racecondition;

import org.junit.Test;

/**
 * EnergySystemTest
 * @Author LeifChen
 * @Date 2018-06-25
 */
public class EnergySystemTest {

    public final static int BOX_AMOUNT = 100;

    public final static double INITIAL_ENERGY = 1000;

    @Test
    public void transfer() {
        EnergySystem energy = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
        for (int i = 0; i < BOX_AMOUNT; i++) {
            EnergyTransferTask task = new EnergyTransferTask(energy, i, INITIAL_ENERGY);
            Thread t = new Thread(task, "TransferThread_" + i);
            t.start();
        }
    }
}