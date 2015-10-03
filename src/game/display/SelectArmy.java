package game.display;

import game.army.ArmyData;
import game.war.WarData;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SelectArmy extends SelectAbstract
{
    private ArmyData selectArmy;
    
    public SelectArmy(WarData war, Rectangle area, ArmyData army)
    {
        super(war, area);
        this.selectArmy = army;
    }
    
    public void renderInfo(Graphics g)
    {
        Text.write(g, this.selectArmy.getInfoName(), this.getArea().x + 25, this.getArea().y + 35, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        Text.write(g, "HEALTH: " + this.selectArmy.getStatHealthNow() + " / " + this.selectArmy.getStatHealthMax(), this.getArea().x + 25, this.getArea().y + 80, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        Text.write(g, "ENERGY: " + this.selectArmy.getStatEnergyNow() + " / " + this.selectArmy.getStatEnergyMax(), this.getArea().x + 25, this.getArea().y + 125, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
    }
    
}