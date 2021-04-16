package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 打家劫舍
 * TODO 动态规划
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 */
public class LeetCode198 {

    public int rob(int[] nums) {
        if(nums.length==0)return 0;
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int[] dp = new int[N+1];
        dp[0]=0;
        dp[1] = nums[0];
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[N];
    }

    public int rob1(int[] nums) {
        if(nums.length==0)return 0;
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int prev=0,curr=0,temp=0;
        for (int i: nums) {
            temp = Math.max(curr, prev + i);
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    @Test
    public void test() {
        int[] a = {0}; //0
//        System.out.println(this.rob(a));
        System.out.println(this.rob1(a));

        int[] b = {2,7,9,3,1}; //12
//        System.out.println(this.rob(b));
        System.out.println(this.rob1(b));

        int[] c = {1,2,3,1}; //4
//        System.out.println(this.rob(c));
        System.out.println(this.rob1(c));

        int[] d = {1,3,1,3,100}; //103
//        System.out.println(this.rob(d));
        System.out.println(this.rob1(d));
    }
}
