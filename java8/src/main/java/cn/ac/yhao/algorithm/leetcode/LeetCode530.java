package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 示例：
 * 输入：
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 */
public class LeetCode530 {
    int ans,pre;

    /**
     * 二叉搜索树中序遍历得到的值序列是递增有序的
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) return ;
        dfs(node.left);
        if (pre != -1) ans = Math.min(ans, node.val - pre);
        pre = node.val;
        dfs(node.right);
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(1, null, new TreeNode(3, new TreeNode(2), null));
        Assertions.assertEquals(1, this.getMinimumDifference(node));
    }
}
