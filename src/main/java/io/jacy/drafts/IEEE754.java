package io.jacy.drafts;

/**
 * v = (-1)^s * M * 2^e
 *
 * float:  1 -  8 - 23
 * double: 1 - 11 - 52
 *
 * @author Jacy
 */
public class IEEE754 {
    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(1091567616));

        System.out.println(Double.doubleToLongBits(9.0));
    }
}
