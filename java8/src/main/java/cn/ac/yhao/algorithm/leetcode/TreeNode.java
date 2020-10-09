package cn.ac.yhao.algorithm.leetcode;

public class TreeNode {
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
