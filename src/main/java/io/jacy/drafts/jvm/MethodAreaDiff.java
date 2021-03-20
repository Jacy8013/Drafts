package io.jacy.drafts.jvm;

/**
 * Method area 在1.7与1.8中的不同
 * 1.7: 叫PermGen (永久代)
 * 1.8: 叫MetaSpace (元数据区)
 * <p>
 * 字符串常量:
 * 1.7分配在PermGen中, 且PG不能被FGC回收
 * 1.8分配在heap中, MS可以被FGC回收
 * <p>
 * Q: 如何证明字符串常量分配在不同地方?
 * A: 结合GC
 *
 * @author Jacy
 */
public class MethodAreaDiff {
    public static void main(String[] args) {
        while (true) {
            X m = T::n;
        }
//        System.out.println(m.getClass());
    }

    @FunctionalInterface
    interface X {
        void m();
    }

    static class T {
        static void n() {
        }
    }
}
