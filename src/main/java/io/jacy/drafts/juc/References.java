package io.jacy.drafts.juc;

import java.util.WeakHashMap;

/**
 * 强软弱虚
 *
 * @author Jacy
 */
public class References {
    public static void main(String[] args) {
        WeakHashMap<Object, String> weakHashMap = new WeakHashMap<>();

        weakHashMap.put(new Object(), "1");
    }
}
