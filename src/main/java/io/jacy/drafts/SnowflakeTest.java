package io.jacy.drafts;

/**
 * @author Jacy
 */
public class SnowflakeTest {
    public static void main(String[] args) {
        long v = 1L << 22 | 1023 << 12 | 4095;
        System.out.println(v);
        System.out.println(Integer.MAX_VALUE);

        System.out.println("20200925362387869697");
    }
}
