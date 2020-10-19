package cn.ac.yhao.algorithm.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public void show() {
        System.out.print(this.val);
        if (this.next != null) {
            System.out.print("->");
            this.next.show();
        }
    }
}
