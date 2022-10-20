package cn.ac.yhao.interview;

/**
 *
 * <p>
 * 功能: 编写一个程序,在运行时触发StackOverflow
 *
 */
public class Question3 {

    int num = 1;
    public void answer() {
        num++;
        this.answer();
    }

    public static void main(String[] args) {
        Question3 q = new Question3();
        q.answer();
    }
}
