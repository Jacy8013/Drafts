package io.jacy.drafts.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 2个线程, 线程1里有容器, 线程2监控1的容器元素数量, 达到某个阈值2退出, 1继续
 * <p>
 * 本示例原理与LockSupport相同
 * countDown()后, t2未必会在t1的第6个元素插入前先执行完, 不可控, 因些t1也需要await(), 等待t2执行完成countDown()
 *
 * @author Jacy
 */
public class ThreadListenThread1_CountDownLatch {
    List<Object> list = new ArrayList<>();

    CountDownLatch latch1 = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);
//    Thread t1 = null;
//    Thread t2 = null;

    void m1() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println("m1 size: " + list.size());
                if (list.size() == 5) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    void m2() {
         new Thread(() -> {
             try {
                 latch1.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("m2 over...");
             latch2.countDown();
        }).start();
    }

    public static void main(String[] args) {
        ThreadListenThread1_CountDownLatch itlt = new ThreadListenThread1_CountDownLatch();


        itlt.m2();
        itlt.m1();
    }
}
