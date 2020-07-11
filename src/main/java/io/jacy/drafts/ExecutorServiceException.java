package io.jacy.drafts;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 连接池中的异常, 只能通过{@link Future#get()}获取
 * <p>
 * 连接池创建后一直存活, 是因为{@link LinkedBlockingQueue#take()}中 while(true) {notEmpty.await()}阻塞引起,
 * 如果corePoolSize=0, 则超过keepAliveTime后, 连接池销毁
 *
 * @author Jacy
 */
public class ExecutorServiceException {
    public static final ExecutorService ES = new ThreadPoolExecutor(1, 2, 6000, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(256),
            new ThreadFactoryBuilder().setNameFormat("es-ex-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
    public static final ExecutorService ES2 = Executors.newFixedThreadPool(1);
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);


    public static void main(String[] args) throws InterruptedException {
        Future<?> future = ES.submit(() -> {
            try {
                System.out.println(3 / 0);
            } finally {
                countDownLatch.countDown();
            }
        });

        ES2.submit(() -> {
            try {
                System.out.println(3);
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        ES2.submit(() -> {
            try {
                System.out.println(3);
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        System.out.println("==================");
    }
}
