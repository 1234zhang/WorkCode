package Week11;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Level3 {
    public static String urlConnect(URL url){
        InputStream inputStream = null;
        String test = null;
        int i = 0;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            inputStream = conn.getInputStream();
            test = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return test;
    }
    public static String[][] getSchedule(String test){
        String[] strings = null;
        String pattern = "<table >[\\s\\S]+?</table>其他时间待定课程安排";
        String pattern1 = "<td >[\\s\\S]+</td>";
        String pattern2 = "<br>[\\s\\S]+?<br>([\\d | \\u4e00-\\u9fa5|\\S]+)";
        Pattern p = Pattern.compile(pattern);
        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m = p.matcher(test);
        if(m.find()) {
            String str = m.group();
            Matcher m1 = p1.matcher(m.group());
            if(m1.find()){
                strings = str.split("<div class='kbTd' zc='[\\d]+'>");
                String[][] schedule = new String[strings.length][];
                for (int i = 1; i < strings.length; i++) {
                    Matcher m2 = p2.matcher(strings[i]);
                    if(m2.find()){
                        schedule[i] = m2.group(0).split("<br>");
                    }
                }
                return schedule;
            }
        }else System.out.println("匹配失败");
        return null;
    }
    public static void scheduleDB(String[][] schedule){
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT","root","2017211678");
            preStmt = conn.prepareStatement("insert into schedule value(?,?)");
            for (int i = 1; i < schedule.length; i++) {
                preStmt.setString(1,schedule[i][1]);
                preStmt.setString(2,schedule[i][2]);
                preStmt.execute();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preStmt != null) {
                preStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        URL url = null;
        String[][] schedule = new String[100][];
        try {
            url = new URL("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh=2017211678");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String test = urlConnect(url);
        schedule = getSchedule(test);
        if(schedule != null)
        scheduleDB(schedule);

    }
}
