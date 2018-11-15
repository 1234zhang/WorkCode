package Week10;

public class Student/*implements Comparable<Student>*/{
    private String name;
    private String stuClass;
    private String stuId;
    private int age;
    private char sex;

    public void setName(String name){this.name = name;}
    public void setStuClass(String stuClass){this.stuClass = stuClass;}
    public void setStuId(String stuId){this.stuId = stuId;}
    public void setAge(int age){this.age = age;}
    public void setSex(char sex){this.sex = sex;}
    public String getName(){return this.name;}
    public String getStuClass(){return this.stuClass;}
    public String getStuId(){return this.stuId;}
    public int getAge(){return this.age;}
    public char getSex(){return this.sex;}
    public String toString(){return "姓名：" + this.name + ";班级： " + this.stuClass + ";性别： " + this.getSex() + ";学号： " + this.stuId + ";年龄：" + this.age; }

    public Student(String name,String stuClass, String stuId, int age, char sex){
            this.age = age;
            this.name = name;
            this.sex = sex;
            this.stuClass = stuClass;
            this.stuId = stuId;
    }
   /* public int compareTo(Student stu){
        return this.stuId.compareTo(stu.getStuId());
    }*/

}
