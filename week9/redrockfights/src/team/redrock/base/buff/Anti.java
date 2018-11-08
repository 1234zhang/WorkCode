package team.redrock.base.buff;

import team.redrock.base.Hero;

/*
* Description: 反弹对方所造成的伤害
* @Author: Brandon
* */
public interface Anti extends Buff{
    default String getDescription(){
        return "反甲持续" + getTime() + "回合";
    }
    @Override
    default boolean isDebuff(){
        return true;
    }
    @Override
    default void action(Hero hero){
        hero.setCanDamage(true);
        hero.attach(hero);
    }

}
