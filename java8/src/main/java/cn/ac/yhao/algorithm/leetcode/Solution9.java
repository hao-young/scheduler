package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

public class Solution9 {

    /**
     * 最长上升子序列
     *
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 示例:
     *
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     *
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // Dynamic programming + Dichotomy.
        int res = 0;
        int[] tails = new int[nums.length];
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int m = (i+j)/2;
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[i] = num;
            if (res == j) res++;
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }

}
