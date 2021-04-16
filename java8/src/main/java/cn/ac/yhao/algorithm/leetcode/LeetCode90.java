package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 子集 II
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 */
public class LeetCode90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        if (nums == null || nums.length == 0) return ans;

        Arrays.sort(nums);
        List<Integer> a = new ArrayList<>();
        a.add(nums[0]);
        ans.add(a);
        if (nums.length == 1) return ans;

        int lastLen = 1;
        for (int i = 1; i < nums.length; i++) {
            int size = ans.size();
            if (nums[i] != nums[i - 1]) {
               lastLen = size;
            }

            for (int j = size - lastLen; j < size; j++) {
                List<Integer> inner = new ArrayList<>(ans.get(j));
                inner.add(nums[i]);
                ans.add(inner);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {0};
        System.out.println(this.subsetsWithDup(nums));

        int[] nums1 = {1, 2, 2};
        System.out.println(this.subsetsWithDup(nums1));

        int[] nums2 = {4,4,4,1,4};
        System.out.println(this.subsetsWithDup(nums2));
    }

}
