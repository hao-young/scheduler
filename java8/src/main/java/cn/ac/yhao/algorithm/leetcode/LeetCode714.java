package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 买卖股票的最佳时机含手续费
 * TODO 难度：中等
 *
 * 给定一个整数数组prices，其中第i个元素代表了第i天的股票价格 ；非负整数fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 */
public class LeetCode714 {

    /**
     * 贪心算法
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int sum = 0;
        int min = prices[0];
        for (int price : prices) {
            min = Math.min(min, price);
            if (price > min + fee) {
                sum += price - min - fee;
                min = price - fee; //计算利润，不一定这次卖出
            }
        }
        return sum;
    }

    /**
     * 动态规划：
     * 因为「不能同时参与多笔交易」，因此每天交易结束后只有持有股票和不持股票两种状态。
     * 定义状态 dp[i][0] 表示第i天交易后不持有股票的最大收益，dp[i][1] 表示第i天交易后持有股票的最大收益（i从0开始）
     * dp[i][0] = max{dp[i-1][0], dp[i-1][1]-prices[i]-fee}
     * dp[i][1] = max{dp[i][1], dp[i-1][0] - prices[i]}
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * 动态规划：
     * 注意到在状态转移方程中，dp[i][0] 和 dp[i][1] 只会从dp[i−1][0] 和dp[i−1][1] 转移而来，因此我们不必使用数组存储所有的状态，
     * 而是使用两个变量sell 以及 buy 分别表示dp[..][0] 和 dp[..][1] 直接进行状态转移即可
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int sell = 0, buy= -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    @Test
    public void test() {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        Assertions.assertEquals(8, this.maxProfit(prices, 2));
        Assertions.assertEquals(8, this.maxProfit1(prices, 2));
        Assertions.assertEquals(8, this.maxProfit2(prices, 2));

        prices = new int[]{1,3,7,5,10,3};
        Assertions.assertEquals(6, this.maxProfit(prices, 3));
        Assertions.assertEquals(6, this.maxProfit1(prices, 3));
        Assertions.assertEquals(6, this.maxProfit2(prices, 3));
    }

}
