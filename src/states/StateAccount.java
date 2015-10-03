package states;

import app.Engine;
import game.account.AccountData;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tools.Render;

public class StateAccount extends State
{
    // Player
    private AccountData playerData;
    
    public StateAccount()
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
        //
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
        // TEMP
        Render.writeTitle(g, "ACCOUNT");
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