package io.jacy.drafts;

/**
 * strictfp 关键字, 保证无论在任何环境下, 浮点数计算都按照IEEE754标准执行, 保证结果的一致
 *
 * @author Jacy
 */
public class Strictfp {

    static class Strict {
        private double d = Math.random();
        private double e = Math.random();

        strictfp double sum() {
            return d + e;
        }

        strictfp double sub() {
            return d - e;
        }
    }

    strictfp static double sum() {
        float a = 0.1f;
        double b = 2e-1;
        return a + b;
    }

    public strictfp static void main(String[] args) {
        float aFloat = 0.6710339f;
        double aDouble = 0.04150553411984792d;

        double d = aFloat + aDouble;

        int n = 9;
        System.out.println((float) n);

        // 0.7125394529774224
        // 0.7125394529774224
        System.out.println(d);

        System.out.println(14.4 + 0.1);
    }
}
