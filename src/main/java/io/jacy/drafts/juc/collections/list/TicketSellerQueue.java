package io.jacy.drafts.juc.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟卖票: Queue
 *
 * @author Jacy
 */
public class TicketSellerQueue {
    static int threadNum = 100;
    static int ticketNum = 1000;
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

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

                    // 不需要锁, 也不会出现超卖和报错, 但需要判断poll()返回值为null的情况
                    // queue性能贼好(测试下来, 相比循环外加锁有20倍以上提升)
                    String ticket = tickets.poll();
                    if (ticket == null) {
                        break;
                    }

                    System.out.println("当前售票: [" + ticket + "]");
                    soldTickets.add(ticket);
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
