package com.pmcc.revicesell.utils;/**
 * Created by 天地 on 2018/4/12.
 */

/**
 * @author 天地
 * @create 2018-04-12 9:50
 * @desc
 **/
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两者金额
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(double d1, double d2) {
        double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
