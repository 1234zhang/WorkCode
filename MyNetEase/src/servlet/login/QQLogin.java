package servlet.login;

import service.login.QQLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/qqlogin")
public class QQLogin extends HttpServlet {
    private static String test =null;
    private static QQLoginService qqLoginService = null;
    private static String appId = "101551268";
    @Override
    public void init() throws ServletException {
        qqLoginService = QQLoginService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String state = request.getParameter("service");
        response.sendRedirect("https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=" + appId + "&" +
                "redirect_uri=http%3a%2f%2fbrandonxcc.top%3a8080%2fMyNetEase%2flogin%2fgetToken&state"+state);
    }
}
