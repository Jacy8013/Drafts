package io.jacy.drafts;

/**
 * finally 关键字
 * <p>
 * finally 里面的return 会覆盖try中的return
 *
 * @author Jacy
 */
public class Keyword_Finally {
    public static void main(String[] args) {
        System.out.println("==========" + get(3, 0));
        System.out.println("==========" + get(0, 0));
    }

    static int get(float d, int b) {
        try {
            double d2 = d / b;
            System.out.println(d2);

            int x = (int) (d / b);
            System.out.println(x);
            return x;
        } finally {

            System.out.println("2222");
            return 5;
        }
    }
}
