package states;

import app.Engine;
import components.ComponentAbstract;
import components.ComponentGroup;
import components.detail.Detail;
import game.world.WorldData;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class StateEditor extends State
{
    // Components
    private ComponentGroup components;
    
    // World
    private WorldData world;
    
    public StateEditor()
    {
        // Components
        this.components = new ComponentGroup();
        this.components.addComponent(new Detail(this.components, "DETAIL1", "detail one", 100, 150));
        
        // World
        this.world = null;
        //this.world = new WorldData();
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
            if(click.isRef("LAUNCH"))
            {
                // NOTE: load the main game in production mode
            }
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
        //this.components.render(g);
        this.renderToolbar(g);
        if(this.world != null) {this.renderTerrain(g);}
        else {this.renderDialog(g);}
    }
    
    private void renderDialog(Graphics g)
    {
        // Area
        int dialogWidth = 600;
        int dialogHeight = 400;
        Rectangle area = new Rectangle((int) Engine.getWindowArea().getCenterX() - (dialogWidth / 2), (int) Engine.getWindowArea().getCenterY() - (dialogHeight / 2), dialogWidth, dialogHeight);
        
        // Fill
        Drawing.fillRect(g, area, Color.BLACK);
        
        // Border
        Drawing.fillRect(g, area, Color.WHITE);
    }
    
    private void renderTerrain(Graphics g)
    {
        // Area
        Rectangle area = new Rectangle(0, 40, Engine.getWindowArea().width, Engine.getWindowArea().height - 40);
        
        // TEMP
        Drawing.fillRect(g, area, Color.LIGHT_GRAY);
    }
    
    private void renderToolbar(Graphics g)
    {
        // Area
        Rectangle area = new Rectangle(0, 0, Engine.getWindowArea().width, 40);
        
        // Background
        Drawing.fillRect(g, area, Color.WHITE);
        
        // Options
        //
    }

    public void tick()
    {
        //
    }
    
}