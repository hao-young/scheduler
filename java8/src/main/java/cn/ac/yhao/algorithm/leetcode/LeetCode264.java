package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数2、3 和/或5的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * 1 <= n <= 1690
 */
public class LeetCode264 {

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0]=1;
        int p2=0,p3=0,p5=0;
        int n2,n3,n5;
        for (int i = 1; i < n; i++) {
            n2 = 2 * dp[p2];
            n3 = 3 * dp[p3];
            n5 = 5 * dp[p5];
            dp[i]= Math.min(Math.min(n2,n3), n5);
            if (dp[i] == n2) {
                p2++;
            }
            if (dp[i] == n3) {
                p3++;
            }
            if (dp[i] == n5) {
                p5++;
            }
        }
        return dp[n-1];
    }

    @Test
    public void test() {
        System.out.println(this.nthUglyNumber(40));
//        System.out.println(this.nthUglyNumber(1));
    }
}
