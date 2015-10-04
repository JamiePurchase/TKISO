package game.world;

import game.war.WarData;
import gfx.Colour;
import gfx.Drawing;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import maths.Maths;

public class WorldLoader
{

    public static WorldData loadWorld(int id, WarData war)
    {
        HashMap<String, Terrain> terrain = loadWorldTerrain(id, war);
        terrain.get("3-7").edit(Drawing.getImage("graphics/terrain/tree.png").getSubimage(100, 0, 100, 100), true);
        terrain.get("1-5").edit(Drawing.getImage("graphics/terrain/tree.png").getSubimage(0, 0, 100, 100), true);
        terrain.get("2-2").edit(Drawing.getImage("graphics/terrain/cliff.png").getSubimage(300, 0, 100, 100), true);
        terrain.get("3-1").edit(Drawing.getImage("graphics/terrain/cliff.png").getSubimage(200, 0, 100, 100), true);
        terrain.get("4-2").edit(Drawing.getImage("graphics/terrain/cliff.png").getSubimage(0, 0, 100, 100), true);
        terrain.get("6-2").edit(Drawing.getImage("graphics/terrain/cliff.png").getSubimage(100, 0, 100, 100), true);
        terrain.get("7-3").edit(Drawing.getImage("graphics/terrain/grass.png").getSubimage(0, 0, 100, 100), false);
        terrain.get("9-7").edit(Drawing.getImage("graphics/terrain/grass.png").getSubimage(200, 0, 100, 100), false);
        terrain.get("11-7").edit(Drawing.getImage("graphics/terrain/grass.png").getSubimage(300, 0, 100, 100), false);
        terrain.get("10-8").edit(Drawing.getImage("graphics/terrain/grass.png").getSubimage(300, 0, 100, 100), false);
        terrain.get("6-8").edit(Drawing.getImage("graphics/terrain/tree.png").getSubimage(200, 0, 100, 100), true);
        return new WorldData(id, war, loadWorldTexture(id, war, terrain), terrain);
    }
    
    private static HashMap<String, Terrain> loadWorldTerrain(int id, WarData war)
    {
        int sizeX = 32;
        int sizeY = 12;
        HashMap<String, Terrain> terrain = new HashMap();
        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(Maths.isEven(x) && Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    terrain.put(pos.getGridString(), new Terrain(war, pos, null, false));
                }
                if(!Maths.isEven(x) && !Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    terrain.put(pos.getGridString(), new Terrain(war, pos, null, false));
                }
            }
        }
        return terrain;
    }
    
    private static BufferedImage loadWorldTexture(int id, WarData war, HashMap<String, Terrain> terrain)
    {
        // TEMP
        int worldWidth = 3000;
        int worldHeight = 1000;
        
        // Create massive image
        BufferedImage textureImage = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = textureImage.createGraphics();
        Drawing.fillRect(g2d, 0, 0, worldWidth, worldHeight, Colour.getColourRGB(240, 228, 184));
        
        // Draw textures
        for(int x = 0; x < 30; x++)
        {
            for(int y = 0; y < 10; y++)
            {
                if(Maths.isEven(x) && Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    g2d.drawImage(terrain.get(pos.getGridString()).getTexture(), pos.getPosX(), pos.getPosY(), null);
                }
                if(!Maths.isEven(x) && !Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    g2d.drawImage(terrain.get(pos.getGridString()).getTexture(), pos.getPosX(), pos.getPosY(), null);
                }
            }
        }
        
        // Return single image
        g2d.dispose();
        return textureImage;
    }

}