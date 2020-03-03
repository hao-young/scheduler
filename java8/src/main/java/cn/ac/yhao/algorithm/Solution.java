package cn.ac.yhao.algorithm;

import org.junit.jupiter.api.Test;


public class Solution {

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123Solution
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     */
    public int reverse(int x) {
        int rec = 0;
        while (x!=0) {
            if (rec > Integer.MAX_VALUE/10 || rec < Integer.MIN_VALUE/10){
                return 0;
            }
            rec = rec*10+ x%10;
            x = x / 10;
        }
        return rec;
    }


    /**
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
     *
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
    public void addTwoNumbersTest() {
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

    @Test
    public void reverseTest() {
        int s = -153236469;
        int reverse = this.reverse(s);
        System.out.println(s);
        System.out.println(reverse);

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

}