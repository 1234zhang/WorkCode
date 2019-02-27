package servlet.songList;

import service.songList.SongListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/songList/CreateList")
public class CreateLIstServlet  extends HttpServlet {
    private static SongListService songListService = null;
    @Override
    public void init() throws ServletException {
        songListService = SongListService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String listName = request.getParameter("name");
        HttpSession session = request.getSession(false);
        response.addHeader("Access-Control-Allow-Headers","*");
        response.addHeader("Access-Control-Allow-Method","GET,POST,OPTION");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        songListService.createSongList(listName,(String)session.getAttribute("id"));
    }
}
