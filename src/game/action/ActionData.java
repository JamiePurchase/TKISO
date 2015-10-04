package game.action;

import game.war.WarData;

public abstract class ActionData
{
    private final int actionID;
    private final WarData actionWar;
    private final int actionAccount;
    private final ActionType actionType;
    
    public ActionData(int id, WarData war, int account, ActionType type)
    {
        this.actionID = id;
        this.actionWar = war;
        this.actionAccount = account;
        this.actionType = type;
    }
    
    public abstract void activate();
    
    public int getID()
    {
        return this.actionID;
    }
    
    public ActionType getType()
    {
        return this.actionType;
    }
    
    public WarData getWar()
    {
        return this.actionWar;
    }

}