package io.jacy.drafts.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 2个线程, 线程1里有容器, 线程2监控1的容器元素数量, 达到某个阈值2退出, 1继续
 * <p>
 * unpark()后, t2未必会在t1的第6个元素插入前先执行完, 不可控, 因些t1也需要park(), 等待t2执行完成, unpark()
 *
 * @author Jacy
 */
public class ThreadListenThread1_LockSupport {
    List<Object> list = new ArrayList<>();

    Thread t1 = null;
    Thread t2 = null;

    void m1() {
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println("m1 size: " + list.size());
                if (list.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t1.start();
    }

    void m2() {
        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("m2 over...");
            LockSupport.unpark(t1);
        });

        t2.start();
    }

    public static void main(String[] args) {
        ThreadListenThread1_LockSupport itlt = new ThreadListenThread1_LockSupport();

        itlt.m2();
        itlt.m1();
    }
}
