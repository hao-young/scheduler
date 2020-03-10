package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {

    /**
     * 算法 ： 滑动窗口
     *
     * 无重复字符的最长子串
     *
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(map.get(s.charAt(i)), j);
            }
            ans = Math.max(ans, i - j + 1);
            map.put(s.charAt(i), i + 1);
        }
        return ans;
    }
    public int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        int leftIndex = 0;
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++) {
            for(int j=leftIndex; j<i; j++) {
                if(chars[j] == chars[i]) {
                    maxLength = Math.max(maxLength, i - leftIndex);
                    leftIndex = j+1;
                    break;
                }
            }
        }
        return Math.max(maxLength, chars.length - leftIndex);
    }

    @Test
    public void lengthOfLongestSubstringtest() {
        String s = "ad";
        int leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
        s = "";
        leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
        s = " ";
        leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
        s = "abcabcbb";
        leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
        s = "bbbbb";
        leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
        s = "pwwkew";
        leng = this.lengthOfLongestSubstring2(s);
        System.out.println(s + " => " + leng);
    }
}
