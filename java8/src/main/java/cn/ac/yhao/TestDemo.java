package cn.ac.yhao;

import java.util.Calendar;

public class TestDemo {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,25);
        System.out.println(cal.getTime());
    }
}