package io.jacy.drafts;

/**
 * 静态/非静态 内部类
 *
 * @author Jacy
 */
public class StaticInnerClass {
    void setA() {

    }

    static void setB() {

    }

    class Inner {
        {
            setA();
        }
    }

    static class StaticInner {
        {
            setB();
        }
    }
}
