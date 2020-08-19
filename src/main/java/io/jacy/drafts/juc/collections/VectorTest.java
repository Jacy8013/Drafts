package io.jacy.drafts.juc.collections;

import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * vector 使用
 *
 * @author Jacy
 */
public class VectorTest {

    static int size = 100_0000;
    static int gap = 10000;

    static String[] items = new String[size];

    public static void main(String[] args) throws InterruptedException {
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < items.length; i++) {
            items[i] = UUID.randomUUID().toString();
        }

        CountDownLatch latch = new CountDownLatch(size / gap);

        long start = System.currentTimeMillis();
        for (int i = 0; i < size / gap; i++) {
            int finalI = i;
            new Thread(() -> {
                int startIndex = finalI * gap;
                for (int k = 0; k < gap; k++) {
                    vector.add(items[startIndex + k]);
                }
                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println(System.currentTimeMillis() - start);

        System.out.println(vector.size());

    }
}
