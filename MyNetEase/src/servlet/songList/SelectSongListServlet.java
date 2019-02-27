package servlet.songList;

import service.songList.SongListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/songList/SongList")
public class SelectSongListServlet extends HttpServlet {
    private static SongListService songListService = null;

    @Override
    public void init() throws ServletException {
        songListService = SongListService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.addHeader("Access-Control-Allow-Headers","*");
        response.addHeader("Access-Control-Allow-Method","GET,POST,OPTION");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        HttpSession session = request.getSession(false);
        if(session != null){
            String songListJson = songListService.selectSonglist((String)session.getAttribute("id"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write(songListJson);
            writer.flush();
            writer.close();
        }
    }
}
