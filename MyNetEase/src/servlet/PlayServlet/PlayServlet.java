package servlet.PlayServlet;

import service.play.PlayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/play")
public class PlayServlet extends HttpServlet {
    private final PlayService playService;
    public PlayServlet(){
        playService = PlayService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //int id = Integer.parseInt(req.getParameter("song_id"));
        String songJson = playService.getSong();
        response.addHeader("Access-Control-AllowHeaders","Content-Type");
        response.addHeader("Access-Control-Allow-Method","*");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        writer.write(songJson);
        writer.flush();
        writer.close();
    }
}
