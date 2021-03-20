package io.jacy.drafts.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class CycleDepend {
//    @Bean("a")
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
//    public A a() {
//        return new A();
//    }
//
//    @Bean("b")
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
//    public B b() {
//        return new B();
//    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
        System.out.println(a);
        System.out.println(b);
        System.out.println("===========");
        System.out.println(a.getB());
        System.out.println(b.getA());
        System.out.println("===========");
        System.out.println(a == b.getA());
        System.out.println(b == a.getB());
    }
}
