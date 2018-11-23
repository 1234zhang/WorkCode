package Week11.MarketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Commodity extends MarketSystem {
    private void mysqlPrint(ResultSet result) throws SQLException {
        System.out.println("商品信息为：");
        if(result != null){
            while(result.next()) {
                System.out.println(result.getInt("commodity_id") + " " + result.getString("commodity_name")
                        + " " + result.getInt("commodity_cost") + " " + result.getInt("commodity_retail"));
            }
        }
    }
    public void insert(){
        System.out.println("输入添加商品信息（商品编号，商品名，商品仓库名，商品成本，商品售价：）");
        int id = sc.nextInt();
        String comName = sc.next();
        String stockName = sc.next();
        int cost = sc.nextInt();
        int retail = sc.nextInt();
        try {
            preStmt = conn.prepareStatement("insert into commodity value(?,?,?,?,?)");
            preStmt.setInt(1, id);
            preStmt.setString(2, comName);
            preStmt.setString(3, stockName);
            preStmt.setInt(4, cost);
            preStmt.setInt(5, retail);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void select(){
        System.out.println("输入商品名字:");
        String name = sc.nextLine();
        try {
            preStmt = conn.prepareStatement("select*from commodity where commodity_name like ? order by commodity_cost");
            preStmt.setString(1, "%" + name + "%");
            ResultSet result = preStmt.executeQuery();
            mysqlPrint(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        System.out.println("输入删除商品名：");
        String commName = sc.nextLine();
        try {
            preStmt = conn.prepareStatement("delete from market.commodity where commodity_name=?");
            preStmt.setString(1, commName);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(commName + "删除成功");
    }
    public void update(){
        System.out.println("输入修改商品名称：");
        String name = sc.next();
        System.out.println("输入新的商品信息");
        System.out.println("商品名：");
        String newName = sc.next();
        System.out.println("新仓库名：");
        String stock = sc.next();
        System.out.println("成本：");
        int cost = sc.nextInt();
        System.out.println("售价：");
        int retail = sc.nextInt();
        try {
            preStmt = conn.prepareStatement("update commodity set commodity_name = ?,commodity_stock_name = ?" +
                    ",commodity_cost = ?, commodity_retail = ? where commodity_name = ?");
            preStmt.setString(1,newName);
            preStmt.setString(2,stock);
            preStmt.setInt(3,cost);
            preStmt.setInt(4,retail);
            preStmt.setString(5,name);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
