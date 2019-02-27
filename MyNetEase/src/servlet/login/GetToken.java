package servlet.login;

import service.login.QQLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/getToken")
public class GetToken extends HttpServlet {
    private QQLoginService qqLoginService = null;
    private String getAccessToken = "https://graph.qq.com/oauth2.0/token";
    private String getOpenId = "https://graph.qq.com/oauth2.0/me";
    private String getUserInfo = "https://graph.qq.com/user/get_user_info";

    @Override
    public void init() throws ServletException {
        qqLoginService = QQLoginService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
            String code = request.getParameter("code");
            String access = qqLoginService.getAccessToken(getAccessToken,code);
            String[] access_token = access.split("&");
            String openidJson = qqLoginService.getOpenId(getOpenId,access_token[0]);
            String[] info = openidJson.split("\"");//openid 在第7个
            qqLoginService.getUserInfo(getUserInfo,info[7],access_token[0]);
            response.setStatus(307);
            response.setHeader("location","http://www.brandonxcc.top:8080/MyNetEase/login/getsession?id=" + info[7]);
    }
}
