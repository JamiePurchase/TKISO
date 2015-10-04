package game.action;

import app.Engine;
import debug.Console;
import game.army.ArmyData;
import game.war.WarData;
import game.world.Position;
import states.StateWar;

public class ActionDataMove extends ActionData
{
    private final ArmyData actionArmy;
    private final Position actionDestination;
    
    public ActionDataMove(int id, WarData war, int account, int army, Position destination)
    {
        super(id, war, account, ActionType.MOVE);
        this.actionArmy = war.getArmyID(army);
        this.actionDestination = destination;
    }
    
    public void activate()
    {
        // Move the army to the destination
        this.actionArmy.setPosition(this.actionDestination);
        
        // Set the info text to support this
        StateWar state = (StateWar) Engine.getState();
        state.setInfoText(this.actionArmy.getInfoName() + " moved...");
    }
    
}