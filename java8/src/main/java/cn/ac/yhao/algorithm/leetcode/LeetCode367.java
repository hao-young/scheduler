package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @description: 有效的完全平方数
 * [二分查找,牛顿迭代法]
 *
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 *
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 *
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 *
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class LeetCode367 {

    //二分法
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int left = 1, right = num;
        int mid, tem;
        while (left < right) {
            mid = left + (right - left) / 2;
            tem = mid * mid;
            if (tem == num) {
                return true;
            } else if (tem < num) {
                return false;
            } else if (tem > num) {
                right = mid;
            }
        }
        return false;
    }

    //牛顿-拉弗森迭代法
    public boolean isPerfectSquare1(int num) {
        if (num == 1) {
            return true;
        }
        double x = num;
        while (x * x - num >= 1e-6) {
            x = x / 2 + num / (2 * x);
        }
        int ans = (int) x;
        return ans * ans == num;
    }

    @Test
    public void test() {
        Assertions.assertTrue(isPerfectSquare(16));
        Assertions.assertTrue(isPerfectSquare(1));
        Assertions.assertFalse(isPerfectSquare(14));

        Assertions.assertTrue(isPerfectSquare1(16));
        Assertions.assertTrue(isPerfectSquare1(1));
        Assertions.assertFalse(isPerfectSquare1(14));
    }
}
