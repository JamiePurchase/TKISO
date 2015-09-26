package states;

import app.Engine;
import debug.Console;
import game.account.AccountData;
import game.account.AccountGateway;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class StateTitle extends State
{
    // TEMP
    private final Rectangle tempButton1 = new Rectangle(50, 50, 200, 75);
    private final Rectangle tempButton2 = new Rectangle(50, 150, 200, 75);
    
    public StateTitle()
    {
        //
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        // TEMP
        if(this.tempButton1.contains(e.getPoint())) {Engine.setState(new StateLobby(1));}
        if(this.tempButton2.contains(e.getPoint())) {Engine.setState(new StateLobby(2));}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderTitle(g);
        
        // TEMP
        Drawing.fillRect(g, this.tempButton1, Color.DARK_GRAY);
        Drawing.drawRect(g, this.tempButton1, Color.LIGHT_GRAY);
        Text.write(g, "Account 1", (int) this.tempButton1.getCenterX(), this.tempButton1.y + 35, "CENTER", Fonts.getFont("STANDARD"), Color.LIGHT_GRAY);
        //
        Drawing.fillRect(g, this.tempButton2, Color.DARK_GRAY);
        Drawing.drawRect(g, this.tempButton2, Color.LIGHT_GRAY);
        Text.write(g, "Account 2", (int) this.tempButton2.getCenterX(), this.tempButton2.y + 40, "CENTER", Fonts.getFont("STANDARD"), Color.LIGHT_GRAY);
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        Drawing.fillScreen(g, Color.BLACK);
    }

    public void tick()
    {
        //
    }
    
}