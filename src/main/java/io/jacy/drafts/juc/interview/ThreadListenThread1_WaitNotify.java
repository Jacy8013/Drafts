package io.jacy.drafts.juc.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 2个线程, 线程1里有容器, 线程2监控1的容器元素数量, 达到某个阈值2退出, 1继续
 * <p>
 * wait / notify 都需要上锁的对象才可以调用
 *
 * @author Jacy
 */
public class ThreadListenThread1_WaitNotify {
    List<Object> list = new ArrayList<>();
    final Object lock = new Object();

    void m1() {
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    list.add(new Object());
                    System.out.println("m1 size: " + list.size());
                    if (list.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    void m2() {
        new Thread(() -> {
            synchronized (lock) {
                while (list.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("m2 over...");
                lock.notify();
            }
        }).start();
    }

    public static void main(String[] args) {
        ThreadListenThread1_WaitNotify itlt = new ThreadListenThread1_WaitNotify();

        itlt.m2();
        itlt.m1();
    }
}
