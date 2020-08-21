package io.jacy.drafts.juc.collections;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jacy
 */
public class CollectionCommon {
    public static final int SIZE;
    public static final int GAP;
    public static final int COUNT;
    public static final String[] ITEMS;

    // 要么把常量赋值放到static代码块中, 要么, 去掉final修饰, 不然static代码块可能不执行
    static {
        System.out.println("static start...");

        SIZE = 10_0000;
        GAP = 10000;
        COUNT = SIZE / GAP;
        ITEMS = new String[SIZE];

        for (int i = 0; i < SIZE; i++) {
            ITEMS[i] = UUID.randomUUID().toString();
        }
        System.out.println("static end...");
    }
}
