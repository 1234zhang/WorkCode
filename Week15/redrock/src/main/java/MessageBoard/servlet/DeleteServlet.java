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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private MessageBoardService messageBoardService;
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";
    private String WRONG = "{\"status\":\"10002\"}";

    @Override
    public void init() throws ServletException {
        messageBoardService = MessageBoardService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        MessageInfo messageInfo = messageBoardService.selectMessage(id);
        String test = (String) session.getAttribute("username");
        String res = ERROR;
        if(session != null){
            if(test.equals(messageInfo.getUserName())) {
                messageBoardService.deleteMessage(id);
                res = OK;
            }else res = WRONG;
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
