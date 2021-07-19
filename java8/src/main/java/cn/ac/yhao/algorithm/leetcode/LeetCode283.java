package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description:
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 */
public class LeetCode283 {
    public void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    @Test
    public void test(){
        int[] a = {0,1,0,3,12,0,0,4};
        moveZeroes(a);
        Arrays.stream(a).forEach(i-> System.out.print(i+","));

        System.out.println("");
        int[] b = {0, 0,1};
        moveZeroes(b);
        Arrays.stream(b).forEach(i-> System.out.print(i+","));
    }
}
