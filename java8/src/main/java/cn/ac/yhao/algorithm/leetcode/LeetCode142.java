package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 */
public class LeetCode142 {

    /**
     * 哈希表
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 快慢指针
     *设从头到环入口的距离为a, 当快慢指针相遇时，快指针走了n圈，慢指针走了m圈，环入口到相遇点的距离为b, 相遇点到换入口的距离为c,
     * 快指针走的距离为满指针的2倍，则 a+n(b+c)+b = 2(a+m(b+c)+b) => a = (n-2m-1)(b+c)+c,
     * 所以相遇后，慢指针向前走的同时，另一指针从头开始走，他们会在入口出相遇
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test
    public void test() {

    }
}
