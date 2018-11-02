import java.util.Arrays;

public class StringWork{
    //判断是否全是数字
    public boolean isNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            char test = str.charAt(i);
            if(test > 57 || test < 48) return false;
        }
        return true;
    }
    //判断是否全是大写字母
    public boolean isAllUpper(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char test = str.charAt(i);
            if(test >= 65 && test <=90) count++;
            else return false;
        }
        if(count == str.length()) return true;
        else return false;
    }
    //将字符串转整数数组
    public int[] strToInt(String str){
        int[] num = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            num[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if(num[i] < num[j]){
                    int k = num[i];
                    num[i] = num[j];
                    num[j] = k;
                }
            }
        }
        return num;
    }
    //判断是否全是小写字母
    public boolean isAllLower(String str){
        for (int i = 0; i < str.length(); i++) {
                if(Character.isUpperCase(str.charAt(i))) return false;
                if(Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
    //判断是否全是字母；
    public boolean isAllLitter(String str){
        for (int i = 0; i < str.length(); i++) {
            char test = str.charAt(i);
            if(test > 48 && test < 57) return false;
        }
        return true;
    }
    //字符串排序，返回一个字符数组
    public char[] sort(String str){
        char[] mySort = new char[str.length()];
        for (int i = 0; i < str.length(); i++) mySort[i] = str.charAt(i);
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if(mySort[j] > mySort[i]){
                    char t = mySort[i];
                    mySort[i] = mySort[j]; mySort[j] = t;
                }
            }
        }
        return mySort;
    }
    //将小写字母转换为大写字母
    public char[] myStrUpper(String str){
        char[] myChar = new char[str.length()];
        for (int i = 0; i < str.length(); i++) myChar[i] = str.charAt(i);
        for (int i = 0; i < str.length(); i++) {
            if(myChar[i] >= 97 && myChar[i] <= 122) myChar[i] -= 32;
        }
        return myChar;
    }
    public static void main(String arg[]){
            String str = "acbdw,1269547,AASIDX,AIUydjs,12sjaA,3819247,ausSHSzio,IUFISsi";
            StringWork myClass = new StringWork();
            String[] test = str.split(",");
            System.out.println("全为字母的字符串，小写转大写，并排序：");
            for (int i = 0; i < test.length; i++) {
                if (myClass.isAllLitter(test[i]) && !myClass.isAllUpper(test[i])) {
                    //字符串转数组
                    char[] strToChar = test[i].toCharArray();
                    //原数组
                    System.out.print(strToChar);
                    System.out.print("  ");
                    //小写转大写
                    System.out.print(myClass.myStrUpper(test[i]));
                    System.out.print(" ");
                    //小写转大写之后，排序
                    System.out.println(myClass.sort(test[i]));
                }
            }
            System.out.println("全是数字的字符串，升序后输出");
            for (int i = 0; i < test.length; i++) {
                if(myClass.isNumber(test[i])){
                    int[] num = myClass.strToInt(test[i]);
                    for (int j = 0; j < num.length; j++) {
                        System.out.print(num[j]);
                    }
                    System.out.println();
                }
        }
        }
}
