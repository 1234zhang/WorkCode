package bee;

import java.sql.Timestamp;

public class SongInfo {
    private int songId;
    private String songName;
    private Timestamp publishTime;
    private String albumname;
    private String singname;
    public SongInfo(int songId, String songName, Timestamp publishTime, String albumname, String singname){
        this.albumname = albumname;
        this.singname = singname;
        this.publishTime = publishTime;
        this.songId = songId;
        this.songName = songName;
    }
    public String getSongName(){return this.songName;}
    public Timestamp getPublishTime(){return this.publishTime;}
    public int getSongId(){return this.songId; }
    public String getAlbumname(){return this.albumname;}
    public String getSingname(){return this.singname;}
    public String toString(){return this.singname + this.albumname;}
}
