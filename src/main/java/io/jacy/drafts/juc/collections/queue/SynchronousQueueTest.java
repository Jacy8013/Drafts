package io.jacy.drafts.juc.collections.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue, 时一时间只能有1个线程put
 *
 * @author Jacy
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> syncQueue = new SynchronousQueue<>();

        for (int k = 0; k < 5; k++) {
            int temp = k;
            new Thread(() -> {
                System.out.println(temp + " before put...");
                // 不能add, 会发生"queue full"异常
                // syncQueue.add("1");

                // offer()不阻塞, 返回是否插入成功
                // System.out.println(syncQueue.offer("1"));
                try {
                    for (int i = 0; i < 10; i++) {
//                        System.out.println("put: " + i);
                        // put() 阻塞等待消费者
                        syncQueue.put(temp + ", " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(temp + " after put...");
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(syncQueue.size());
        System.out.println("===================");


        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("get: " + syncQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
