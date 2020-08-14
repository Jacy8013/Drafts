package io.jacy.drafts.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Jacy
 */
public class SyncAndVolatile {
    volatile int count = 0;
   static AtomicInteger ai = new AtomicInteger(0);

    public synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        SyncAndVolatile sav = new SyncAndVolatile();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(sav::m, "t-" + i));
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(sav.count);

        ai.incrementAndGet();

    }
}
