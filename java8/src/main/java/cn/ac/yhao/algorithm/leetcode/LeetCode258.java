package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <span>
 * 各位相加
 * </span>
 *
 *  <p>给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。</p>
 *
 *  示例 1: <br>
 * 输入: num = 38 <br>
 * 输出: 2 <br>
 * 解释: 各位相加的过程为： <br>
 * 38 --> 3 + 8 --> 11 <br>
 * 11 --> 1 + 1 --> 2 <br>
 * 由于2 是一位数，所以返回 2。 <br>
 *
 * <br>
 * 示例 1: <br>
 * 输入: num = 0 <br>
 * 输出: 0 <br>
 *
 * <br>
 * 提示：<br>
 * 0 <= num <= 2<sup>31</sup>- 1
 *
 * @author: Daniel Young
 * @create: 2022-03-03 09:48:22
 */
public class LeetCode258 {

    /**
     * 这道题的本质是计算自然数 num 的数根。
     *
     * 数根又称数字根（Digital root），是自然数的一种性质，每个自然数都有一个数根。对于给定的自然数，反复将各个位上的数字相加，直到结果为一位数，则该一位数即为原自然数的数根
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        int res = num;
        while (res > 9) {
            int n = res;
            res = n / 10 + n % 10;
        }
        return res;
    }

    /**
     * 数学公式
     * 这道题的本质是计算自然数 num 的数根。
     *
     * 数根又称数字根（Digital root），是自然数的一种性质，每个自然数都有一个数根。对于给定的自然数，反复将各个位上的数字相加，直到结果为一位数，则该一位数即为原自然数的数根
     *
     * 同余定理
     *
     */
    public int addDigits1(int num) {
        return (num - 1) % 9 + 1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(addDigits(38), 2);
        Assertions.assertEquals(addDigits(0), 0);

        Assertions.assertEquals(addDigits1(38), 2);
        Assertions.assertEquals(addDigits1(0), 0);

    }

}
