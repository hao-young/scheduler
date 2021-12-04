package cn.ac.yhao.java8;

import java.util.stream.Stream;

/**
 * @description:
 * @author: Daniel Young
 * @create: 2021-10-07 16:40
 */
public class StreamExamples {

    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, x) -> acc + x);
    }


}
