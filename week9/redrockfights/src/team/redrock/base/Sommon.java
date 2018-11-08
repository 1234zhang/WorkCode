package team.redrock.base;

import team.redrock.heros.Pikachu;
import team.redrock.monster.LevelMonster;

import java.io.IOException;
import java.util.Scanner;

public class Sommon {
    private static LevelMonster monster = new LevelMonster();
    private static Pikachu pikachu = new Pikachu();

    //设置刷怪舞台
    private Stage stage = new Stage();

    //设置初始级数
    private  static int level = 1;

    //设置开始经验
    private int experience = 0;

    //杀死一只怪物所获得的经验
    private final double EXP = 800;

    //开始刷怪
    public void begin(){
        Hero right = pikachu;
        Hero left = monster;
        while(experience < right.getExperience(level)){
            boolean winner = stage.battle(left,right);
            if(!winner) {
                experience += EXP * (level * 1.2);//如果赢了，增加经验
            }
        }
        pikachu.levelUp();
        monster.levelUp();
        level++;
    }

    public static void main(String[] args) throws IOException {
        String flag;
        Sommon sommon = new Sommon();
        Scanner sc = new Scanner(System.in);
        System.out.print("是否进入刷怪模式(y/n)：");
        flag = sc.nextLine();
        do {
            sommon.begin();
            System.out.print("是否继续刷级(y/n)：");
            flag = sc.nextLine();
            if(level == pikachu.getMaxLevel()) break;
        }while(flag.equals("y") || flag.equals("Y"));
        if(level == pikachu.getMaxLevel()) System.out.println("恭喜你! 英雄达到满级");
        else System.out.println("当前英雄等级为：" + level);
    }
}
