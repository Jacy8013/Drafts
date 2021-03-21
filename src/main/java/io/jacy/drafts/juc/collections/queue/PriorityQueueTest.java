package io.jacy.drafts.juc.collections.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小根堆
 *
 * @author Jacy
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("b");
        priorityQueue.add("c");
        priorityQueue.add("a");
        priorityQueue.add("z");
        priorityQueue.add("x");

        String item;
        while ((item = priorityQueue.poll()) != null) {
            System.out.println(item);
        }
        System.out.println("================");

        PriorityQueue<A> apq = new PriorityQueue<>(Comparator.<A>comparingInt(a1 -> a1.a).reversed());
        apq.add(new A(3));
        apq.add(new A(1));
        apq.add(new A(4));
        apq.add(new A(2));

        A a;
        while ((a = apq.poll()) != null) {
            System.out.println(a);
        }

    }

    static class A {
        int a;

        public A(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    '}';
        }
    }
}
