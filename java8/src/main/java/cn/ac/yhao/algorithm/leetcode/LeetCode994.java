package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description:
 * 腐烂的橘子
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 * 值0代表空单元格；
 * 值1代表新鲜橘子；
 * 值2代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回-1。
 *
 * 示例 1：
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 *
 *  示例 2：
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 *
 *  示例 3：
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 * 提示：
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为0、1或2
 *
 */
public class LeetCode994 {

    //bfs求无权图最短路的问题
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length ==0) {
            return 0;
        }
        int cnt = 0;
        int[] p1 = {1, -1, 0, 0}, p2 = {0, 0, 1, -1};
        Deque<Integer> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        //把腐烂的🍊加入队列中，作为开始扩散的起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i * n + j);
                } else if (grid[i][j] == 1) {
                    cnt++; //统计新鲜🍊的数量
                }
            }
        }

        //从腐烂的🍊开始感染，其实就是一个bfs求无权图最短路的问题
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int temp1 = p / n, temp2 = p % n;
                for (int j = 0; j < p1.length; j++) {
                    int x = temp1 + p1[j], y = temp2 + p2[j];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                        cnt--; // 每传染一个，更新新鲜橘子的数量
                        grid[x][y] = 2;
                        queue.offer(x * n + y);
                    }
                }
            }
            if (!queue.isEmpty()) {
                steps++;
            }
        }

        return cnt == 0 ? steps : -1;

    }

    @Test
    public void test(){
        int[][] p = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(p)); //4
    }
    
}
