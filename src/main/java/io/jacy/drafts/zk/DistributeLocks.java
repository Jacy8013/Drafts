package io.jacy.drafts.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * d lock
 *
 * @author Jacy
 */
public class DistributeLocks {
    public static void main(String[] args) {
//        syncDL1();
//        asyncDL();

        apacheDL();
    }

    public static void apacheDL() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                CuratorFramework client = CuratorFrameworkFactory.newClient("172.100.7.130:2181,172.100.7.131:2181,172.100.7.132:2181,172.100.7.133:2181", 3000, 1500, new RetryNTimes(3, 50));
                client.start();

                InterProcessMutex lock = new InterProcessMutex(client, "/apache-locks");
                try {
                    if (lock.acquire(1000L, TimeUnit.MILLISECONDS)) {
                        Thread.sleep(2000);
                        System.out.println("+++++++++++++ " + Thread.currentThread().getName() + ", " + lock);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void asyncDL() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                AsyncLock lock = null;
                try {
                    lock = new AsyncLock(ZkUtils.get("/locks"));
                    lock.tryLock();
                    System.out.println(lock.getZooKeeper().getSessionId() + ", " + Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock != null) {
                        lock.release();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void syncDL1() {
        AtomicInteger atomic = new AtomicInteger(0);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                SyncLock lock = null;
                try {
                    lock = new SyncLock(ZkUtils.get("/locks"));
                    lock.tryLock();
                    System.out.println("=============== " + Thread.currentThread().getName() + ", " + atomic.incrementAndGet());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (lock != null) {
                        lock.release();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");

    }
}
