package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
 *
 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为O(1) 的原地算法解决这个问题吗？
 * 
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出:        [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释: 
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 */
public class LeetCode189 {

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        rotate(a, 3);
        Arrays.stream(a).forEach(i -> System.out.print(i + ",")); //5,6,7,1,2,3,4

        System.out.println("");
        int[] b = {-1, -100, 3, 99};
        rotate(b, 2);
        Arrays.stream(b).forEach(i -> System.out.print(","+i)); //3,99,-1,-100

    }



}
