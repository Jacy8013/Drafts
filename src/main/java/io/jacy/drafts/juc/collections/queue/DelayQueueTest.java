package io.jacy.drafts.juc.collections.queue;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 *
 * @author Jacy
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        DelayQueue<Item> delayQueue = new DelayQueue<>();

        final long currentMillis = System.currentTimeMillis();
        final Random random = new Random();
//        new Thread(() -> {
        for (int i = 0; i < 10; i++) {
            delayQueue.put(new Item(currentMillis + random.nextInt(2000) + 500, String.valueOf(i)));
//            delayQueue.add(new Item(random.nextInt(10), String.valueOf(i)));
        }
        System.out.println(delayQueue);
//        }).start();

//        new Thread(() -> {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        }).start();

//        System.in.read();
//        TimeUnit.SECONDS.sleep(20);
    }

    static class Item implements Delayed {
        private long millis;
        private String str;

        public Item(long millis, String str) {
            this.millis = millis;
            this.str = str;
        }

        /**
         * 首次计算出最近一个元素的delay时间, 等parkNanos(delay)后, 该方法会再次被调用(内部实现死循环),
         * 因此, 方法返回值需要一直在更新(减少), 否则take()永远无法取到元素
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            long s = unit.convert(this.millis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            System.out.println(s + ", " + this.str);
            return s;
        }

        /**
         * compare内容随便定义, 不一定需要调用getDelay()方法
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if (!(o instanceof Item)) {
                return 0;
            }
            return Long.compare(this.millis, ((Item) o).millis);
        }

        @Override
        public String toString() {
            return System.currentTimeMillis() + ", Item{" +
                    "millis=" + millis +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
}
