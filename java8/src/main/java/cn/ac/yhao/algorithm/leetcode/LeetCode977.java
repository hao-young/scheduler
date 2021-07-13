package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description:
 * 有序数组的平方
 * 
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 *  示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 * 
 * 进阶：
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 */
public class LeetCode977 {

    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] res = new int[nums.length];
        int i = nums.length - 1;
        while (left <= right) {
            int m = nums[left] * nums[left];
            int n = nums[right] * nums[right];
            if (n >= m) {
                res[i] = n;
                right--;
            } else {
                res[i] = m;
                left++;
            }
            i--;
        }
        return res;
    }

    @Test
    public void test(){
        int[] a = {-4, -1, 0, 3, 10};
        Arrays.stream(sortedSquares(a)).forEach(i-> System.out.print(i+","));//0,1,9,16,100
        System.out.println("");

        int[] b = {-7, -3, 2, 3, 11};
        Arrays.stream(sortedSquares(b)).forEach(i-> System.out.print(i+",")); //4,9,9,49,121
    }

}
