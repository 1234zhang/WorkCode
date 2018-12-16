package WebWork;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Signup extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String newpwd = request.getParameter("newworld");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        response.setContentType("text/html;charset=gb2312");
        if(name.isEmpty()){
            response.getWriter().write("用户名不能为空");
        }
        Connection conn = new SqlConn().getConn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement("select *from userinfo where(user=?)");
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) response.getWriter().write("用户名已经被注册");
             else{
                 preparedStatement = conn.prepareStatement("insert into userinfo values (?, ?,?)");
                 preparedStatement.setString(1,name);
                 preparedStatement.setString(2,password);
                 preparedStatement.setDate(3,sqlDate);
                 preparedStatement.executeUpdate();
                 response.getWriter().write("注册成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }
}
