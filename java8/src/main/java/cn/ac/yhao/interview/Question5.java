package cn.ac.yhao.interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
* <p>功能: 有一个列表含有N个学生，学生可以获取姓名和班级编号，其中姓名为字符串，班级编号为int。
* <p>现在对这些学生按班级分组和排序，班级之间按编号递增排序，班级内学生按照姓名字母序排序，然后顺序打印分组信息

 */
public class Question5 {

	List<Student> list = new ArrayList<>();

	public Question5() {
		list.add(new Student("ZhangDa", 2));
		list.add(new Student("ZhangEr", 1));
		list.add(new Student("ZhangSan", 3));
		list.add(new Student("LiDa", 4));
		list.add(new Student("LiEr", 1));
		list.add(new Student("LiSan", 2));
		list.add(new Student("LiSi", 3));
		list.add(new Student("ZhaoLiu", 1));
		list.add(new Student("WangDa", 2));
		list.add(new Student("WangEr", 2));
		list.add(new Student("MutouLiu", 1));
		list.add(new Student("NiuDa", 4));
		list.add(new Student("XiongDa", 2));
		list.add(new Student("XiongEr", 4));
	}

	public void answer() {
		LinkedHashMap<Integer, List<Student>> collect = list.stream()
				.sorted(Comparator.comparing(Student::getName))
				.collect(Collectors.groupingBy(Student::getClassIndex))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c1, LinkedHashMap::new));

		System.out.println(collect);
	}

	public static void main(String[] args) {
		Question5 q = new Question5();
		q.answer();
	}
}

