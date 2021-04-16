package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 森林中的兔子
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在answers数组里。
 *
 * 返回森林中兔子的最少数量。
 *
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 *
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 *
 * 输入: answers = []
 * 输出: 0
 * 说明:
 * answers的长度最大为1000。
 * answers[i]是在[0, 999]范围内的整数。
 *
 */
public class LeetCode781 {

    public int numRabbits(int[] answers) {
        int[] m = new int[1000];
        int result = 0;
        for (int i : answers) {
            if (m[i] > 0) { //相同记录,
                m[i]--;
            } else {
                //没有相同的记录，新建记录，并添加result
                m[i] = i;
                result += i + 1;
            }
        }
        return result;
    }

    @Test
    public void test() {
//        System.out.println(this.numRabbits(new int[]{1,1,2}));
//        System.out.println(this.numRabbits(new int[]{10, 10, 10}));
        System.out.println(this.numRabbits(new int[]{1,0,1,0,0}));
        System.out.println(this.numRabbits(new int[]{}));
    }

}
