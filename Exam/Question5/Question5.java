package Exam.Question5;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Question5 {
    private static String doGet(String url){
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse httpResponse = client.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void getInfo(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray stringGet = jsonObject.getJSONArray("results");
        String js = stringGet.toJSONString();
        List<Student> students = JSONObject.parseArray(js, Student.class);
        for (Student a: students
        ) {
            System.out.println(a);
        }
        //boolean test = new ConnSQL().insert(students);
        /*if(test) System.out.println("入库成功");
        else System.out.println("入库失败");*/
    }
    public static void main(String[] args) {
        String test = null;
        Scanner sc = new Scanner(System.in);
        String stuid = sc.next();
        test = doGet("http://120.79.143.238/cqupt/query?search=" + stuid);
        System.out.println(test);
        getInfo(test);
    }
}
