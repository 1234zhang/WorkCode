package service.search;

import bee.AlbumInfo;
import bee.SingInfo;
import bee.SongInfo;
import dao.SearchDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private static SearchDao searchDao = null;
    private static SearchService instance = null;
    private List<SingInfo> singList = new ArrayList<>();
    private List<AlbumInfo> albumList = new ArrayList<>();
    private List<SongInfo> songList = new ArrayList<>();

    public SearchService(){
        searchDao = SearchDao.getInstance();
    }
    public static SearchService getInstance(){
        if(instance == null){
            synchronized (SearchService.class){
                if(instance == null){
                    instance = new SearchService();
                }
            }
        }
        return instance;
    }
    public JSONArray getSingJson(String singName){
        singList = searchDao.getSing(singName);
        JSONArray data = new JSONArray();
        for (SingInfo a: singList) {
            JSONObject content = new JSONObject();
            content.put("id", a.getSingId());
            content.put("name",a.getSingName());
            content.put("EnName", a.getSingEn());
            content.put("imgUrl", "http://brandonxcc.top:8080/picture/"+a.getSingPicId()+".jpg");
            data.put(content);
        }
        return data;
    }
    public JSONArray getAlbumJson(String albumName){
        albumList = searchDao.getAlbum(albumName);
        JSONArray data = new JSONArray();
        for (AlbumInfo a: albumList) {
            JSONObject content = new JSONObject();
            content.put("id", a.getAlbumId());
            content.put("name",a.getAlbumName());
            content.put("imgUrl", "http://brandonxcc.top:8080/picture/" + a.getAlbumPicId() + ".jpg");
            content.put("singInfo", getSingJson(a.getSingName()));
            content.put("publishTime", a.getPublishTime());
            content.put("publishCompany",a.getPublishCompany());
            data.put(content);
        }
        return data;
    }
    public String getSongInfo(String songName){
        songList = searchDao.getSong(songName);
        JSONObject songJson = new JSONObject();
        JSONArray data = new JSONArray();
        for (SongInfo a: songList) {
            JSONObject content = new JSONObject();
            content.put("id",a.getSongId());
            content.put("name", a.getSongName());
            content.put("publishTime", a.getPublishTime());
            content.put("singInfo",getSingJson(a.getSingname()));
            content.put("albumInfo",getAlbumJson(a.getAlbumname()));
            data.put(content);
        }
        songJson.put("result",data);
        songJson.put("statue",200);
        return songJson.toString();
    }
}
