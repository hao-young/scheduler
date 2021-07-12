package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * H 指数 II
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
 *
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * （其余的N - h篇论文每篇被引用次数不多于 h 次。）"
 *
 * 示例:
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3 
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * 
 * 说明:
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 */
public class LeetCode275 {

    public int hIndex(int[] citations) {
        int len = citations.length;
        int start = 0;
        int end = len - 1;
        int res = 0;
        while (start <=end) {
            int mid = (start + end) >> 1;
            int paperCont = len - mid;
            if (paperCont <= citations[mid]) {
                res = paperCont;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] citations = {0,1,3,5,6};
        int h = hIndex(citations);
        System.out.println(h); //3

        int[] ci = {1, 1};
        h = hIndex(ci);
        System.out.println(h); //1

        int[] cit = {0};
        h = hIndex(cit);
        System.out.println(h);
    }
}
