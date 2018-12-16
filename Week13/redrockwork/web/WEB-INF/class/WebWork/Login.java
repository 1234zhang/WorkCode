package WebWork;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Login extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "2017211678";

    private Connection conn;
    private PreparedStatement prestmt;
    private ResultSet resultSet;

    public void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String userName = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        try {
            prestmt = conn.prepareStatement("select*from userinfo where (user = ?)");
            prestmt.setString(1,userName);
            resultSet = prestmt.executeQuery();
            if(resultSet == null){
                out.println("该用户不存在");
            }else if(!pwd.equals(resultSet.getString("pwd"))){
                out.println("密码错误");
            }else{
                out.println("登录成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletResponse response, HttpServletRequest request) throws  IOException,ServletException{}
}
