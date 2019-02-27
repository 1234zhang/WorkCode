package servlet.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/login/LoginOut")
public class LoginOut extends HttpServlet {
    private String OK = "{\"statue\":\"10001\"}";
    private String ERROR = "{\"statues\":\"10000\"}";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String res = ERROR;
        response.addHeader("Access-Control-AllowHeaders","Content-Type");
        response.addHeader("Access-Control-Allow-Method","*");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            res = OK;
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
