package servlet.songList;

import dao.SongListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/songList/DeleteSong")
public class DeleteSongServlet extends HttpServlet {
    SongListDao songListDao = null;

    @Override
    public void init() throws ServletException {
        songListDao = SongListDao.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int songId = Integer.parseInt(request.getParameter("song_id"));
        int songListId = Integer.parseInt(request.getParameter("songlist_id"));
        response.addHeader("Access-Control-Allow-Headers","*");
        response.addHeader("Access-Control-Allow-Method","GET,POST,OPTION");
        response.addHeader("Access-Control-Allow-Origin","http://brandonxcc.top:8080");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        songListDao.deleteSong(songListId,songId);
    }
}
