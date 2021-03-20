package io.jacy.drafts.juc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jacy
 */
public class ReentrantLockCondition {
    public static void main(String[] args) throws IOException {
        ReentrantLock lock = new ReentrantLock();
        String[] a1 = new String[]{"a", "b", "c", "d", "e"};
        String[] a2 = new String[]{"1", "2", "3", "4", "5"};
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (String s : a1) {
                    System.out.println(s);
                    condition2.signal();

                    condition1.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();

                for (String s : a2) {
                    condition2.await();
                    System.out.println(s);
                    condition1.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t2.start();
        t1.start();

        System.in.read();
    }
}
