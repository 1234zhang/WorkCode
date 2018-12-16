package Thread;

import Week12.ConnSQL;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KbCrawler extends Thread{
    static ArrayList<String> grade = new ArrayList<String>();//要爬取的年级；
    public String url = null;
    public static int  stuNum = 0;
    public static final Object flag = new Object();//线程中的通信变量；
    public KbCrawler(String url){
        this.url = url;

    }
    public void run(){
        crawl(this.url);
    }

    public static void main(String[] args) {
        /*
        * 使用模糊匹配（201X21XX可一次匹配一百个人）
        * */
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 4; k++) {
                    grade.add("http://jwzx.cqu.pt/data/json_StudentSearch.php?searchKey=201" + i + "21" + j + k);
                }
            }
        }
        System.out.println("________________________Start___________________");
        long start = System.currentTimeMillis();
        ExecutorService execu = Executors.newFixedThreadPool(10);
        for (String url: grade) {
            execu.execute(new KbCrawler(url));
        }
        execu.shutdown();
        while(true) {
            if (execu.isTerminated()) {
                long end = System.currentTimeMillis();
                System.out.println("____________________end____________________");
                System.out.println("程序运行了：" + (end-start)/1000 + "秒");
                System.out.println("共有" + stuNum + "名学生");
                //upDate();//将数据库数据更新，加上没有对应学生的学号。
                System.exit(1);
            }
        }
    }
    /*
    * 开始抓取数据
    * */
    public void crawl(String url){
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
        try {
            HttpResponse httpResponse = client.execute(get);
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println("抓取:" + url);
            jsonHandle(EntityUtils.toString(httpEntity, "utf-8"));//处理爬取下来的数据，并存入数据库。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 处理从网站上抓取的json
    *
    * */
    public void jsonHandle(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray arr = jsonObject.getJSONArray("returnData");
        String js = arr.toJSONString();
        /*
        * 这里直接将json解析出Student对象，存入list容器。但是我不知道为啥，当我把学号从Xh改成stuId之后，学号就存不进去了。
        * 所以我没有使用作业中给出的Student对象。。。。。
        * */
        List<Student> students = JSONObject.parseArray(js, Student.class);
        //new ConnSQL().insert(students);//将学生信息存入数据库。
        stuNum += students.size();
        for (Student a: students) {
            System.out.println(a.getXh());
        }
        //System.out.println("入库成功");
    }
    /*
    * 增加数据库信息。加上错误学号。
    * */
    public static void upDate(){
        ConnSQL conn = new ConnSQL();
        for (String a: grade) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String[] buff = a.split("=");
                    String stuid = buff[1] + i+j;
                    System.out.println(stuid);
                    ResultSet resultSet = conn.ifReal(stuid);
                    try {
                        if (!resultSet.next()) {
                            conn.insert(stuid);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
