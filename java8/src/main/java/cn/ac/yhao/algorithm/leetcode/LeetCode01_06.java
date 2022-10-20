package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

public class LeetCode01_06 {

    /**
     *
     * 面试题：字符串压缩。<br>
     * 利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
     * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     * <p></p>示例1:<br>
     *
     *  输入："aabcccccaaa"<br>
     *  输出："a2b1c5a3"<br>
     *
     * <p>示例2:<br>
     *
     *  输入："abbccd"<br>
     *  输出："abbccd"<br>
     *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。<br>
     *
     * <p>提示：<br>
     * 字符串长度在[0, 50000]范围内。<br>
     *
     * @param S
     * @return
     */
    public String compressString(String S) {
        int len = S.length();
        if (len < 2) {
            return S;
        }
        char[] chars = S.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(chars[0]);
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                sb.append(count).append(chars[i]);
                count = 1;
            }
        }
        sb.append(count);
        String res = sb.toString();
        if (res.length() >= len) {
            return S;
        }
        return res;
    }

    @Test
    public void test() {
        String s = "aabcccccaaa";
        System.out.println(compressString(s));
        System.out.println("a2b1c5a3");

        s = "abbccd";
        System.out.println(compressString(s));
        System.out.println("abbccd");
    }

}
