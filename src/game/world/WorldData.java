package game.world;

import game.war.WarData;

public class WorldData
{
    // World
    private int worldID;
    private WarData worldWar;
    
    // Terrain
    //
    
    public WorldData(int worldID)
    {
        // NOTE: might not need to worry about ID or warData... we need to load the terrain art and collision data
        // then compile it into a lightweight bufferedImage and array of data (don't keep rendering individual tiles)
    }
    
}