package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
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
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *   请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 */
public class LeetCode3 {

    /**
     * 算法 ： 滑动窗口
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

    /**
     * 滑动窗口，数组优化
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int[] dict = new int[128];
        Arrays.fill(dict, -1);
        int longest = 0, m = 0;
        for (int i = 0; i < s.length(); i++) {
            m = Math.max(dict[s.charAt(i)] + 1, m);
            dict[s.charAt(i)] = i;
            longest = Math.max(longest, i - m + 1);
        }
        return longest;
    }

    public int lengthOfLongestSubstring3(String s) {
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
    public void test() {
        Assertions.assertEquals(2, this.lengthOfLongestSubstring("ad"));
        Assertions.assertEquals(0, this.lengthOfLongestSubstring(""));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring(" "));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring("abcabcbb"));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring("bbbbb"));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring("pwwkew"));

        Assertions.assertEquals(2, this.lengthOfLongestSubstring2("ad"));
        Assertions.assertEquals(0, this.lengthOfLongestSubstring2(""));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring2(" "));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring2("abcabcbb"));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring2("bbbbb"));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring2("pwwkew"));

        Assertions.assertEquals(2, this.lengthOfLongestSubstring3("ad"));
        Assertions.assertEquals(0, this.lengthOfLongestSubstring3(""));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring3(" "));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring3("abcabcbb"));
        Assertions.assertEquals(1, this.lengthOfLongestSubstring3("bbbbb"));
        Assertions.assertEquals(3, this.lengthOfLongestSubstring3("pwwkew"));
    }
}
