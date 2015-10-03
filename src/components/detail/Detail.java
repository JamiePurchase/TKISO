package components.detail;

import components.ComponentAbstract;
import components.ComponentGroup;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Detail extends ComponentAbstract
{
    private String caption;
    
    public Detail(ComponentGroup group, String ref, String caption, int posX, int posY)
    {
        super(group, ref, new Rectangle(posX, posY, 300, 50));
        this.caption = caption;
    }
    
    public void renderComponent(Graphics g)
    {
        Text.write(g, this.caption, this.getArea().x, this.getArea().y, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
    }
    
}