package io.jacy.drafts.spring;


public class BeanCreate {
    public BeanCreate() {
        System.out.println("constructor");
    }

    static {
        System.out.println("static");
    }

    public void init() {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
