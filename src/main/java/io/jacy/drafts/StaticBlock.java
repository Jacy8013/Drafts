package io.jacy.drafts;

import java.util.*;

/**
 * 静态/非静态 代码块
 *
 * @author Jacy
 */
public class StaticBlock {
    static {
        // 不能访问代码块后面的静态变量, 编译错误
        // System.out.println(a);

        // 但可以赋值:
        a = 2;
    }

    static int a = 1;

    {
        // 同样, 非静态代码块也不能访问后面的属性
        // System.out.println(b);

        b = 20;
    }

    int b = 10;

    public static void main(String[] args) {
        System.out.println(0xff);
        System.out.println(0b10010);
        System.out.println(0b1000111111111111111111111111110);
        System.out.println(Integer.MAX_VALUE - 8);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(0x61c88647 );

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

//        List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());
//        list.sort();
//        map.entrySet()
    }
}
