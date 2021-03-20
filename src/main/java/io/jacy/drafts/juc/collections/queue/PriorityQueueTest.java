package io.jacy.drafts.juc.collections.queue;

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
    }
}
