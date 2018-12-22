package MessageBoard.been;

public class UserInfo {
    private String username;
    private String password;
    public UserInfo(String username, String password){
        this.password = password;
        this.username = username;
    }
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
}
