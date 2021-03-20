package io.jacy.drafts.juc;

import java.util.concurrent.TimeUnit;

public class SyncConditionTest {
    static synchronized void m1() {
        System.out.println("m1: method ---------");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1: method");
    }

    void m2() {
        synchronized (SyncConditionTest.class) {
            System.out.println("m2: class ---------");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2: class");
        }
    }

    void m3() {
        synchronized (this) {
            try {
                System.out.println("m3: this ---------" + this);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m3: this" + this);
        }
    }

    public static void main(String[] args) throws Exception {
        SyncConditionTest test = new SyncConditionTest();
        new Thread(() -> test.m2()).start();
        new Thread(() -> SyncConditionTest.m1()).start();
        new Thread(() -> test.m3()).start();

        SyncConditionTest test2 = new SyncConditionTest();
        new Thread(() -> test2.m3()).start();

        TimeUnit.SECONDS.sleep(3);

    }
}
