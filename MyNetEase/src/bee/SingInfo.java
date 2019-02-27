package bee;

public class SingInfo {
    private int singId;
    private String singName;
    private String singPicId;
    private String singEn;
    public SingInfo(int id, String singName, String PicId, String singEn){
        this.singEn = singEn;
        this.singId = id;
        this.singName = singName;
        this.singPicId = PicId;
    }
    public String getSingName(){return this.singName;}
    public String getSingPicId(){return this.singPicId;}
    public int getSingId(){return this.singId;}
    public String getSingEn(){return this.singEn;}
    public String toString(){return this.singName + this.singPicId;}
}
