package MessageBoard.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/out")
public class LoginOut  extends HttpServlet {
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String res = ERROR;
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
