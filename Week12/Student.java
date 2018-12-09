package Week12;

public class Student {
    private String xh;
    private String xm;
    private String zym;
    private String yxm;
    private  String xb;
    public void setXh(String xh){this.xh = xh;}
    public void setXb(String xb){this.xb = xb;}
    public void setXm(String xm){this.xm = xm;}
    public void setZym(String zym){this.zym = zym;}
    public void setYxm(String yxm){this.yxm = yxm;}
    public String getXh(){return this.xh;}
    public String getXb(){return this.xb;}
    public String getXm(){return this.xm;}
    public String getZym(){return this.zym;}
    public String getYxm(){return this.yxm;}
    public String toString(){return "姓名：" + this.xm +" 性别：" + this.xb + " 学号：" + this.xh + " 学院:" + this.yxm + " 专业名:" + this.zym; }
}
