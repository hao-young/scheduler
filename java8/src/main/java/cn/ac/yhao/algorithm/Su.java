package cn.ac.yhao.algorithm;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Su {

    /**
     * 等比数列求和
     * Sn = a1*(1-q^n)/(1-q)
     * @param a  首项
     * @param q  等比
     * @param n  项数
     */
    public static double geometricSum(double a, double q, int n) {
        if (q == 1) {
            return a*n;
        }
        return a * (1-Math.pow(q, n))/(1-q);
    }

    @Test
    public void test() {
        System.out.println(geometricSum(100, 1, 2));
    }
}
