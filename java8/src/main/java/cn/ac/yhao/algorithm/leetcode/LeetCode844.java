package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 比较含退格的字符串
 *
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 *
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 *
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 *
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 */
public class LeetCode844 {
    /**
     * 重构字符串
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c != '#') {
                s.append(c);
            } else {
                if (s.length() > 0) {
                    s.deleteCharAt(s.length() - 1);
                }
            }
        }
        StringBuffer t = new StringBuffer();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (c != '#') {
                t.append(c);
            } else {
                if (t.length() > 0) {
                    t.deleteCharAt(t.length()-1);
                }
            }
        }
        return s.toString().equals(t.toString());
    }

    /**
     * 双指针
     *
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     * 具体地，我们定义 skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 skip 加 1；
     * 若该字符为普通字符：
     * 若 skip 为 0，则说明当前字符不需要删去；
     * 若 skip 不为 0，则说明当前字符需要删去，我们让 skip 减 1。
     * 这样，我们定义两个指针，分别指向两字符串的末尾。每次我们让两指针逆序地遍历两字符串，直到两字符串能够各自确定一个字符，然后将这两个字符进行比较。重复这一过程直到找到的两个字符不相等，或遍历完字符串为止。
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else if (i >= 0 || j >= 0) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

    @Test
    public void test() {
        String S, T;
        S = "ab#c"; T = "ad#c";
        Assertions.assertTrue(this.backspaceCompare(S, T));
        Assertions.assertTrue(this.backspaceCompare1(S, T));

        S = "ab##"; T = "c#d#";
        Assertions.assertTrue(this.backspaceCompare(S, T));
        Assertions.assertTrue(this.backspaceCompare1(S, T));

        S = "a##c"; T = "#a#c";
        Assertions.assertTrue(this.backspaceCompare(S, T));
        Assertions.assertTrue(this.backspaceCompare1(S, T));

        S = "a#c"; T = "b";
        Assertions.assertFalse(this.backspaceCompare(S, T));
        Assertions.assertFalse(this.backspaceCompare1(S, T));

    }
}
