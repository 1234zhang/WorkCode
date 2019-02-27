package service.songList;

import bee.SongInfo;
import dao.SearchDao;
import dao.SongListDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class SongListService {
    private static SongListDao songListDao = null;
    private static SearchDao searchDao = null;
    private static SongListService instance = null;

    public SongListService(){
        songListDao = SongListDao.getInstance();
        searchDao = SearchDao.getInstance();
    }
    public static SongListService getInstance(){
        if(instance == null){
            synchronized (SongListService.class){
                if(instance == null){
                    instance = new SongListService();
                }
            }
        }
        return instance;
    }
    public void createSongList(String name, String userId){
        int songListId = songListDao.insert(name,userId);
        songListDao.create(songListId);
    }
    public void insertSongList(int songListid,int songId){
        SongInfo songInfo = searchDao.getSong(songId);
        songListDao.insert(songInfo,songListid);
    }
    public String selectSonglist(String userid){
        ResultSet resultSet = songListDao.select(userid);
        JSONObject listJson = new JSONObject();
        JSONArray listData = new JSONArray();
        try{
            while(resultSet.next()){
                JSONObject data = new JSONObject();
                data.put("songlist_id",resultSet.getInt("songlist_id"));
                data.put("songListName", resultSet.getString("songlist_name"));
                data.put("imgUrl","brandonxcc.top:8080/MyNetEase/picture/songlist.jpg");
                listData.put(data);
            }
            listJson.put("result",listData);
            listJson.put("statue",200);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listJson.toString();
    }
    public String selectSong(int songListId){
        ResultSet result = songListDao.selectSong(songListId);
        JSONObject songJson = new JSONObject();
        JSONArray listSong = new JSONArray();
        try{
            while(result.next()){
                JSONObject data = new JSONObject();
                data.put("song_id",result.getInt("song_id"));
                data.put("song_name",result.getString("song_name"));
                data.put("sing_name",result.getString("sing_name"));
                data.put("album_name",result.getString("album_name"));
                data.put("play_url", "http://brandonxcc.top:8080/music/" + result.getString("song_name") + ".mp3");
                listSong.put(data);
            }
            songJson.put("result",listSong);
            songJson.put("statue",200);
        }catch(Exception e){
            e.printStackTrace();
        }
        return songJson.toString();
    }
}
