package Week10;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    public List<Student> list = new ArrayList<>();
    public MyClass(){
        this.addStudent(new Student("wxz", "03030301", "13245", 12, 'M'));
        this.addStudent(new Student("as", "03031321", "15445", 12, 'M'));
        this.addStudent(new Student("ae", "12330301", "54245", 12, 'M'));
        this.addStudent(new Student("ac", "63330301", "35245", 12, 'W'));
        this.addStudent(new Student("ad", "56430301", "95245", 12, 'W'));
        this.addStudent(new Student("ag", "98630301", "45245", 12, 'W'));
    }
    public void addStudent(Student student){
        this.list.add(student);
    }

}
