package Exam.Question2;

import Exam.Questioin1.FileUtil;
import Exam.Questioin1.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Question2 {
    private static Student[] students;
    private static HashMap<String, Integer> map;

    static {
        try {
            students = FileUtil.parseStudents("D:\\afterWork\\src\\Exam\\Questioin1\\students.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> result = most();
        ArrayList<Integer> list = new ArrayList<Integer>(result.values());
        Set<String> set = result.keySet();
        System.out.println("一共有：" + list.size() + "个名字最多,分别：");
        System.out.println(set);
        System.out.println("一共出现了：" + list.get(0));
    }
    private static HashMap<String,Integer> most(){
        map = new HashMap<String, Integer>();
        String str = "";
        int count = 0, result = 0;
        for (int i = 0; i < students.length; i++) {
            str += students[i].getName();
        }
        for (int i = 0; i < students.length; i++) {
            String tmp = str.replaceAll(students[i].getName(),"");
            count = (str.length() - tmp.length())/students[i].getName().length();
            if(count > result){
                map.clear();
                map.put(students[i].getName(),count);
                result = count;
            }else if(result == count){
                map.put(students[i].getName(),count);
            }
        }
        return  map;
    }
}
