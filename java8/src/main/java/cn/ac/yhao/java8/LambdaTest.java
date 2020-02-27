package cn.ac.yhao.java8;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.IntConsumer;

interface MathOperation {
    int operation(int a, int b);
}

public class LambdaTest {

    public static void main(String[] args) {
        //类型声明
        MathOperation add = (int a, int b) -> a + b;
        //不声明类型
        MathOperation sub = (a, b) -> a - b;
        //带有大括号，带有返回语句
        MathOperation mul = (int a, int b) -> {
            return a * b;
        };
        //不带大括号及返回语句
        MathOperation div = (int a, int b) -> a / b;

        /*
        System.out.println("10 + 5 = "+ operator(10, 5, add));
        System.out.println("10 - 5 = "+ operator(10, 5, sub));
        System.out.println("10 * 5 = "+ operator(10, 5, mul));
        System.out.println("10 / 5 = "+ operator(10, 5, div));
        */
        /*
        String[] words = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn"};
        Arrays.sort(words, (first, second) -> first.length() - second.length());
        for (String word : words) {
            System.out.println(word);
        }
        */

        List list = new ArrayList();
        list.add(null);
        list.add("1");
        list.add(null);
        list.add("2");
        list.removeIf(Objects::isNull);

        list.forEach(e -> System.out.println(e));
        /**
         * 重复一个动作n次
         */
        //repeat(10, i-> System.out.println("Countdown:"+(9-i)));

    }

    private static int operator(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }


}

