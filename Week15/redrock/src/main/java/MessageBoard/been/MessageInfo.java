package MessageBoard.been;

import java.util.List;

public class MessageInfo {
    private int id;//留言id；
    private int pid;//父节点的id
    private String userName;//用户内容;
    private String text;//留言内容；
    List<MessageInfo> childContent;//这条留言的子节点；
    public void setId(int id){this.id = id;}
    public void setUserName(String userName){this.userName = userName;}
    public void setText(String text){this.text = text;}
    public void setPid(int pid){this.pid = pid;}
    public void setChildContent(List<MessageInfo> list){this.childContent = list;}
    public String getUserName(){return this.userName;}
    public String getText(){return this.text; }
    public int getPid(){return this.pid;}
    public int getId(){return this.id;}
    public List<MessageInfo> getChildContent(){return childContent;}
}
