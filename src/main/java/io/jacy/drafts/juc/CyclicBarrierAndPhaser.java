package io.jacy.drafts.juc;

import java.util.concurrent.*;

/**
 * CyclicBarrier
 * Phaser
 *
 * @author Jacy
 */
public class CyclicBarrierAndPhaser {

    Phaser phaser = new Phaser();

    CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("enough!"));

    void m1() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await(3, TimeUnit.SECONDS);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("==================");
        for (int i = 0; i < 20; i++) {
            try {
                cyclicBarrier.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        CyclicBarrierAndPhaser cbap = new CyclicBarrierAndPhaser();
        cbap.m1();

    }
}
