package crawler;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/4/2.
 * @time 14:35.
 */

public class StudentCrawl {
    private static KbCrawler kbCrawler;
    private static MysqlOperation mysqlOperation;
    public StudentCrawl(){
        kbCrawler = KbCrawler.getInstance();
        mysqlOperation = new MysqlOperation();
    }
    private String getJson(String stuNum) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey=" + stuNum);
        HttpResponse httpResponse = client.execute(get);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "utf-8");
    }
    public void getStudent(String stuNum) throws Exception {
        String json = getJson(stuNum);
        Student student = new Gson().fromJson(json,Student.class);
        if(student.returnData.length != 0){
        for (Student.A a : student.returnData){
            if(a != null) {
                mysqlOperation.insertStudent(a);
                for (Resource resource : kbCrawler.getResourceList(a.xh)) {
                  mysqlOperation.insertResource(resource);
                    for (Map.Entry<String, String> info : resource.teacherInfo.entrySet()) {
                       mysqlOperation.insertMap(a.xh, info.getKey(), info.getValue());
                    }
                }
                System.out.println(a.xh);
            }
            }
        }
    }
}
