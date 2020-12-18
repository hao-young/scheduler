package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入区间
 * TODO 难度：困难
 *
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
 */
public class LeetCode57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ansList = new ArrayList<>();
        boolean placed = false;
        int left = newInterval[0];
        int right = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                //在插入区间的右侧，且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                ansList.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] l1 = {{1, 3}, {6, 9}};//[[1,5],[6,9]]
        Arrays.stream(this.insert(l1, new int[]{2, 5})).forEach(i -> {
            System.out.print("[");
            Arrays.stream(i).forEach(n -> System.out.print(n + ","));
            System.out.print("]");
        });
        System.out.println("\n------------------");
        int[][] l2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};//[[1,2],[3,10],[12,16]]
        Arrays.stream(this.insert(l2, new int[]{4, 8})).forEach(i -> {
            System.out.print("[");
            Arrays.stream(i).forEach(n -> System.out.print(n + ","));
            System.out.print("]");
        });
    }

}
