package team.redrock.base;

/**
 * @Description 治疗类技能
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class CureSkill extends Skill {
    private double cure;

    protected CureSkill(String name, int cure){
        super(name,cure/2);
        this.cure=  cure;
    }
    public void leveUp(){
        this.cure = this.cure * 1.1;
    }

    @Override
    public final int cast(Hero hero) {
        return (int)cure;
    }
}
