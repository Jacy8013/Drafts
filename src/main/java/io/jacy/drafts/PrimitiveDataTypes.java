package io.jacy.drafts;

/**
 * @author Jacy
 */
public class PrimitiveDataTypes {
    public static void main(String[] args) {
        Integer i1 = new Integer(128);
        Integer i2 = new Integer(126);

        // valueOf(1)

        Integer i3 = 1;
        Integer i4 = 1;

        Integer i5 = 2;

        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i4 == i3);

        System.out.println(i1 == i2 + i5);


        Boolean b1 = true;
        Boolean b2 =  Boolean.valueOf("true");


        Boolean b3 = false;
        Boolean b4 =  Boolean.valueOf("false");


        System.out.println(b1 == b2);
        System.out.println(b4 == b3);
    }
}
