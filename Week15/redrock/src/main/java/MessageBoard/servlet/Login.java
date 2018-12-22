package MessageBoard.servlet;

import MessageBoard.been.UserInfo;
import MessageBoard.service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static UserInfoService userInfoService;
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";

    @Override
    public void init() throws ServletException {
        userInfoService = UserInfoService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        UserInfo user = new UserInfo(username, pwd);
        HttpSession session = null;
        String res = ERROR;
        if(userInfoService.loginCheck(user)) {
            res = OK;
            session = request.getSession();
            session.setAttribute("username", user.getUsername());
        }
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
