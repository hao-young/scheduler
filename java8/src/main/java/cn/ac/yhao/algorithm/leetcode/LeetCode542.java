package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @description:
 * 01 矩阵
 *
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1：
 * 输入：mat = [[0,0,0],
 *            [0,1,0],
 *            [0,0,0]]
 * 输出：[[0,0,0],
 *      [0,1,0],
 *      [0,0,0]]
 *
 * 示例 2：
 * 输入：mat = [[0,0,0],
 *            [0,1,0],
 *            [1,1,1]]
 * 输出：[[0,0,0],
 *      [0,1,0],
 *      [1,2,1]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0
 *
 */
public class LeetCode542 {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int l = 10*10*10*10;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = l;
            }
        }

        //从左上角开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else  {
                    if (i - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }
        //从右下角开始
        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i + 1 < m) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                    if (j + 1 < n) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                }
            }
        }

        return dp;
    }

    @Test
    public void test(){
        int[][] mat = {{1,0,1,1,0,0,1,0,0,1},
                       {0,1,1,0,1,0,1,0,1,1},
                       {0,0,1,0,1,0,0,1,0,0},
                       {1,0,1,0,1,1,1,1,1,1},
                       {0,1,0,1,1,0,0,0,0,1},
                       {0,0,1,0,1,1,1,0,1,0},
                       {0,1,0,1,0,1,0,0,1,1},
                       {1,0,0,0,1,1,1,1,0,1},
                       {1,1,1,1,1,1,1,0,1,0},
                       {1,1,1,1,0,1,0,0,1,1}};
        Arrays.stream(updateMatrix(mat)).forEach(i-> {
            Arrays.stream(i).forEach(j-> System.out.print(j+","));
            System.out.println("");
        });

    }

}
