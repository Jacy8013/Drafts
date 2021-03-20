package io.jacy.drafts.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Jacy
 */
public class NewThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.
        new T1().start();

        // 2
        new Thread(new T2()).start();

        // 3
        FutureTask<String> future = new FutureTask<>(() -> {
            System.out.println("t3: " + Thread.currentThread().getName());
            return "t3";
        });
        new Thread(future, "abc-1").start();
        System.out.println(future.get());

        // 4
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(32),
                new ThreadFactoryBuilder().setNameFormat("t4-%d").build(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }


}

class T1 extends Thread {
    @Override
    public void run() {
        System.out.println("t1: " + Thread.currentThread().getName());
    }
}

class T2 implements Runnable {
    @Override
    public void run() {
        System.out.println("t2: " + Thread.currentThread().getName());
    }
}
