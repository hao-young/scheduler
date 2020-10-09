package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 *  两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class LeetCode2 {

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p1 = l1, p2 = l2, curr = head;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int x = (p1 != null) ? p1.val : 0;
            int y = (p2 != null) ? p2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(9, new ListNode(9));
        ListNode l2 = new ListNode(9);
        this.showNum(l1);
        System.out.println("");
        this.showNum(l2);
        System.out.println("");
        ListNode listNode = this.addTwoNumbers(l1, l2);
        this.showNum(listNode);

    }
    private void showNum(ListNode li) {
        System.out.print(li.val + "->");
        if (li.next != null) {
            this.showNum(li.next);
        }

    }

}

