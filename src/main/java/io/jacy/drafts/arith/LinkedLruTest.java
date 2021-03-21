package io.jacy.drafts.arith;

import java.util.Map;

public class LinkedLruTest {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);


        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(4, 4);

        for (Map.Entry<Integer, Integer> next : cache.entrySet()) {
            System.out.println(next.getKey() + ", " + next.getValue());
        }
    }
}
