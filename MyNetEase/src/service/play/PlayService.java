package service.play;

import bee.SingInfo;
import bee.SongInfo;
import dao.PlayDao;
import dao.SearchDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayService {
    private static PlayDao playDao = null;
    private static PlayService instance = null;
    List<SongInfo> list = new ArrayList<>();
    public PlayService(){
        playDao = PlayDao.getInstance();
    }
    public static PlayService getInstance(){
        if(instance == null){
            synchronized (PlayService.class){
                if(instance == null){
                    instance = new PlayService();
                }
            }
        }
        return instance;
    }
    public String getSong(){
        list = playDao.getSong();
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        for (SongInfo songInfo : list) {
            JSONObject content = new JSONObject();
            content.put("song_id",songInfo.getSongId());
            content.put("Song_name", songInfo.getSongName());
            content.put("play_add","http://brandonxcc.top:8080/music/" + songInfo.getSongName() + ".mp3");
            data.put(content);
        }
        result.put("statue", 200);
        result.put("result",data);
        return result.toString();
    }
}
