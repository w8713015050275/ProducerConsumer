package com.company;

import java.util.List;
import java.util.Random;

import static java.lang.System.out;


public class ConsumerB implements Runnable{
    private final List<Integer> mQueue;
    public static final int MAX_SLEEP_TIME = 300;
    public ConsumerB(List<Integer> queue) {
        this.mQueue = queue;
    }

    @Override
    public void run() {
        int result;
        Random random = new Random();
        while (true) {
            //consuming products
            while (mQueue.size() <= 0) {
                synchronized (mQueue) {
                    try {
                        out.println("zhc mQueue is empty: notify and wait");
                        mQueue.notifyAll();
                        mQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            result = random.nextInt(MAX_SLEEP_TIME);
            try {
                Thread.sleep(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (mQueue) {
                int product = mQueue.remove(0);
                out.println("zhc Consumer get product: " + product);
                mQueue.notifyAll();
            }
        }
    }
}
