package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class LeetCode83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = new ListNode(0, head);
        ListNode tem = p;
        while (tem != null && tem.next != null) {
            if (tem.val == tem.next.val) {
                while (tem.next != null && tem.val == tem.next.val) {
                    tem.next = tem.next.next;
                }
            } else {
                tem = tem.next;
            }
        }
        return p.next;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1, new ListNode(1, new ListNode(2)));
        ListNode node = this.deleteDuplicates(node1);
        node.show();

        System.out.println("");
        node1 = new ListNode(1, new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(3)))));
        node = this.deleteDuplicates(node1);
        node.show();
    }
}
