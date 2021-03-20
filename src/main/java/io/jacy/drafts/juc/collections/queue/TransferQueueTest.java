package io.jacy.drafts.juc.collections.queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * TransferQueue, 可多个线程同时入队等待
 *
 * @author Jacy
 */
public class TransferQueueTest {
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();

        for (int i = 0; i < 10; i++) {
            int tmp = i;
            new Thread(() -> {
                System.out.println("before transfer...");
                try {
                    transferQueue.transfer(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after transfer...");
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(transferQueue.size());
        System.out.println("===================");

        new Thread(() -> {
            try {
                System.out.println(transferQueue.size());
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
