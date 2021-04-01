package io.jacy.drafts.arith;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

        int[] arr2 = ArrayUtils.initArray(10);
        System.out.println(Arrays.toString(arr2));
        sort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
        System.out.println("=======================");
    }

    static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    static void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = p(array, l, r);
        sort(array, l, m);
        sort(array, m + 1, r);
    }

    static int p(int[] array, int l, int r) {
        int moreIndex = l - 1;
        for (int i = l; i < r; i++) {
            if (array[i] <= array[r]) {
                swap(array, ++moreIndex, i);
            }
        }
        swap(array, moreIndex + 1, r);
        return moreIndex;
    }
}
