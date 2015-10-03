package states;

import app.Engine;
import components.ComponentAbstract;
import components.ComponentGroup;
import components.button.Button;
import game.account.banner.Banner;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import tools.Render;

public class StateDev extends State
{
    // Detail
    private String detailContent, detailInfo;
    
    // TEMP
    private ComponentGroup components;
    private boolean loadAccount;
    
    // TEMP
    private Banner tempBanner = new Banner("050-000-050", "1");
    
    public StateDev()
    {
        // Detail
        this.detailContent = "Select an option by clicking on it...";
        this.detailInfo = "Version " + Engine.getAppVersion();
        
        // TEST
        //Timestamp ts1 = new Timestamp();
        
        // TEMP
        this.components = new ComponentGroup();
        this.components.addComponent(new Button(this.components, "LAUNCH", "LAUNCH GAME", 50, 150));
        this.components.addComponent(new Button(this.components, "QUICK1", "QUICK START 1", 50, 200));
        this.components.addComponent(new Button(this.components, "QUICK2", "QUICK START 2", 50, 250));
        this.components.addComponent(new Button(this.components, "EDITOR", "WORLD EDITOR", 50, 300));
        this.loadAccount = false;
        
        // TEST
        /*Timestamp ts2 = new Timestamp();
        Console.print("TIMESTAMPS");
        Console.print(" 1 " + ts1.asString());
        Console.print(" 2 " + ts2.asString());
        Console.print(" compare = " + ts1.compare(ts2));*/
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
            if(click.isRef("QUICK1"))
            {
                this.loadAccount = true;
                this.detailContent = "Please wait while the account is downloaded...";
                Engine.setState(new StateLobby(1));
            }
            if(click.isRef("QUICK2"))
            {
                this.loadAccount = true;
                this.detailContent = "Please wait while the account is downloaded...";
                Engine.setState(new StateLobby(2));
            }
            if(click.isRef("EDITOR"))
            {
                Engine.setState(new StateEditor());
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
        this.renderTitle(g);
        
        // TEMP
        if(!this.loadAccount) {this.components.render(g);}
        
        // TEMP
        this.tempBanner.render(g, 500, 200);
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        Drawing.fillScreen(g, Color.BLACK);
        
        // Title
        Render.writeTitle(g, "DEV MENU");
        Render.writeText(g, this.detailContent, 100);
        
        // Info
        Render.writeText(g, this.detailInfo, Engine.getWindowArea().y + Engine.getWindowArea().height - 60);
    }

    public void tick()
    {
        //
    }
    
}