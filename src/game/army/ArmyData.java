package game.army;

import game.action.ActionPosition;
import game.action.ActionType;
import game.army.effect.Effect;
import game.war.WarData;
import game.world.Position;
import game.world.Terrain;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ArmyData
{
    // Army
    private int armyID, armyRulerID;
    private WarData armyWar;
    private Position armyPosition;
    
    // Stats
    private int statHealthNow, statHealthMax;
    private int statEnergyNow, statEnergyMax;
    private ArrayList<Effect> statEffect;
    
    // Action
    private boolean actionIdle;
    
    // Anim
    private BufferedImage animSheet;
    private int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public ArmyData(int id, WarData war, int ruler, Position position, int healthNow, int healthMax, int energyNow, int energyMax)
    {
        // Army
        this.armyID = id;
        this.armyWar = war;
        this.armyRulerID = ruler;
        this.armyPosition = position;
        
        // NOTE: consider a custom class for position (easy string storage, useful functions)
        
        // Stats
        this.statHealthNow = healthNow;
        this.statHealthMax = healthMax;
        this.statEnergyNow = energyNow;
        this.statEnergyMax = energyMax;
        this.statEffect = new ArrayList();
        
        // Action
        this.actionIdle = true;
        //this.actionType = null;
        
        // Anim
        // NOTE: we just need a id of the template from the db and we can build the anims
        this.animSheet = Drawing.getImage("graphics/armies/samurai/idle.png");
        this.animTickNow = 0;
        this.animTickMax = 30;
        this.animFrameNow = 0;
        this.animFrameMax = 3;
    }
    
    public void action(ActionPosition action)
    {
        if(action.getType() == ActionType.MOVE) {this.setPosition(action);}
        // NOTE: inform the server of this action
        // should we update the animation straight away or wait for confirmation from the server?
        this.actionIdle = false;
    }
    
    public ArrayList<ActionPosition> getActionPosition()
    {
        ArrayList<ActionPosition> posArray = new ArrayList();
        ArrayList<Position> posAdjacent = this.armyPosition.getPosAdjacent();
        // NOTE: need to consider what actions are available
        for(int x = 0; x < posAdjacent.size(); x++)
        {
            Terrain posTerrain = this.armyWar.getTerrain(posAdjacent.get(x).getGridString());
            if(!posTerrain.isSolid())
            {
                ArmyData posArmy = this.armyWar.getPositionArmy(posAdjacent.get(x));
                if(posArmy != null)
                {
                    if(posArmy.getArmyRulerID() == this.getArmyRulerID())
                    {
                        //else {posArray.add(new ActionPosition(this.armyWar, this, ActionType.HEAL, posAdjacent.get(x)));}
                    }
                    else
                    {
                        posArray.add(new ActionPosition(this.armyWar, this, ActionType.ATTACK, posAdjacent.get(x)));
                    }
                }
                else {posArray.add(new ActionPosition(this.armyWar, this, ActionType.MOVE, posAdjacent.get(x)));}
            }
        }
        return posArray;
    }
    
    public int getArmyID()
    {
        return this.armyID;
    }
    
    public int getArmyRulerID()
    {
        return this.armyRulerID;
    }
    
    public String getInfoName()
    {
        return "JAMIE";
    }
    
    public Position getPosition()
    {
        return this.armyPosition;
    }
    
    private Rectangle getRenderHealth()
    {
        return new Rectangle(this.armyPosition.getPosX() + 20, this.armyPosition.getPosY() - 10, 60, 10);
        //return new Rectangle(this.armyPosition.getPosX(), this.armyPosition.getPosY() - 10, 100, 10);
    }
    
    private BufferedImage getRenderImage()
    {
        return this.animSheet.getSubimage(this.animFrameNow * 100, 0, 100, 100);
    }
    
    public ArrayList<Effect> getStatEffect()
    {
        return this.statEffect;
    }
    
    public int getStatEnergyMax()
    {
        return this.statEnergyMax;
    }
    
    public int getStatEnergyNow()
    {
        return this.statEnergyNow;
    }
    
    public int getStatHealthMax()
    {
        return this.statHealthMax;
    }
    
    public int getStatHealthNow()
    {
        return this.statHealthNow;
    }
    
    public boolean isIdle()
    {
        return this.actionIdle;
    }
    
    public void render(Graphics g)
    {
        // Selection
        if(this.armyWar.getSelectArmy() == this)
        {
            Drawing.fadePolygon(g, this.armyPosition.getInputArea(), Color.WHITE, 0.8f);
            Drawing.drawPolygon(g, this.armyPosition.getInputArea(), Color.BLACK);
        }
        
        // Army
        Drawing.drawImage(g, this.getRenderImage(), this.armyPosition.getPosX(), this.armyPosition.getPosY());
    }
    
    public void renderHealth(Graphics g)
    {
        Drawing.fillRect(g, this.getRenderHealth(), Color.RED);
        Drawing.drawRect(g, this.getRenderHealth(), Color.BLACK);
        
        // NOTE: status effect animations? defend icon?
    }
    
    private void setPosition(Position pos)
    {
        this.armyPosition = pos;
    }
    
    public void tick()
    {
        this.tickAnim();
    }
    
    private void tickAnim()
    {
        this.animTickNow += 1;
        if(this.animTickNow > this.animTickMax)
        {
            this.animTickNow = 0;
            this.animFrameNow += 1;
            if(this.animFrameNow > this.animFrameMax)
            {
                this.animFrameNow = 0;
            }
        }
    }
    
}