package MessageBoard.dao;

import MessageBoard.been.UserInfo;

import java.sql.*;

public class UserInfoDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String NAME = "root";
    private static final String PASSWORD = "2017211678@Cqupt";

    private static UserInfoDao instance = null;

    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UserInfoDao getInstance(){
        if(instance == null){
            synchronized (UserInfoDao.class){
                if(instance == null){
                    instance = new UserInfoDao();
                }
            }
        }
        return instance;
    }

    public Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void insert(UserInfo user){
        Connection conn = getConn();
        java.util.Date data = new java.util.Date();
        Date sqlDate = new Date(data.getTime());
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into userinfo (user,pwd,signtime) values(?,?,?)");
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setDate(3,sqlDate);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                assert stmt != null;
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public UserInfo select(String username){
        Connection conn = getConn();
        PreparedStatement stmt = null;
        ResultSet result = null;
        UserInfo user = null;
        try {
            stmt = conn.prepareStatement("select*from userinfo where(user=?)");
            stmt.setString(1,username);
            result = stmt.executeQuery();
            while(result.next()){
                user = new UserInfo(result.getString("user"),result.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                assert stmt != null;
                stmt.close();
                assert result != null;
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
