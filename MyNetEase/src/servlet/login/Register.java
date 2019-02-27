package servlet.login;

import bee.UserInfo;
import service.login.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/login/Register")
public class Register extends HttpServlet {
    private static UserInfoService userInfoService = null;
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";

    @Override
    public void init() throws ServletException {
        userInfoService = UserInfoService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UserInfo userInfo = new UserInfo(request.getParameter("password"),request.getParameter("username"), request.getParameter("id"));
        String res = ERROR;
        response.addHeader("Access-Control-AllowHeaders","Content-Type");
        response.addHeader("Access-Control-Allow-Method","*");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        if(userInfoService.checkRegister(userInfo)) res = OK;
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
    }
}
