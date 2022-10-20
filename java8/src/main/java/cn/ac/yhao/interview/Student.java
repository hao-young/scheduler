package cn.ac.yhao.interview;

/**
 * <span>
 * 学生类型
 * </span>
 *
 * @author: Daniel Young
 * @create: 2022-06-05 10:15:32
 */
public class Student {
    public Student(String name, int classIndex) {
        super();
        this.name = name;
        this.classIndex = classIndex;
    }

    String name;
    int classIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", classIndex=").append(classIndex);
        sb.append('}');
        return sb.toString();
    }
}
