package game.army;

import game.war.WarData;
import java.awt.Graphics;

public class ArmyData
{
    // Army
    private int armyID, armyRulerID;
    private WarData armyWar;
    private String armyPosition;
    
    // Stats
    private int statHealthNow, statHealthMax;
    
    // Anim
    //private BufferedImage animSheet;
    private int animTickNow, animTickMax, animFrameNow, animFrameMax;
    
    public ArmyData(int id, WarData war, int ruler, String position, int healthNow, int healthMax)
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
        
        // Anim
        // NOTE: we just need a id of the template from the db and we can build the anims
        //this.animSheet
        //this.animTickNow
        //this.animTickMax
        //this.animFrameNow
        //this.animFrameMax
    }
    
    public void render(Graphics g)
    {
        // NOTE: take into consideration the board scroll offset, hexagonal placement
        // and render location within the window (alternate canvas should take care of this)
    }
    
    public void tick()
    {
        // NOTE: scroll through anim frames
    }
    
}