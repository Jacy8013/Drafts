package io.jacy.drafts;

/**
 * @author Jacy
 */
public class OverrideOverload {
    public static void main(String[] args) {
        Foo foo = new Boo();
        foo.a = "3333";
        System.out.println(foo.getA());
    }
}

 class Foo {
    protected String a;

     String getA() {
        return "2222";
    }
}

class Boo extends Foo {

    @Override
    public String getA() {
        return a;
    }
}
