package MessageBoard.servlet;

import MessageBoard.been.MessageInfo;
import MessageBoard.service.MessageBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
    private static final String ERROR = "{\"status\":\"10000\"}";
    private static final String OK = "{\"status\":\"10001\"}";
    private static final String WRONG = "{\"status\":\"10002\"}";

    private MessageBoardService messageBoardService;

    @Override
    public void init() throws ServletException {
        messageBoardService = MessageBoardService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        String text = request.getParameter("text");
        int pid = Integer.parseInt(request.getParameter("pid"));
        MessageInfo message = new MessageInfo();
        message.setPid(pid);
        message.setText(text);
        String res = ERROR;
        if(session == null) {
            res = WRONG;
        }else {
            String username = (String) session.getAttribute("username");
            message.setUserName(username);
            if (messageBoardService.insertMessage(message)) {
                res = OK;
            }
        }
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        out.write(res);
        out.flush();
        out.close();
    }
}
