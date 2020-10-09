package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeetCode121 {

    /**
     * 买卖股票的最佳时机
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length;i++) {
            for(int j=i+1;j<prices.length;j++) {
                max = Math.max(max, prices[j]-prices[i]);
            }
        }
        return max;
    }

    /**
     * 贪心算法
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int max = 0;
        int low = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < low) {
                low = prices[i];
            }
            max = max > (prices[i] - low) ? max : prices[i] - low;
        }
        return max;
    }

    @Test
    public void test() {
        int[] prices = {7,1,5,3,6,4};
        Assertions.assertEquals(5, this.maxProfit(prices));
        Assertions.assertEquals(5, this.maxProfit1(prices));

        prices = new int[]{7,6,4,3,1};
        Assertions.assertEquals(0, this.maxProfit(prices));
        Assertions.assertEquals(0, this.maxProfit1(prices));
    }
}
