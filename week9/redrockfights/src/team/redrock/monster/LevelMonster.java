package team.redrock.monster;

import team.redrock.base.Hero;

public class LevelMonster extends Hero {
    //升级小怪
    private final static String NAME = "哥布林";
    private final static String ID = "13456";
    private final static int STR = 50;
    private final static int INT = 50;
    private final static int AGI = 40;
    public LevelMonster() {
        super(NAME, ID, STR, INT, AGI);
    }
}
