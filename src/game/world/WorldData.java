package game.world;

import game.war.WarData;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class WorldData
{
    private final int worldID;
    private final WarData worldWar;
    private final BufferedImage worldTexture;
    private HashMap<String, Terrain> worldTerrain;
    
    public WorldData(int id, WarData war, BufferedImage texture, HashMap<String, Terrain> terrain)
    {
        this.worldID = id;
        this.worldWar = war;
        this.worldTexture = texture;
        this.worldTerrain = terrain;
    }
    
    public HashMap<String, Terrain> getTerrain()
    {
        return this.worldTerrain;
    }
    
    public BufferedImage getTexture()
    {
        return this.worldTexture;
    }
    
}