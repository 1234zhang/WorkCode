package dao;

import bee.SongInfo;

import java.sql.*;
public class SongListDao {
    private static SongListDao instance = null;
    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mynetease?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String password = "2017211678@Cqupt";
    private static final String username = "root";

    static{
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static SongListDao getInstance(){
        if(instance == null){
            synchronized (SongListDao.class){
                if(instance == null){
                    instance = new SongListDao();
                }
            }
        }
        return instance;
    }
    public Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    //加入一个歌单
    public int insert(String songListName,String userId){
        Connection conn = getConn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement("insert into songlist_table (user_id,songlist_name, songlist_type)values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,songListName);
            preparedStatement.setInt(3,1);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void create(int id){
        String s = String.valueOf(id);
        Statement stmt = null;
        String sql = "create table if not exists " + s +"th(" +
                "id int unsigned auto_increment," +
                "song_id int unsigned," +
                "song_name varchar(10) not null," +
                "sing_name varchar(11) not null," +
                "primary key(id))engine=InnoDB default charset=utf8";
        try {
            getConn().createStatement().executeUpdate(sql);
            getConn().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert(SongInfo songInfo,int id){
        PreparedStatement preparedStatement = null;
        String s = String.valueOf(id);
        try {
            preparedStatement = getConn().prepareStatement("insert into"+ s +"th (song_id,song_name,sing_name) values (?,?,?)");
            preparedStatement.setInt(1,songInfo.getSongId());
            preparedStatement.setString(2,songInfo.getSongName());
            preparedStatement.setString(3,songInfo.getSingname());
            preparedStatement.execute();
            preparedStatement.close();
            getConn().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteList(int listId){
        PreparedStatement preparedStatement = null;
        String sql = "drop table "+String.valueOf(listId)+"th";
        try {
            preparedStatement = getConn().prepareStatement("delete from songlist_table where songlist_id = " + String.valueOf(listId));
            preparedStatement.execute();
            preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.executeUpdate();
            getConn().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSong(int listId, int songId){
        PreparedStatement preparedStatement = null;
        String sql = "delete from "+ String.valueOf(listId) + "th where songId = ?";
        try {
            preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.setInt(1,songId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            getConn().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet select(String userid){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConn().prepareStatement("select*from songlist_table where(user_id = ?)");
            preparedStatement.setString(1,userid);
            resultSet = preparedStatement.executeQuery();
            getConn().close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet selectSong(int songListId){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = getConn().prepareStatement("select*from " + songListId +"th");
            resultSet = preparedStatement.executeQuery();
            getConn().close();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
