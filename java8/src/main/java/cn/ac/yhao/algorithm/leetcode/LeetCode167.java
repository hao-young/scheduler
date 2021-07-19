package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description:
 * 两数之和 II - 输入有序数组
 *
 * 给定一个已按照 升序排列 的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 *  示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 *
 *  示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class LeetCode167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] answer = new int[2];
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] == target) {
                answer[0] = low + 1;
                answer[1] = high + 1;
                return answer;
            } else if (numbers[low] + numbers[high] < target) {
                low++;
            } else {
                high--;
            }
        }
        return answer;
    }

    @Test
    public void test() {
        int[] a = {2, 7, 11, 15};
        Arrays.stream(twoSum(a, 9)).forEach(i-> System.out.print(i+",")); //1,2

        System.out.println("");
        int[] b = {2, 3, 4};
        Arrays.stream(twoSum(b, 6)).forEach(i-> System.out.print(i+","));//1,3

        System.out.println("");
        int[] c = {-1, 0};
        Arrays.stream(twoSum(c, -1)).forEach(i-> System.out.print(i+",")); //1,2

        System.out.println("");
        int[] d = {1, 2, 3, 4, 4, 9, 56, 90};
        Arrays.stream(twoSum(d, 8)).forEach(i-> System.out.print(i+","));//4,5
    }

}
