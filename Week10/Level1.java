package Week10;

import java.util.*;

public class Level1 {

    public static void main(String[] args) {
        MyClass myCla = new MyClass();
        String name = "as";//要删除的名字；
        for(int i = myCla.list.size()-1; i > 0; i--){
            Student moveName = myCla.list.get(i);
            if(moveName.getName().equals(name)) myCla.list.remove(moveName);
        }

        //升序
        //Collections.sort(list);
        //降序
        Collections.sort(myCla.list, new Comparator<Student>() {
            public int compare(Student stu1, Student stu2) {
                return stu2.getStuId().compareTo(stu1.getStuId());
            }
        });


        /*遍历方法一：
        for(Student stu : list){
            System.out.println(stu);
        }*/
        /*遍历方法二：
        Iterator<Student> it = list.iterator();
        while(it.hasNext()){
            Student stu = (Student) it.next();
            System.out.println(stu);
        }*/
        /*遍历方法三：（采用流式api）
        list.stream().forEach(abc -> System.out.println(abc));*/
        Iterator it = myCla.list.iterator();
        it.forEachRemaining(exe -> System.out.println(exe));
    }
}
