package cn.ac.yhao.algorithm.leetcode;


import org.junit.jupiter.api.Test;

public class Solution5 {

    /**
     * 二叉树的直径
     *
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        deep(root);
        return ans;
    }
    int ans = 0;
    // DFS 深度优先遍历
    private int deep(TreeNode node) {
        if (node == null) return 0;
        int l = deep(node.left);
        int r = deep(node.right);
        ans = Math.max(ans, l + r);
        return Math.max(l, r) + 1;
    }

    @Test
    public void treeTest() {
        TreeNode node = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        System.out.println(diameterOfBinaryTree(node));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        TreeNode(int x, TreeNode lNode, TreeNode rNode) {
            val = x;
            left = lNode;
            right = rNode;
        }
    }
}


