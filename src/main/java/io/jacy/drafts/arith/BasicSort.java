package io.jacy.drafts.arith;

import java.util.Arrays;

public class BasicSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.initArray(10);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");

        int[] arr1 = ArrayUtils.initArray(10);
        selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println("=======================");

        int[] arr2 = ArrayUtils.initArray(10);
        insertionSort(arr2);
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

    /**
     * 插入
     *
     * @param array
     */
    static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    /**
     * 冒泡
     *
     * @param array
     */
    static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择
     *
     * @param array
     */
    static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                minIndex = array[minIndex] > array[j] ? j : minIndex;
            }
            swap(array, i, minIndex);
        }
    }
}
