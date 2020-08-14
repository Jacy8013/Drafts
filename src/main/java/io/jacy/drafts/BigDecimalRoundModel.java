package io.jacy.drafts;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * finally 关键字
 *
 * @author Jacy
 */
public class BigDecimalRoundModel {
    public static void main(String[] args) {
        // 0.67, 向大的方向取舍
        System.out.println(new BigDecimal(2).divide(new BigDecimal(3), 2, RoundingMode.CEILING));
        // -0.66
        System.out.println(new BigDecimal(-2).divide(new BigDecimal(3), 2, BigDecimal.ROUND_CEILING));

        // 0.66, 向小的方向取舍
        System.out.println(new BigDecimal(2).divide(new BigDecimal(3), 2, BigDecimal.ROUND_FLOOR));
        // -0.67
        System.out.println(new BigDecimal(-2).divide(new BigDecimal(3), 2, BigDecimal.ROUND_FLOOR));

        // 0.34, 远离原点
        System.out.println(new BigDecimal(1).divide(new BigDecimal(3), 2, BigDecimal.ROUND_UP));
        // -0.33, 靠近原点
        System.out.println(new BigDecimal(-1).divide(new BigDecimal(3), 2, BigDecimal.ROUND_DOWN));

        // 0.12, 5舍
        System.out.println(new BigDecimal(1).divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_DOWN));
        // 0.13, 5入
        System.out.println(new BigDecimal(1).divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_UP));

        // 0.12, 向上一位的偶数方向取舍
        System.out.println(new BigDecimal(1).divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_EVEN));
        // 0.14
        System.out.println(new BigDecimal("1.08").divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_EVEN));

        // 0.25, 结果刚好精确到2位通过, 否则异常
        System.out.println(new BigDecimal("1").divide(new BigDecimal(4), 2, BigDecimal.ROUND_UNNECESSARY));
    }
}
