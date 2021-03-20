package io.jacy.drafts.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    @Autowired
    private B b;

    public A() {
    }

    public A(B b) {
        this.b = b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
}