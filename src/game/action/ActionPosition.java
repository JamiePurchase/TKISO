package game.action;

import game.army.ArmyData;
import game.war.WarData;
import game.world.Position;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;

public class ActionPosition extends Position
{
    private ActionType actionType;
    
    public ActionPosition(WarData war, ArmyData army, ActionType type, Position pos)
    {
        super(pos.getGridString());
        this.actionType = type;
    }
    
    public ActionPosition(WarData war, ArmyData army, ActionType type, int posX, int posY)
    {
        super(posX, posY);
        this.actionType = type;
    }
    
    public ActionType getType()
    {
        return this.actionType;
    }
    
    public void render(Graphics g)
    {
        Color highlight = Color.WHITE;
        if(this.actionType == ActionType.ATTACK) {highlight = Color.RED;}
        if(this.actionType == ActionType.COLLECT) {highlight = Color.GREEN;}
        Drawing.fadePolygon(g, this.getInputArea(), highlight, 0.8f);
        Drawing.drawPolygon(g, this.getInputArea(), Color.BLACK);
    }

}