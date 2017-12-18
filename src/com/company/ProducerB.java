package com.company;

import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class ProducerB implements Runnable {
    private final List<Integer> mQueue;
    private final int mLength;
    public static final int MAX_SLEEP_TIME = 300;

    public ProducerB(List<Integer> queue, int length) {
        this.mQueue = queue;
        this.mLength = length;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            while (mQueue.size() >= mLength) {
                //wait
                synchronized (mQueue) {
                    try {
                        out.println("zhc mQueue is full, notify consumer and wait...");
                        mQueue.notifyAll();
                        mQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            int product = random.nextInt(MAX_SLEEP_TIME);
            try {
                Thread.sleep(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (mQueue) {
                mQueue.add(product);
                out.println("zhc product: " + product);
                mQueue.notifyAll();
            }
        }
    }
}
