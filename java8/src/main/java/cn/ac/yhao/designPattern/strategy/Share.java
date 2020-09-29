package cn.ac.yhao.designPattern.strategy;

import java.util.HashMap;
import java.util.Map;

public class Share {
    private static Map<String, DealStrategy> algs = new HashMap<>();
    //静态代码块，先加载所有的策略
    static {
        algs.put("sina", new DealSina());
        algs.put("weChat", new DealWeChat());
    }

    public void shareOptions(String type) {
        algs.get(type).dealMethod("agrs");
    }

    public static void main(String[] args) {
        new Share().shareOptions("weChat");
    }

}
