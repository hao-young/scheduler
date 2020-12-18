package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LeetCode7 {

    /**
     * 整数反转
     *
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 注意：假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
     *
     * 示例1:
     *
     * 输入: 123
     * 输出: 321
     * 示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rec = 0;
        while (x!=0) {
            int pop = x % 10;
            if (rec > Integer.MAX_VALUE / 10 || (rec == Integer.MAX_VALUE && pop > 7)
                    || rec < Integer.MIN_VALUE / 10 || (rec == Integer.MIN_VALUE && pop < -8)) {
                return 0;
            }
            rec = rec*10+ x%10;
            x = x / 10;
        }
        return rec;
    }

    @Test
    public void test() {

        Assertions.assertEquals(321, this.reverse(123));
        Assertions.assertEquals(-321, this.reverse(-123));
        Assertions.assertEquals(21, this.reverse(120));
        int s = -153236469;
        int reverse = this.reverse(s);
        System.out.println(s);
        System.out.println(reverse);
    }
}
