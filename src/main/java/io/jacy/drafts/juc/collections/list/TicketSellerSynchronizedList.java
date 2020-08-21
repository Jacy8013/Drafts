package io.jacy.drafts.juc.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟卖票: List
 *
 * @author Jacy
 */
public class TicketSellerSynchronizedList {
    static int threadNum = 100;
    static int ticketNum = 1000;
    static List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    static final Object LOCK = new Object();

    static {
        for (int i = 0; i < ticketNum; i++) {
            tickets.add(String.valueOf(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[threadNum];
        CountDownLatch ticketLatch = new CountDownLatch(threadNum);
        List<String> soldTickets = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        // 通过简短的sleep模拟真实逻辑耗时
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 票快卖完的时候这里会报错, 多个线程都检测到size>0, 但其实剩下的票已经不够这些线程售卖了,
                    // 所以可能会超卖, 可能会卖出null的票, 最后还可能会报异常, 解决方案: 外层加锁
                    try {
                        String ticket = tickets.remove(0);
                        if (ticket == null) {
                            break;
                        }
                        System.out.println("当前售票: [" + ticket + "]");
                        soldTickets.add(ticket);
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
                ticketLatch.countDown();
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        ticketLatch.await();
        System.out.println("time:" + (System.currentTimeMillis() - start) + ", size: " + soldTickets.size());
    }
}
