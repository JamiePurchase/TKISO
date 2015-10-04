package game.world;

import game.war.WarData;
import java.awt.image.BufferedImage;

public class Terrain
{
    private WarData terrainWar;
    private Position terrainPos;
    private BufferedImage terrainTexture;
    private boolean terrainSolid;
    
    public Terrain(WarData war, Position pos, BufferedImage image, boolean solid)
    {
        this.terrainWar = war;
        this.terrainPos = pos;
        this.terrainTexture = image;
        this.terrainSolid = solid;
    }
    
    public void edit(BufferedImage image, boolean solid)
    {
        this.terrainTexture = image;
        this.terrainSolid = solid;
    }
    
    public Position getPos()
    {
        return this.terrainPos;
    }
    
    public BufferedImage getTexture()
    {
        return this.terrainTexture;
    }
    
    public boolean isEmpty()
    {
        if(this.terrainWar.getPositionArmy(this.terrainPos) != null) {return false;}
        return true;
    }
    
    public boolean isSolid()
    {
        return this.terrainSolid;
    }
    
    public void setSolid(boolean solid)
    {
        this.terrainSolid = solid;
    }
    
    public void setTexture(BufferedImage image)
    {
        this.terrainTexture = image;
    }

}