package Week11.MarketSystem;

import java.sql.*;
import java.util.Scanner;

class MarketSystem {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://localhost:3306/market?useSSL=false&serverTimezone=GMT";

    private static final String USER = "root";
    private static final String PASSWORD = "2017211678";
    public static Scanner sc = new Scanner(System.in);

    public static Connection conn = null;
    public static PreparedStatement preStmt = null;
    public MarketSystem(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
