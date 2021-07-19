package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * @description:
 * 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 */
public class LeetCode557 {
    public String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i].length()-1; j >= 0 ; j--) {
                sb.append(arr[i].charAt(j));
            }
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }

}
