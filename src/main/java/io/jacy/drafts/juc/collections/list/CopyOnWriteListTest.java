package io.jacy.drafts.juc.collections.list;

import io.jacy.drafts.juc.collections.CollectionCommon;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jacy
 */
public class CopyOnWriteListTest {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        CountDownLatch latch = new CountDownLatch(CollectionCommon.COUNT);

        long start = System.currentTimeMillis();

        // 插入操作, 性能差, 相当差
        for (int i = 0; i < CollectionCommon.COUNT; i++) {
            int tempIndex = i * CollectionCommon.GAP;
            new Thread(() -> {
                for (int j = 0; j < CollectionCommon.GAP; j++) {
                    copyOnWriteList.add(CollectionCommon.ITEMS[tempIndex + j]);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("write: " + (System.currentTimeMillis() - start));
        System.out.println(copyOnWriteList.size());

        System.out.println("========================");

        String[] tmp = new String[CollectionCommon.GAP];

        int cowCount = 1000;
        CountDownLatch readLatch = new CountDownLatch(cowCount);
        Thread[] threads = new Thread[cowCount];
        for (int i = 0; i < cowCount; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < CollectionCommon.GAP; k++) {
                    tmp[k] = copyOnWriteList.get(15);
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
