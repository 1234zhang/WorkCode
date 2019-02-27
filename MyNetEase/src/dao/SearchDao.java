package dao;

import bee.AlbumInfo;
import bee.SingInfo;
import bee.SongInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/mynetease?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    public static final String NAME = "root";
    public static final String PASSWORD = "2017211678@Cqupt";

    private static SearchDao instance = null;

    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static SearchDao getInstance(){
        if(instance == null){
            synchronized (SearchDao.class){
                if(instance == null){
                    instance = new SearchDao();
                }
            }
        }
        return instance;
    }
    private Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public ResultSet getInfo(String key) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConn().prepareStatement("select*from song_table where(sing_name = ? or song_name = ? or album_name = ?)");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,key);
            preparedStatement.setString(3,key);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }
    public List<SingInfo> getSing(String name) {
        ResultSet result = getInfo(name);
        List<SingInfo> list = new ArrayList<>();
        try {
            while (result.next()) {
                SingInfo singInfo = new SingInfo(result.getInt("sing_id"), result.getString("sing_name"),
                        result.getString("sing_picid"), result.getString("sing_en"));
                list.add(singInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<SongInfo> getSong(String name){
        ResultSet resultSet = null;
        List<SongInfo> list = new ArrayList<>();
        try {
            resultSet = getInfo(name);
            while(resultSet.next()){
                list.add(new SongInfo(resultSet.getInt("song_id"),resultSet.getString("song_name")
                ,resultSet.getTimestamp("song_publishtime"), resultSet.getString("album_name")
                        ,resultSet.getString("sing_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<AlbumInfo> getAlbum(String name){
        List<AlbumInfo> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = getInfo(name);
            while(resultSet.next()){
                list.add(new AlbumInfo(
                        resultSet.getString("album_name"), resultSet.getInt("album_id"), resultSet.getString("sing_name"),
                        resultSet.getString("album_picid"),resultSet.getTimestamp("album_publishtime"), resultSet.getString("album_company")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public SongInfo getSong(int id){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        SongInfo songInfo = null;
        try {
            preparedStatement = getConn().prepareStatement("select *from song_table where(song_id = ?)");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               songInfo =  new SongInfo(resultSet.getInt("song_id"),resultSet.getString("song_name")
                        ,resultSet.getTimestamp("song_publishtime"), resultSet.getString("album_name")
                        ,resultSet.getString("sing_name"));
            }
            getConn().close();
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songInfo;
    }
}
