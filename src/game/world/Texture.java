package game.world;

import java.awt.image.BufferedImage;

public class Texture
{
    public Position pos;
    public BufferedImage image;
    public boolean solid;
    
    public Texture(int x, int y, BufferedImage image, boolean solid)
    {
        this.pos = new Position(x, y);
        this.image = image;
        this.solid = solid;
    }
    
}