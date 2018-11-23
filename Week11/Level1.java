package Week11;

import java.sql.*;

public class Level1 {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT";
    private static final String user = "root";
    private static final String passward = "2017211678";

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet result = null;

    public Level1(){
        try {
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(JDBC_URL, user, passward);
            this.stmt = this.conn.prepareStatement("select id,name from think_data where id=?");
            this.stmt.setInt(1,3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void SelectSql(String sql){
        try {
            this.result = this.stmt.executeQuery();
            while(this.result.next()){
                System.out.println(this.result.getInt("id") + " " + this.result.getString("name"));
            }
            if(this.result != null){
                this.result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void operationSql(String sql) throws SQLException {
        if((this.stmt.executeUpdate(sql)) > 0){
            this.SelectSql("select id,name from think_data");
        }
    }
    public void mysqlClose(){
        if(this.stmt != null){
            try {
                this.stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(this.conn != null){
                try {
                    this.conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
