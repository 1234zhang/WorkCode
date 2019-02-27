package servlet.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/WeiboLogin")
public class WeiboLogin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("https://api.weibo.com/oauth2/authorize?client_id=2097788999&response_type=code&redirect_uri=http%3a%2f%2fbrandonxcc.top%3a8080%2fMyNetEase%2flogin%2fWbToken");
    }
}
