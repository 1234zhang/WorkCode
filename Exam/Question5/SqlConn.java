package Exam.Question5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlConn {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "20172111678";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";

    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection Conn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL,USER_NAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void update(String stuId,String classes){
        Connection conn = Conn();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update wechat set class = ? where number = ?");
            stmt.setString(1,classes);
            stmt.setString(2,stuId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
