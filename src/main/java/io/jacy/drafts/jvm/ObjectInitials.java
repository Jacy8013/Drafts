package io.jacy.drafts.jvm;

import java.io.IOException;

/**
 * Q: 带有继承的类实例化后, 内存中有哪些对象? 会存在父类对象吗?
 * A: 只有当前具体实例化类的对象, 不存在父类对象, <init>字节码中也只有return而已, 没有其他任何逻辑
 *
 * @author Jacy
 */
public class ObjectInitials {
    public static void main(String[] args) throws IOException {
        D[] ds = new D[10000];

        for (int i = 0; i < ds.length; i++) {
            ds[i] = new D(1);
        }
        System.out.println(ds);

        // 阻塞住, 然后通过jmap查看对象信息
        System.in.read();
    }

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
        C(int x) {
        }
    }

    static class D extends C {
        D(int x) {
            super(x);
        }
    }
}
