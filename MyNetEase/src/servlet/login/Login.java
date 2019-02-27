package servlet.login;

import bee.UserInfo;
import service.login.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/login/Login")
public class Login extends HttpServlet {
    private UserInfoService userInfoService = null;
    private String ERROR = "{\"state\":\"10000\"}";
    private String OK = "{\"state\":\"10001\"}";
    @Override
    public void init() throws ServletException {
        userInfoService = UserInfoService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        UserInfo userInfo = new UserInfo(request.getParameter("password")," " ,request.getParameter("id"));
        String state = ERROR;
        HttpSession session = null;
        response.addHeader("Access-Control-AllowHeaders","Content-Type");
        response.addHeader("Access-Control-Allow-Method","*");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        if(userInfoService.checkLogin(userInfo)) {
            state = OK;
            session = request.getSession();
            session.setAttribute("id", userInfo.getId());
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        writer.write(state);
        writer.flush();
        writer.close();
    }
}
