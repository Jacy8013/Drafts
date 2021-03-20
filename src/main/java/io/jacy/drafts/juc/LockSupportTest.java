package io.jacy.drafts.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jacy
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("+++++++");
            LockSupport.unpark(mainThread);
        }).start();
        System.out.println("--------------: " + System.currentTimeMillis());
        LockSupport.parkNanos(3L * 1000 * 1000 * 1000);
        System.out.println("=============: " + System.currentTimeMillis());
    }
}
