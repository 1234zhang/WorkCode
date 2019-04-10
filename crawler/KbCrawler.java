package crawler;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Brandon.
 * @date 2019/3/31.
 * @time 15:33.
 */

public class KbCrawler {
    public static KbCrawler instance;
    MysqlOperation mysqlOperation = new MysqlOperation();
    private static final String GET_TABLE = "(?<=[\\s|\\S]</thead><tbody>)<tr >[\\s|\\S]+<td rowspan='1'></td>";
    private static final String SIMPLE_INFO = "[\\s|\\S]+(?=<td[\\s|\\S]+>名单[\\s|\\S]+)";
    private static final String DIVIDE_INFO = "(星期\\d)[\\W]{0,1}(第[\\d|\\W]+节)[\\W]{0,1}([\\w|\\d|\\W|\\u4e00-\\u9fa5]+)";

    public static KbCrawler getInstance(){
        if(instance == null){
            synchronized (KbCrawler.class){
                if(instance == null){
                    instance = new KbCrawler();
                }
            }
        }
        return instance;
    }

    public String getHtml(String kbUrl) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(kbUrl);
        HttpResponse httpResponse = client.execute(get);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "utf-8");
    }

    public String[] stuClass(String url) throws IOException {
        String html = getHtml(url);
        String[] classs = new String[30];
        Pattern p = Pattern.compile(GET_TABLE);
        Matcher m = p.matcher(html);
        if (m.find()) {
            String str = m.group();
            classs = str.split("</tr>");
        }
        return classs;
    }

    public Resource getResource(String scheduleInfo){
        String[] infos = scheduleInfo.split("</td>");
        Resource resource = new Resource();
        for (int i = 0; i < infos.length; i++) {
            switch(i){
                case 0:
                    resource.classType = infos[i].substring(infos[i].indexOf(">") + 1,infos[i].length());
                    break;
                case 1:
                    resource.courseId = infos[i].substring(infos[i].indexOf(">") + 1,infos[i].indexOf("-"));
                    resource.course = infos[i].substring(infos[i].indexOf("-") + 1,infos[i].length());
                    break;
                case 3:
                    resource.type = infos[i].substring(infos[i].indexOf(">") + 1,infos[i].length());
                    break;
                case 5:
                    resource.teacher = infos[i].substring(infos[i].indexOf(">") + 1,infos[i].length());
                    break;
                case 6:
                     Pattern pattern = Pattern.compile(DIVIDE_INFO);
                     Matcher matcher = pattern.matcher(infos[i]);
                     int key = 0;
                     if(matcher.find()){
                         resource.day = matcher.group(1);
                         resource.lesson = matcher.group(2);
                         resource.rawWeek = matcher.group(3);
                     }
                     break;
                case 7:
                    resource.classRoom = infos[i].substring(infos[i].indexOf(">") + 1,infos[i].length());
                    break;
                    default:
            }
        }
        return resource;
    }
    public Resource getResource(Resource resource,String info){
        String[] divideInfo = info.split("</td>");
        resource.teacher = divideInfo[0].substring(9,divideInfo[0].length());
        Pattern pattern = Pattern.compile(DIVIDE_INFO);
        Matcher matcher = pattern.matcher(divideInfo[1]);
        if(matcher.find()){
            resource.day = matcher.group(1);
            resource.lesson = matcher.group(2);
            resource.rawWeek = matcher.group(3);
        }
        resource.classRoom = divideInfo[2].substring(divideInfo[2].indexOf(">") + 1,divideInfo[2].length());
        return resource;
    }
    public List<Resource> getResourceList(String stuNum) throws IOException, CloneNotSupportedException, SQLException {
        String[] schedule = stuClass("http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=" + stuNum +"#kbStuTabs-list");
        Pattern pattern1 = Pattern.compile(SIMPLE_INFO);
        List<Resource> resources = new ArrayList<>();
        for (String str : schedule) {
            if (str != null) {
                str = str.replaceAll("\t", "");
                if (str.length() > 200) {
                    Matcher matcher = pattern1.matcher(str);
                    if (matcher.find()) {
                        Resource resource = getResource(str);
                        if (!"选修".equals(resource.type)) {
                            resource.teacherInfo.put(resource.teacher,resource.courseId);
                            resources.add(resource);
                            }else{
                            mysqlOperation.insertResource(resource);
                        }
                    }
                } else {
                    if (resources.size() != 0) {
                        Resource resource = (Resource) resources.get(resources.size() - 1).clone();
                        resources.add(getResource(resource,str));
                    }
                }
            }
        }
        return resources;
    }
}
