package game.world;

import game.war.WarData;
import gfx.Colour;
import gfx.Drawing;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import maths.Maths;

public class WorldLoader
{

    public static WorldData loadWorld(int id, WarData war)
    {
        // NOTE: need to merge the functionality of terrain and texture objects
        HashMap<String, Terrain> terrain = loadWorldTerrain(id, war);
        //
        ArrayList<Texture> textureArray = new ArrayList();
        textureArray.add(new Texture(3, 7, Drawing.getImage("graphics/terrain/tree.png").getSubimage(100, 0, 100, 100), true));
        terrain.get("3-7").setSolid(true);
        //
        return new WorldData(id, war, loadWorldTexture(id, war, textureArray), terrain);
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
                    terrain.put(pos.getGridString(), new Terrain(war, pos, false));
                }
                if(!Maths.isEven(x) && !Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    terrain.put(pos.getGridString(), new Terrain(war, pos, false));
                }
            }
        }
        return terrain;
    }
    
    private static BufferedImage loadWorldTexture(int id, WarData war, ArrayList<Texture> textureArray)
    {
        // TEMP
        int worldWidth = 3000;
        int worldHeight = 1000;
        
        // Create massive image
        BufferedImage textureImage = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = textureImage.createGraphics();
        Drawing.fillRect(g2d, 0, 0, worldWidth, worldHeight, Colour.getColourRGB(240, 228, 184));
        
        // Draw textures
        for(int x = 0; x < textureArray.size(); x++)
        {
            g2d.drawImage(textureArray.get(x).image, textureArray.get(x).pos.getPosX(), textureArray.get(x).pos.getPosY(), null);
        }
        
        // Return single image
        g2d.dispose();
        return textureImage;
    }

}