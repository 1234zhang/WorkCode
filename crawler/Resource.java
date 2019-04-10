package crawler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/3/31.
 * @time 17:07.
 */

public class Resource implements Cloneable {
    public String day;
    public String classType;
    public String courseId;
    public String lesson;
    public String course;
    public String teacher;
    public String classRoom;
    public String rawWeek;
    public String type;
    public Map<String,String> teacherInfo = new HashMap<>();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Resource resource = null;
        resource = (Resource) super.clone();
        return resource;
    }

    @Override
    public String toString() {
        return day + " " + classRoom +" " + courseId + lesson + course + teacher;
    }
}
