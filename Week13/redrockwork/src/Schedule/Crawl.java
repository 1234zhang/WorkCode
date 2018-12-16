package Schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Crawl {
    public String getSchedule(String stuId){
        InputStream inputStream = null;
        String test = null;
        URL url = null;
        try {
            url = new URL("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh=" + stuId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            inputStream = conn.getInputStream();
            test = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(test != null){
            String pattern = "<table >[\\s\\S]+?</table>其他时间待定课程安排";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(test);
            if(m.find()){
                return m.group();
            }
        }
        return null;
    }

   /* public static void main(String[] args) {
        String test = new Crawl().getSchedule("2017211678");
        System.out.println(test);
    }*/
}
