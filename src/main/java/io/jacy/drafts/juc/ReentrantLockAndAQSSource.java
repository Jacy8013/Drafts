package io.jacy.drafts.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁及AQS源码
 *
 * @author Jacy
 */
public class ReentrantLockAndAQSSource {
    static int count = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    count++;
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(3));
                    System.out.println("a: " + count);
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
