package Exam.Question3;

public class Question3 {
    public static void main(String[] args) {
        long time = System.currentTimeMillis()/1000;
        long year = 1970;
        long month = 1;
        long day = 1;
        long hour = 8;
        long min = 0;
        long second = 0;
        long addday = time/86400;
         addday = addday%365 + 365+11;
         addday = addday%30+3;
         day += addday;
        System.out.println(day);
        time = time%86400;
        System.out.println(time/3600);

    }
}
