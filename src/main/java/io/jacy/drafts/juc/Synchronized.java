package io.jacy.drafts.juc;

/**
 * @author Jacy
 */
public class Synchronized {

    /**
     * 不能用在类上面
     */
    /*synchronized*/ static class T1 {
        synchronized void m(){
            System.out.println("T1.m");
        };

        void m1() {
            synchronized (this) {
//                System.out.println("T1.m1");
            }
        }
    }

    static class T2 extends T1 {
        @Override
        synchronized void m() {
            System.out.println("T2.m");
            super.m();
            m2();
        }
    }

    public static void main(String[] args) {
//        new T2().m();

        Object o1 = null;
        synchronized (o1){
            System.out.println("null pointer");
        }

        new Synchronized().m1();
    }



    /**
     * 等价于: synchronized (this)
     */
    synchronized void m1() {
        m3();
    }

    void m3() {
    }

    /**
     * 等价于: synchronized(T.class)
     */
    synchronized static void m2() {
    }
}
