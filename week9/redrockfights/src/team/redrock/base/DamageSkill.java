package team.redrock.base;

/**
 * @Description 伤害类技能
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class DamageSkill extends Skill {

    private double damage;

    public DamageSkill(String name, int damage) {
        super(name, damage / 2);
        this.damage=damage;
    }
    public void levelUp(){
        this.damage =  this.damage * 1.2;
    }
    @Override
    public int cast(Hero hero) {
        hero.reduceHp(damage);
        return 0;
    }

}
