package com.company;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static java.lang.System.out;

public class ProducerA implements Runnable {

    private final BlockingQueue<Integer> mQueue;
    public static final int MAX_SLEEP_TIME = 50;
    private boolean mIsRunning = true;
    static int sProduct;

    @Override
    public void run() {
        while (mIsRunning) {
            sProduct++;
            Random random = new Random();
            int sleepTimes = random.nextInt(MAX_SLEEP_TIME);
            try {
                Thread.sleep(sleepTimes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mQueue.put(sProduct);
                out.println("produce int: " + sProduct);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public ProducerA(BlockingQueue<Integer> queue) {
        this.mQueue = queue;
    }

    void stop() {
        mIsRunning = false;
    }
}
