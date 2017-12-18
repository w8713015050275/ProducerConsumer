package com.company;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static java.lang.System.out;

public class ConsumerA implements Runnable {
    private final BlockingQueue<Integer> mQueue;
    public static final int MAX_SLEEP_TIME = 150;
    public static int sConsumerCount = 0;
    int mConsumerIndex;

    public ConsumerA(BlockingQueue<Integer> queue) {
        this.mQueue = queue;
        mConsumerIndex = sConsumerCount++;
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            int sleepTime = random.nextInt(MAX_SLEEP_TIME);
            try {
                Thread.sleep(sleepTime);
                int result = mQueue.take();
                out.println("consume" + mConsumerIndex +" int: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
