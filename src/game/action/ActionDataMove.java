package game.action;

import debug.Console;
import game.army.ArmyData;
import game.war.WarData;
import game.world.Position;

public class ActionDataMove extends ActionData
{
    private final ArmyData actionArmy;
    private final Position actionDestination;
    
    public ActionDataMove(int id, WarData war, int account, int army, Position destination)
    {
        super(id, war, account, ActionType.MOVE);
        this.actionArmy = war.getArmyID(army);
        this.actionDestination = destination;
        
        // DEBUG
        Console.print("ActionDataMove created (" + id + ", war, " + account + ", " + army + ", " + destination.getGridString() + ")");
    }
    
    public void activate()
    {
        // Move the army to the destination
        // Set the info text to support this
    }
    
}