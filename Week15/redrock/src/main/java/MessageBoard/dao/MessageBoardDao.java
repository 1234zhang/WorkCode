package MessageBoard.dao;

import MessageBoard.been.MessageInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageBoardDao {
        private static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
        private static final String NAME = "root";
        private static final String PASSWORD = "2017211678@Cqupt";

        private static MessageBoardDao instance = null;

        //加载类时加载数据库连接
    static{
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //得到dao的单例；
    public static MessageBoardDao getInstance(){
        if(instance == null){
            synchronized (MessageBoardDao.class){
                if(instance == null){
                    instance = new MessageBoardDao();
                }
            }
        }
        return instance;
    }
    //得到数据连接
    private Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, NAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void insert(MessageInfo message){
        PreparedStatement stmt = null;
        ResultSet result = null;
        Connection conn = getConn();
        try {
            stmt = conn.prepareStatement("insert into message_board (username,text,pid) values(?,?,?)");
            stmt.setString(1,message.getUserName());
            stmt.setString(2,message.getText());
            stmt.setInt(3,message.getPid());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public MessageInfo select(int id){
        Connection conn = getConn();
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        MessageInfo message = new MessageInfo();
        try {
            stmt = conn.prepareStatement("select*from message_board where(id=?)");
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                message.setUserName(resultSet.getString("username"));
                message.setText(resultSet.getString("text"));
                message.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
    public void delete(int id){
        Connection conn = getConn();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from message_board where (id = ?)");
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据父节点查找评论
    public List<MessageInfo> findMessageInfoByPid(int pid){
        Connection conn = getConn();
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        try {
            stmt = conn.prepareStatement("select*from message_board where(pid=?)");
            stmt.setInt(1,pid);
            result = stmt.executeQuery();
            while(result.next()){
                MessageInfo message = new MessageInfo();
                message.setId(result.getInt("id"));
                message.setPid(result.getInt("pid"));
                message.setText(result.getString("text"));
                message.setUserName(result.getString("username"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            assert conn != null;
            assert stmt != null;
            assert result != null;
            close(conn, stmt, result);
        }
        return list;
    }
    private void close(Connection conn, PreparedStatement stmt, ResultSet result){
        try {
            conn.close();
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
