package cn.ac.yhao.interview;

/**
 * 
* <p>功能: 编写单例模式的程序
 */
public class Question2 {
	public void answer() {
		System.out.println(this.hashCode());
	}

	private Question2(){}

	public static Question2 getInstance(){
		return Question2Singletion.INSTANCE;
	}

	private static class Question2Singletion{
		public static final Question2 INSTANCE = new Question2();
	}

	public static void main(String[] args) {
		Question2 question2 = Question2.getInstance();
		question2.answer();
	}
}
