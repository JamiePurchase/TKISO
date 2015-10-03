package game.army.effect;

import game.army.ArmyData;

public class Effect
{
    private ArmyData effectArmy;
    private EffectType effectType;
    private int effectTime;
    
    public Effect(ArmyData army, EffectType type, int time)
    {
        this.effectArmy = army;
        this.effectType = type;
        this.effectTime = time;
    }
    
}