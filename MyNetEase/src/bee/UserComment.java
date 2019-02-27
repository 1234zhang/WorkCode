package bee;

import java.util.List;

public class UserComment {
    //评论id
    private int id;
    //评论父id
    private int pid;
    //评论内容
    private String text;
    //用户名
    private String username;
    //评论的子评论
    List<UserComment> childComment;
}
