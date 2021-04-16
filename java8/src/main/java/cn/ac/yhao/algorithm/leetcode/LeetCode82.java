package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中没有重复出现的数字。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class LeetCode82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = new ListNode(0, head);
        ListNode tem = p;
        while (tem.next != null && tem.next.next != null) {
            if (tem.next.val == tem.next.next.val) {
                int c = tem.next.val;
                while (tem.next != null && tem.next.val == c) {
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
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode node = this.deleteDuplicates(node1);
        node.show();

    }

}
