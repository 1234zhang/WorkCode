package Exam.Question4;

import count.Stack;

import java.util.Scanner;

public class Question4 {
    private static Stack<Character> charStack = new Stack<Character>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str1 = scanner.next().toCharArray();
        char[] str = put(str1);
        int i = 1;charStack.push(str[0]);
       while(i <= str.length-1 ){
            char test = charStack.peek();
            int flag = compare(test,str[i]);
            switch (flag){
                case 0:
                    System.out.println("非法");
                    System.exit(1);
                case 1:
                    charStack.pop();
                    break;
                case 2:
                    charStack.push(str[i]);
                    break;
            }
            i++;
        }
        for (int j = 0; j < str.length; j++) {
            System.out.println(str[i]);
        }
     }
     public static int compare(char stackIn, char stackOut){
        int in = check(stackIn);
        int out = check(stackOut);
        int check[][] = {{2,0,2,0},{1,0,2,0},{2,0,2,1},{0,2,1,2}};
        return check[in][out];
     }
     public static int check(char str){
        int i = -1;
        switch (str){
            case '[':
                i = 0;
                break;
            case ']':
               i = 1;
                break;
            case'{':
               i = 2;
                break;
            case'}':
                i =3;
                break;
        }
        return i;
     }
    public static char[] put(char[] str){
        char[] chars = new char[10];
        int k = 0;
        for (int i = 0; i < str.length; i++) {
            switch (str[i]){
                case '[':
                    chars[k] = str[i];
                    k++;
                    break;
                case ']':
                    chars[k] = str[i];
                    k++;
                    break;
                case'{':
                    chars[k] = str[i];
                    k++;
                    break;
                case'}':
                    chars[k] = str[i];
                    k++;
                    break;
            }
        }

       return chars;
    }
}
