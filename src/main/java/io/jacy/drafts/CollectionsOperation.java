package io.jacy.drafts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 集合操作
 *
 * @author Jacy
 */
public class CollectionsOperation {
    static List<Integer> list = new ArrayList<>();

    static {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(-1);
        list.add(-2);
        list.add(-3);
    }

    public static void main(String[] args) {
        System.out.println(list);

        // 移动distance个元素: 后到前(正数)或  前到后(负数)
        Collections.rotate(list, 2);
        System.out.println(list);
        Collections.rotate(list, -2);
        System.out.println(list);

        Collections.reverse(list.subList(0, 2));
        Collections.reverse(list.subList(2, list.size()));

        System.out.println(list.subList(0, 2));
        System.out.println(list);


        System.out.println(Collections.singleton(list));
        System.out.println(Collections.singletonList(list));

        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(arr.length);
        }
        System.out.println("========================");
        int[] arr3 = new int[arr.length];
        System.arraycopy(arr, 0, arr3, 0, arr.length);
        Arrays.parallelSort(arr3);

        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1, 0, arr.length);
        long st = System.currentTimeMillis();
        Arrays.parallelSort(arr1);
        System.out.println(arr1[0] + ", " + (System.currentTimeMillis() - st));


        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        st = System.currentTimeMillis();
        Arrays.sort(arr2);
        System.out.println(arr2[0] + ", " + (System.currentTimeMillis() - st));

        // 下标不够, 补0
        int[] srcArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] rangeDesArr = Arrays.copyOfRange(srcArr, 8, 11);
        System.out.println(Arrays.toString(rangeDesArr));
    }
}
