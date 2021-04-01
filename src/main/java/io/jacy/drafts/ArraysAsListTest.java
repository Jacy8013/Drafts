package io.jacy.drafts;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers.size());

        integers.add(4);
        System.out.println(integers.size());
    }
}
