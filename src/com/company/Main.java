package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) {
	// write your code here
        //ProducerA-ConsumerA by LinkedBlockingQueue
        ExecutorService executor = Executors.newCachedThreadPool();

/*        BlockingQueue<Integer> mQueue = new LinkedBlockingQueue<>(5);
        ProducerA producer1 = new ProducerA(mQueue);
//        ProducerA producer2 = new ProducerA(mQueue);
//        ProducerA producer3 = new ProducerA(mQueue);

        ConsumerA consumer1 = new ConsumerA(mQueue);
        ConsumerA consumer2 = new ConsumerA(mQueue);
        ConsumerA consumer3 = new ConsumerA(mQueue);*/

/*        executor.execute(producer1);
//        executor.execute(producer2);
//        executor.execute(producer3);
        executor.execute(consumer1);
        executor.execute(consumer2);
        executor.execute(consumer3);*/

        //ProducerA-ConsumerA by LinkedList
        List<Integer> mContainer = new ArrayList<>();
        ProducerB producerB = new ProducerB(mContainer, 5);
        ConsumerB consumerB = new ConsumerB(mContainer);

        executor.execute(producerB);
        executor.execute(consumerB);
    }
}
