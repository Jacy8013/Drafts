package io.jacy.drafts.juc.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟卖票: Vector
 *
 * @author Jacy
 */
public class TicketSellerVector {
    static int threadNum = 100;
    static int ticketNum = 1000;
    static Vector<String> tickets = new Vector<>();

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
                // 通过对整个代码段加锁来保证 不会报错或超卖
                while (tickets.size() > 0) {
                    try {
                        // 通过简短的sleep模拟真实逻辑耗时
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 票快卖完的时候这里会报错, 多个线程都检测到size>0, 但其实剩下的票已经不够这些线程分了
                    // 使用try-catch可以将多线程操作异常当做保证线程安全的一种方式使用
                    // 另: 该try-catch可以提升到while循环外
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
