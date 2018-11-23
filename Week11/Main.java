package Week11;

import java.sql.SQLException;

public class Main {
    public static String select(){return "select id,name from think_data";}
    public static String insert(){return "insert into think_data values('4','xiaobai','0')";}
    public static String delete(){return "delete from think_data where 'id' = 1";}
    public static void main(String[] args) throws SQLException {
        Level1 mc = new Level1();
        mc.SelectSql(select());
        //mc.operationSql(insert());
        //mc.operationSql(delete());
        mc.mysqlClose();
    }
}
