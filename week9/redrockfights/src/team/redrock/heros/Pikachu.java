package team.redrock.heros;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.skills.AntiArmor;

public class Pikachu extends Hero {
    private final static String NAME = "张万超";
    private final static String STUID = "2017211678";
    private final static int STR = 100;  //体力
    private final static int INT = 100;  //智力
    private final static int AGI = 100;  //移速

    public Pikachu(){
        super(NAME, STUID, STR, INT, AGI);

        Skill antiArmor = new AntiArmor();
        Skill electric = new DamageSkill("十万伏特",400);
        super.addSkill(electric);
        super.addSkill(antiArmor);
    }
}
