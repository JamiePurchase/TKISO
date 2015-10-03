package game.account.banner;

import gfx.Colour;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Banner
{
    private Color background;
    private String logo;
    
    public Banner(Color bkg, String logo)
    {
        this.background = bkg;
        this.logo = logo;
    }
    
    public Banner(String bkg, String logo)
    {
        String[] bkgRgb = bkg.split("\\-");
        this.background = Colour.getColourRGB(Integer.parseInt(bkgRgb[0]), Integer.parseInt(bkgRgb[1]), Integer.parseInt(bkgRgb[2]));
        this.logo = logo;
    }
    
    public void render(Graphics g, int posX, int posY)
    {
        Rectangle rect = new Rectangle(posX, posY, 200, 80);
        Drawing.fillRect(g, rect, this.background);
        Drawing.drawImage(g, Drawing.getImage("graphics/banner/" + this.logo + ".png"), posX, posY);
        Drawing.drawRect(g, rect, Color.BLACK);
    }
    
}