package io.jacy.drafts.juc;

import java.util.concurrent.TimeUnit;

public class SyncArrayItem {
    public static void main(String[] args) throws Exception {
        System.out.println(1<< 15);
        Item[] items = new Item[]{
                new Item(1, "1"),
                new Item(2, "2"),
                new Item(3, "3"),
                new Item(4, "4"),
                new Item(5, "5")
        };

        new Thread(() -> {
            synchronized (items[1]) {
                System.out.println("[1] 锁着呢~");
                items[1].a = 10;
                try {
                    TimeUnit.SECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        TimeUnit.MILLISECONDS.sleep(2000);
        new Thread(() -> {
            System.out.println("读锁住的元素");
            System.out.println(items[1].a + ", " + items[1].b);
        }).start();
        System.in.read();
    }

    static class Item {
        int a;
        String b;

        public Item(int a, String b) {
            this.a = a;
            this.b = b;
        }
    }
}
