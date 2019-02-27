package bee;

public class UserInfo {
    //用户名
    private String username;
    //用户id
    private String id;
    //用户密码
    private String password;
    /*//用户等级
    private int level;*/

    public UserInfo(String password, String Username, String id){
        this.password = password;
        this.id = id;
        this.username = Username;
    }
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public String getUsername(){return this.username;}
    public String getId(){return this.id;}
    public String getPassword(){return this.password;}
}
