package io.jacy.drafts;

/**
 * @author Jacy
 */
public class TryFinal {
    public static void main(String[] args) {
        a();
        b();
    }

    static void a() {
        try {
            int i = 0;
            System.out.println(2 / i);
        } finally {
            System.out.println("finally...");
        }

    }

    static void b() {

        System.out.println(222222);
    }

    void x(int a, int b) {}
    Integer x() {
        return 0;
    }
    Integer x(int a) {
        return 0;
    }
}
