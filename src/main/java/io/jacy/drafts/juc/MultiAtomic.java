package io.jacy.drafts.juc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class MultiAtomic {
    public static void main(String[] args) throws InterruptedException, IOException {
        AtomicInteger atomic = new AtomicInteger(0);
        LongAdder adder = new LongAdder();
        int size = 10000;
        Thread[] threads = new Thread[size];
        Thread[] threads2 = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                atomic.incrementAndGet();
            });
        }
        for (int i = 0; i < size; i++) {
            threads2[i] = new Thread(() -> {
                adder.increment();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads2) {
            thread.start();
        }

        System.in.read();
//        TimeUnit.SECONDS.sleep(20);
        System.out.println(atomic.get());
        System.out.println(adder.intValue());
    }
}
