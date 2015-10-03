package game.world;

import game.war.WarData;

public class Terrain
{
    private WarData terrainWar;
    private Position terrainPos;
    private boolean terrainSolid;
    
    public Terrain(WarData war, Position pos, boolean solid)
    {
        this.terrainWar = war;
        this.terrainPos = pos;
        this.terrainSolid = solid;
    }
    
    public Position getPos()
    {
        return this.terrainPos;
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

}