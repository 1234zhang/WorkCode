package crawler;

import java.sql.*;

/**
 * @author Brandon.
 * @date 2019/4/3.
 * @time 17:26.
 */

public class MysqlOperation {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "root";
    public static final String PASSWORD = "2017211678";
    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,PASSWORD);
        return connection;
    }
    public void insertResource(Resource resource) throws SQLException {
        PreparedStatement prepare = null;
        Connection conn = getConn();
        try{
            prepare = conn.prepareStatement("insert ignore into resource(course_id,teacher,course,class_room,raw_week,lesson,type,day) value (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1,resource.courseId);
            prepare.setString(2,resource.teacher);
            prepare.setString(3,resource.course);
            prepare.setString(4,resource.classRoom);
            prepare.setString(5,resource.rawWeek);
            prepare.setString(6,resource.lesson);
            prepare.setString(7,resource.type);
            prepare.setString(8,resource.day);
            prepare.execute();
        }finally{
            conn.close();
            assert prepare != null;
            prepare.close();
        }
    }
    public void insertStudent(Student.A a) throws SQLException {
        PreparedStatement prepare = null;
        Connection conn = getConn();
        try{
                prepare = conn.prepareStatement("insert ignore into student(xh,xm,bj,yxm,zym) value (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                prepare.setString(1, a.xh);
                prepare.setString(2, a.xm);
                prepare.setString(3, a.bj);
                prepare.setString(4, a.yxm);
                prepare.setString(5, a.zym);
                prepare.execute();
        }finally{
            conn.close();
            assert prepare != null;
            prepare.close();
        }
    }
    public void insertMap(String xh,String teacher,String courseId) throws SQLException {
        PreparedStatement prepare = null;
        Connection connection = getConn();
        try{
            prepare = connection.prepareStatement("insert ignore into resource_map (xh,teacher,course_id) value (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1,xh);
            prepare.setString(2,teacher);
            prepare.setString(3,courseId);
            prepare.execute();
        }finally{
            connection.close();
            assert prepare != null;
            prepare.close();
        }
    }
}
