package bee;

import java.sql.Timestamp;

public class AlbumInfo {
    private String albumName;
    private int albumId;
    private String singName;
    private String albumPicId;
    private Timestamp publishTime;
    private String publishCompany;
    public AlbumInfo(String name, int albumId, String singName, String albumPicId, Timestamp publishTime, String publishCompany){
        this.albumName = name;
        this.albumId = albumId;
        this.singName = singName;
        this.albumPicId = albumPicId;
        this.publishTime = publishTime;
        this.publishCompany = publishCompany;
    }
    public String getAlbumName(){return this.albumName;}
    public int getAlbumId(){return this.albumId;}
    public String getSingName(){return this.singName;}
    public String getAlbumPicId(){return albumPicId;}
    public Timestamp getPublishTime(){return this.publishTime;}
    public String getPublishCompany(){return this.publishCompany;}
}
