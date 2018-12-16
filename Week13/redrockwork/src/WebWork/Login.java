package WebWork;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Login extends HttpServlet {
    private Connection conn;
    private PreparedStatement prestmt;
    private ResultSet resultSet;
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
         conn = new SqlConn().getConn();
        String userName = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        response.setContentType("text/html;charset=gb2312");
        try {
            prestmt = conn.prepareStatement("select*from userinfo where (user = ?)");
            prestmt.setString(1,userName);
            resultSet = prestmt.executeQuery();
            if(!resultSet.next()){
                response.getWriter().write("该用户不存在");
            }else if(!pwd.equals(resultSet.getString("pwd"))){
                response.getWriter().write("密码错误");
            }else{
                response.getWriter().write("登录成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{}
}
