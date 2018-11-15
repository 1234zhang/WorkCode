package Week10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Level3 {
    public static String getString(URL url){
        String test = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        byte[] bytes = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            test = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining(System.lineSeparator()));
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return test;
    }
    public static void main(String[] args) {
        String inputMajor = null;
        String inputCollage = null;
        String inputGrade = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("输入希望查找班级的学院，专业，年纪：");
        inputCollage = sc.nextLine();
        inputMajor = sc.nextLine();
        inputGrade = sc.nextLine();
        URL url = null;
        String line = null;
        Matcher m = null;
        Set<String> myClass = new HashSet<>();
        String pattern = "<h3>"+ inputCollage +"</h3><div.+><table>[\\s\\S]*?</table></div>";
        String pattern1 = "<tr><td>[\\d]+?-" + inputMajor + "</td><td>[\\s\\S]*?</td></tr>";
        String pattern2 = "\\d+" + inputGrade + "\\d+";
        Pattern p = Pattern.compile(pattern);
        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);
        try {
            url = new  URL("http://jwzx.cqupt.edu.cn/kebiao/index.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url != null) {
            line = getString(url);
        }
        if(line != null){
            m = p.matcher(line);
        }
        if (m != null && m.find()) {
            Matcher m2 = p1.matcher(m.group());
            if(m2 != null && m2.find()){
                String test = m2.group(0);
            String[] test1 = test.split("\\D+");
            for (int i = 2; i < test1.length ; i++) {
                myClass.add(test1[i]);
            }
            }

        }else System.out.println("匹配失败");
        for(String num : myClass){
            Matcher m3 = p2.matcher(num);
            if(m3.find()){
                System.out.println(num + "班");
            }
        }
    }
}
