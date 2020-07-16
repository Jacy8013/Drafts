package io.jacy.drafts;

/**
 * 接口中默认方法：
 * 当实现多个接口，同时这些接口中有定义相同的默认方法时， 需要重写, 否则编译错误
 *
 * @author Jacy
 */
public class InterfaceDefaultMethod {

    static class Child implements Ioo, Too {
        @Override
        public void m1() {
            // must override, or compile error.
        }
    }
}

interface Ioo {
    default void m1() {
        System.out.println("a");
    }
}

interface Too {
    default void m1() {
        System.out.println("b");
    }
}

