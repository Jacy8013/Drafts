package io.jacy.drafts.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class BeanCreateTest {

    public static void main(String[] args) {

        System.out.println("main");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context");
        BeanCreate beanCreate = context.getBean("beanCreate", BeanCreate.class);
        System.out.println("get bean");
        System.out.println(beanCreate);
    }
}
