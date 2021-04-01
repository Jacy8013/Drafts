package io.jacy.drafts.juc;

/**
 * @author Jacy
 */
public class VolatileClass {
    static volatile int a = 100;

    public static void main(String[] args) {
        new Thread(() -> {
            a++;
        }).start();
        new Thread(() -> {
            a++;
        }).start();
        System.out.println(a);
    }
}
