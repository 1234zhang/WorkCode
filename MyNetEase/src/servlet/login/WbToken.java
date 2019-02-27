package servlet.login;

import service.login.WbloginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/WbToken")
public class WbToken extends HttpServlet {
    private static WbloginService wbloginService = null;
    @Override
    public void init() throws ServletException {
        wbloginService = WbloginService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            String code = request.getParameter("code");
            String result = wbloginService.getAccessToken(code);
            String[] accessToken = result.split("\"");//accessToken[13]即是openid
            wbloginService.getOpenid(accessToken[3],accessToken[13]);
            response.setStatus(307);
            response.setHeader("location","http://www.brandonxcc.top:8080/MyNetEase/login/getsession?id=" + accessToken[13]);
    }
}
