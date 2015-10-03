package game.display;

import game.war.WarData;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SelectAbstract
{
    private WarData selectWar;
    private Rectangle selectArea;
    
    public SelectAbstract(WarData war, Rectangle area)
    {
        this.selectWar = war;
        this.refreshArea(area);
    }
    
    public Rectangle getArea()
    {
        return this.selectArea;
    }
    
    public void refreshArea(Rectangle area)
    {
        int displayWidth = 600;
        int displayHeight = 200;
        this.selectArea = new Rectangle((int) area.getCenterX() - (displayWidth / 2), (int) area.y + area.height - displayHeight - 25, displayWidth, displayHeight);
    }
    
    public void render(Graphics g, Rectangle area)
    {
        this.refreshArea(area);
        this.renderDisplay(g);
        this.renderInfo(g);
    }
    
    private void renderDisplay(Graphics g)
    {
        Drawing.fadeRect(g, this.selectArea, Color.WHITE, 0.8f);
        Drawing.drawRect(g, this.selectArea, Color.BLACK);
    }
    
    public abstract void renderInfo(Graphics g);

}