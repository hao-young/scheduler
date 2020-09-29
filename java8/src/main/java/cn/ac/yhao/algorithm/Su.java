package cn.ac.yhao.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Su {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse("2020-07-20 11:12:33"));

            System.out.println(sdf.format(cal.getTime()));
            System.out.println(sdf.format(sdf.parse("2020-07-20 11:12:33")));
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Date time1 = cal.getTime();
            cal.add(Calendar.DATE,1);
            Date time2 = cal.getTime();
            System.out.println(sdf.format(time1));
            System.out.println(sdf.format(time2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        System.out.println(geometricSum(100, 1, 2));
    }

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
}
