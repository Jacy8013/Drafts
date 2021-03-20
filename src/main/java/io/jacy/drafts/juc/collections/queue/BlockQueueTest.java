package io.jacy.drafts.juc.collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列 - 线程池使用
 *
 * @author Jacy
 */
public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(5);
        // 单向链表
        // BlockingQueue<String> blockQueue = new LinkedBlockingQueue<>(5);



        new Thread(() -> {
            try {
                while (true) {
                    // take()阻塞
                    System.out.println(blockQueue.take());
                    // poll()不阻塞,队列中没有数据就返回null
                    // System.out.println(blockQueue.poll());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // put()阻塞,
                    blockQueue.put(String.valueOf(i));
                    // offer()不阻塞并返回插入是否成功
                    // boolean success = blockQueue.offer(String.valueOf(i));
                    System.out.println("put : " + i);
                }
            } catch (Exception e) {
            }
//            latch.countDown();
        }).start();


    }
}
