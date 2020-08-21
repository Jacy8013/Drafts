package io.jacy.drafts.juc.collections.list;

import io.jacy.drafts.juc.collections.CollectionCommon;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * vector 使用
 *
 * @author Jacy
 */
public class VectorTest {

    public static void main(String[] args) throws InterruptedException {
        Vector<String> vector = new Vector<>();

        CountDownLatch latch = new CountDownLatch(CollectionCommon.COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < CollectionCommon.COUNT; i++) {
            int finalI = i;
            new Thread(() -> {
                int startIndex = finalI * CollectionCommon.GAP;
                for (int k = 0; k < CollectionCommon.GAP; k++) {
                    vector.add(CollectionCommon.ITEMS[startIndex + k]);
                }
                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println("write: " + (System.currentTimeMillis() - start));
        System.out.println(vector.size());
        System.out.println("========================");

        String[] tmp = new String[CollectionCommon.GAP];

        int cowCount = 1000;
        CountDownLatch readLatch = new CountDownLatch(cowCount);
        Thread[] threads = new Thread[cowCount];
        for (int i = 0; i < cowCount; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < CollectionCommon.GAP; k++) {
                    tmp[k] = vector.get(15);
                }
                readLatch.countDown();
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        readLatch.await();
        System.out.println("read: " + (System.currentTimeMillis() - start));
        System.out.println(tmp.length);
    }
}
