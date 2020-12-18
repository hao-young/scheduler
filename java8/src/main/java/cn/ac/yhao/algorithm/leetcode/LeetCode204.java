package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *
 * 提示：
 * 0 <= n <= 5 * 10^6
 *
 */
public class LeetCode204 {

    /**
     * 埃氏筛
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long)i * i < n) {
                    for (int j = i * i; j < n; j+=i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 线性筛
     * @return
     */
    public int countPrimes1() {
        return 0;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, this.countPrimes(0));
        Assertions.assertEquals(0, this.countPrimes(1));
        Assertions.assertEquals(4, this.countPrimes(10));
        Assertions.assertEquals(41537, this.countPrimes(499979));
    }
}
