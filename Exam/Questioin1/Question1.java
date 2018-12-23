package Exam.Questioin1;

import java.io.IOException;
import java.util.Scanner;

public class Question1 {
    private static Student[] students;
    static {
        try {
            students = FileUtil.parseStudents("D:\\afterWork\\src\\Exam\\Questioin1\\students.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查找学号:");
        String key = sc.next();
        int flag = compare(key, 0, students.length);
        if(flag == 0){
         System.out.println("学生不存在");
        }
    }
    public static int compare(String key, int low, int height){
        if(low > height){return 0;}
        int mid = (low + height)/2;
        if(students[mid].getStuid().compareTo(key) > 0){
            compare(key, low, mid - 1);
        }else if(students[mid].getStuid().compareTo(key) < 0){
            compare(key,mid + 1,height);
        }else {
            System.out.println("姓名：" + students[mid].getName() + "学号：" + students[mid].getStuid());
            System.exit(1);
        }
        return 0;
    }
}
