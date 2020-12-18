package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 找不同
 *
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串t由字符串s随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 *
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 *
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 *
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *
 */
public class LeetCode389 {

    public char findTheDifference(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chart);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chart[i]) {
                return chart[i];
            }
        }
        return chart[chart.length-1];
    }

    /**
     * 计数
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference1(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            cnt[c-'a']--;
            if (cnt[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 求和
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); i++) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            at += t.charAt(i);
        }
        return (char)(at - as);
    }

    /**
     * 位运算
     * 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符
     *
     * a^a=0; 任何数字和自己异或都是0
     * a^0=a; 任何数字和0异或还是他自己
     * a^b^a=a^a^b 异或运算具有交换律
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            ret ^= t.charAt(i);
        }
        return (char)(ret);
    }

    /**
     * 位运算
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference4(String s, String t) {
        return (char) (s + t).chars().reduce(0, (c, d) -> c ^ d);
    }

    @Test
    public void test() {
        Assertions.assertEquals('e', this.findTheDifference("abcd","abcde"));
        Assertions.assertEquals('y', this.findTheDifference("","y"));
        Assertions.assertEquals('a', this.findTheDifference("a","aa"));
        Assertions.assertEquals('a', this.findTheDifference("ae","aea"));
        Assertions.assertEquals('e', this.findTheDifference("a","ae"));

        Assertions.assertEquals('e', this.findTheDifference1("abcd","abcde"));
        Assertions.assertEquals('y', this.findTheDifference1("","y"));
        Assertions.assertEquals('a', this.findTheDifference1("a","aa"));
        Assertions.assertEquals('a', this.findTheDifference1("ae","aea"));
        Assertions.assertEquals('e', this.findTheDifference1("a","ae"));

        Assertions.assertEquals('e', this.findTheDifference2("abcd","abcde"));
        Assertions.assertEquals('y', this.findTheDifference2("","y"));
        Assertions.assertEquals('a', this.findTheDifference2("a","aa"));
        Assertions.assertEquals('a', this.findTheDifference2("ae","aea"));
        Assertions.assertEquals('e', this.findTheDifference2("a","ae"));

        Assertions.assertEquals('e', this.findTheDifference3("abcd","abcde"));
        Assertions.assertEquals('y', this.findTheDifference3("","y"));
        Assertions.assertEquals('a', this.findTheDifference3("a","aa"));
        Assertions.assertEquals('a', this.findTheDifference3("ae","aea"));
        Assertions.assertEquals('e', this.findTheDifference3("a","ae"));

        Assertions.assertEquals('e', this.findTheDifference4("abcd","abcde"));
        Assertions.assertEquals('y', this.findTheDifference4("","y"));
        Assertions.assertEquals('a', this.findTheDifference4("a","aa"));
        Assertions.assertEquals('a', this.findTheDifference4("ae","aea"));
        Assertions.assertEquals('e', this.findTheDifference4("a","ae"));
    }
}
