package dao;

import bee.UserInfo;

import java.sql.*;

public class UserInfoDao {
    public static final String JDBC_Drive = "com.mysql.cj.jdbc.Driver";
    public static final String  NAME = "root";
    public static final String  PASSWORD = "2017211678@Cqupt";
    public static final String  JDBC_URL = "jdbc:mysql://localhost:3306/mynetease?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";

    private static UserInfoDao instance = null;
    static{
        try {
            Class.forName(JDBC_Drive);
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
    //新增用户
    public void insert(UserInfo userinfo){
        UserInfo isEmpty = select(userinfo.getId());
        if(isEmpty == null) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = getConn().prepareStatement("insert into userinfo (id,username,password) value (?,?,?)");
                preparedStatement.setString(1, userinfo.getId());
                preparedStatement.setString(2, userinfo.getUsername());
                preparedStatement.setString(3, userinfo.getPassword());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //修改用户信息
    /*
    * 使用过滤器判断用户是否登录。登录才能修改信息
    * */
    public void update(UserInfo userinfo) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConn().prepareStatement("update userinfo set username = ? ,password = ? where id = ?");
            preparedStatement.setString(1, userinfo.getUsername());
            preparedStatement.setString(2, userinfo.getPassword());
            preparedStatement.setString(3, userinfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public UserInfo select(String id){
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        UserInfo userInfo = null;
        try {
            preparedStatement = getConn().prepareStatement("select*from userinfo where(id = ?)");
            preparedStatement.setString(1, id);
            result = preparedStatement.executeQuery();
            while(result.next()){
                userInfo = new UserInfo(result.getString("password"), result.getString("username"), result.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(result != null){
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }
}
