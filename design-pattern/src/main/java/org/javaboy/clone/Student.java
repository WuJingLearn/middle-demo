package org.javaboy.clone;

/**
 * 通过clone可以创建一个新的对象出来。
 * 对象中的值属性（包括字符串，基本类型的包装类） 都是赋值
 * 对象属性是直接赋值的引用；
 * 要想实现深拷贝，需要重写clone方法，每个引用类型的变量都去重写clone方法
 */
public class Student implements Cloneable{
    
    private String name;
    private int age;

    private School school;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * 深拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        School school = (School) this.school.clone();
        clone.setSchool(school);
        return clone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setAge(10);
        student.setName("zs");

        School school = new School();
        school.setName("广德中学");
        student.setSchool(school);

        Student cloneStu = (Student) student.clone();
        System.out.println(cloneStu);

        student.setName("ls");
        student.setAge(11);
        school.setName("广德大学");

        /**
         * 在浅拷贝中String,Integer这些类型，是值的拷贝。
         * 对象是引用拷贝。
         *
         * 深拷贝的其他方式：
         * 1.序列化 反序列化操作。
         * 2.clone中重写
         */
        System.out.println(cloneStu);

    }
}
