package MessageBoard.servlet;

import MessageBoard.been.MessageInfo;
import MessageBoard.service.MessageBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    private static MessageBoardService messageBoardService;

    @Override
    public void init() throws ServletException {
        messageBoardService = MessageBoardService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        List<MessageInfo> list = messageBoardService.getAllMessage();
        String res = messageBoardService.messageToJson(list);

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
