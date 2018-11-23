package Week11;

import Week11.MarketSystem.Commodity;

import java.util.Scanner;

public class Level2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("选择操作（1、查看 2、添加 3、修改 4、删除）：");
        Commodity commodity = new Commodity();
        int num = sc.nextInt();
        switch(num){
            case 1:
                commodity.select();
                break;
            case 2:
                commodity.insert();
                break;
            case 3:
                commodity.update();
                break;
            case 4:
                commodity.delete();
                break;
        }
    }
}
