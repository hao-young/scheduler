package cn.ac.yhao.interview;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * <p>
 * 功能: 实现16个线程分别打印A~P，编程实现调度这16个线程,屏幕循环打印10次A~P
 *
 */
public class Question6 {
    Thread thread0; // 打印A
    Thread thread1; // 打印B
    Thread thread2; // 打印C
    Thread thread3; // 打印D
    Thread thread4; // 打印E
    Thread thread5; // 打印F
    Thread thread6; // 打印G
    Thread thread7; // 打印H
    Thread thread8; // 打印I
    Thread thread9; // 打印J
    Thread thread10; // 打印K
    Thread thread11; // 打印L
    Thread thread12; // 打印M
    Thread thread13; // 打印N
    Thread thread14; // 打印O
    Thread thread15; // 打印P

    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 2000, TimeUnit.MILLISECONDS, queue);

    public void answer() {
        thread0 = new Thread(new Mythread("A")); // 打印A
        thread1 = new Thread(new Mythread("B")); // 打印B
        thread2 = new Thread(new Mythread("C")); // 打印C
        thread3 = new Thread(new Mythread("D")); // 打印D
        thread4 = new Thread(new Mythread("E")); // 打印E
        thread5 = new Thread(new Mythread("F")); // 打印F
        thread6 = new Thread(new Mythread("G")); // 打印G
        thread7 = new Thread(new Mythread("H")); // 打印H
        thread8 = new Thread(new Mythread("I")); // 打印I
        thread9 = new Thread(new Mythread("J")); // 打印J
        thread10 = new Thread(new Mythread("K")); // 打印K
        thread11 = new Thread(new Mythread("L")); // 打印L
        thread12 = new Thread(new Mythread("M")); // 打印M
        thread13 = new Thread(new Mythread("N")); // 打印N
        thread14 = new Thread(new Mythread("O")); // 打印O
        thread15 = new Thread(new Mythread("P")); // 打印P

        for (int i = 0; i < 10; i++) {
            executor.execute(thread0);
            executor.execute(thread1);
            executor.execute(thread2);
            executor.execute(thread3);
            executor.execute(thread4);
            executor.execute(thread5);
            executor.execute(thread6);
            executor.execute(thread7);
            executor.execute(thread8);
            executor.execute(thread9);
            executor.execute(thread10);
            executor.execute(thread11);
            executor.execute(thread12);
            executor.execute(thread13);
            executor.execute(thread14);
            executor.execute(thread15);
        }
    }

    public static void main(String[] args) {
        Question6 q = new Question6();
        q.answer();
    }

    class Mythread implements Runnable  {

        private String str;

        public Mythread(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println(str);
        }
    }
}
