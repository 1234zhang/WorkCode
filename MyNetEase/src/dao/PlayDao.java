package dao;

import bee.SingInfo;
import bee.SongInfo;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mynetease?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String NAME = "root";
    private static final String PASSWORD = "2017211678@Cqupt";

    private static PlayDao instance = null;
    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static PlayDao getInstance(){
        if(instance == null){
            synchronized (PlayDao.class){
                if(instance == null){
                    instance = new PlayDao();
                }
            }
        }
        return instance;
    }
    public Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public List<SongInfo> getSong(){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SongInfo> list = new ArrayList<>();
        try {
            statement = getConn().prepareStatement("select*from song_table");
            //statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                SongInfo songInfo =  new SongInfo(resultSet.getInt("song_id"),resultSet.getString("song_name")
                        ,resultSet.getTimestamp("song_publishtime"), resultSet.getString("album_name")
                        ,resultSet.getString("sing_name"));
                list.add(songInfo);
            }
            resultSet.close();
            getConn().close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
