package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 分割等和子集  动态规划
 * TODO 难度：中等
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class LeetCode416 {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int target = 0, maxNum = 0;
        for (int num : nums) {
            target += num;
            maxNum = Math.max(maxNum, num);
        }
        if ((target & 1) == 1) {
            return false;
        }
        target /= 2;
        if (maxNum > target) {
            return false;
        }
        int len = nums.length;
        boolean[][] dp = new boolean[len][target + 1];
        dp[0][0] = true;

        if (nums[0] == target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[len - 1][target];
    }

    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if ((s & 1) == 1) {
            return false;
        }
        s /= 2;
        boolean[] a = new boolean[s + 1];
        a[0] = true;
        for (int n : nums) {
            for (int i = s; i >= 0; i--) {
                if (a[i] && n + i <= s) {
                    a[n + i] = true;
                }
            }
        }
        return a[s];
    }

    public boolean canPartition3(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if ((s & 1) == 1) {
            return false;
        }
        s /= 2;
        Arrays.sort(nums);
        return dfs(nums, s, 0);
    }

    public boolean dfs(int[] nums, int target, int index) {
        if (target == 0) {
            return true;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - index]) {
                continue;
            }
            if (dfs(nums, target - nums[i], i + 1)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] a1 = {1,5,11,5};
        Assertions.assertTrue(this.canPartition(a1));
        Assertions.assertTrue(this.canPartition2(a1));
        Assertions.assertTrue(this.canPartition3(a1));

        int[] a2 = {1,2,3,5};
        Assertions.assertFalse(this.canPartition(a2));
        Assertions.assertFalse(this.canPartition2(a2));
        Assertions.assertFalse(this.canPartition3(a2));
    }
}
