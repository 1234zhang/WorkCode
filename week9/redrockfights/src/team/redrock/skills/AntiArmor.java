package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.buff.Anti;

public class AntiArmor extends DamageSkill implements Anti {
    private final static String NAME = "反甲";
    private final static int DAMAGE = 400;
    private final static int WEER_TIME = 10;
    public AntiArmor(){
        super(NAME, DAMAGE);
    }
    @Override
    public int getTime(){
        return WEER_TIME;
    }
}
