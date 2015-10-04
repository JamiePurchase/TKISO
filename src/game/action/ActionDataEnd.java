package game.action;

import app.Engine;
import debug.Console;
import game.war.WarData;
import states.StateWar;

public class ActionDataEnd extends ActionData
{
    private final int actionAccount;
    
    public ActionDataEnd(int id, WarData war, int accountSource, int accountNew)
    {
        super(id, war, accountSource, ActionType.MOVE);
        this.actionAccount = accountNew;
        
        // DEBUG
        Console.print("ActionDataEnd created (" + id + ", war, " + accountSource + ", " + accountNew + ")");
    }
    
    public void activate()
    {
        StateWar state = (StateWar) Engine.getState();
        
        // Move the army to the destination
        this.getWar().setTurnPlayer(state.getPlayerData().getID());
        //this.getWar().setTurnNext();
        // NOTE: the setTurnNext method should also work?
        
        // Set the info text to support this
        state.setInfoText("It's your turn!");
        // NOTE: this.getWar().getTurnActive() returns the ID of the player
        // the username needs to be accessible for the info text (so we can say 'Jamie ended his turn' ?)
    }
    
}