package io.jacy.drafts.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 多线程 计数器
 *
 * @author Jacy
 */
public class ThreadCounter {
    static AtomicInteger atomicInteger = new AtomicInteger();
    static AtomicInteger atomicInteger2 = new AtomicInteger();
    static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        List<Thread> threads1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(atomicInteger::incrementAndGet));
            threads1.add(new Thread(longAdder::increment));
        }
        threads.forEach(Thread::start);
        threads1.forEach(Thread::start);


        TimeUnit.SECONDS.sleep(2);
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicInteger.get());
        System.out.println(longAdder.intValue());


        List<Thread> threads2 = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            threads2.add(new Thread(() -> {atomicInteger2.incrementAndGet(); countDownLatch.countDown();}));
        }
        threads2.forEach(Thread::start);
        countDownLatch.await();

        System.out.println(atomicInteger2.get());
    }
}
