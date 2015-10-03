package states;

import app.Engine;
import components.ComponentAbstract;
import components.ComponentGroup;
import components.button.Button;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import tools.Render;

public class StateTitle extends State
{
    // TEMP
    private ComponentGroup components;
    
    public StateTitle()
    {
        // TEMP
        this.components = new ComponentGroup();
        this.components.addComponent(new Button(this.components, "BUTTON1", "ACCOUNT 1", 50, 100));
        this.components.addComponent(new Button(this.components, "BUTTON2", "ACCOUNT 2", 50, 200));
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
        ComponentAbstract click = this.components.inputClick(e);
        if(click != null)
        {
            if(click.isRef("BUTTON1")) {Engine.setState(new StateLobby(1));}
            if(click.isRef("BUTTON2")) {Engine.setState(new StateLobby(2));}
        }
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
        this.components.render(g);
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        Drawing.fillScreen(g, Color.BLACK);
        
        // Title
        Render.writeTitle(g, "MAIN MENU");
    }

    public void tick()
    {
        //
    }
    
}