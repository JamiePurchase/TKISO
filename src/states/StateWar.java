package states;

import app.Engine;
import debug.Console;
import game.account.AccountData;
import game.account.banner.Banner;
import game.war.WarData;
import game.world.Terrain;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import time.Timestamp;

public class StateWar extends State
{
    // Player
    private AccountData playerData;
    
    // War
    private WarData warData;
    private Rectangle warArea;
    
    // Network
    private boolean networkRequest;
    private Timestamp networkTime;
    
    // Info
    private Rectangle infoArea;
    private String infoText;
    
    // TEMP
    private final Rectangle tempButton1 = new Rectangle(350, 50, 200, 75);
    
    public StateWar(AccountData player, WarData war)
    {
        // Player
        this.playerData = player;
        
        // War
        this.warData = war;
        this.warArea = new Rectangle(0, 0, Engine.extendWindow.getRenderFill().width, Engine.extendWindow.getRenderFill().height - 40);
        
        // Network
        this.networkRequest = false;
        this.networkTime = new Timestamp(new Timestamp().asLong() + 3000);
        
        // Info Bar
        this.infoArea = new Rectangle(0, Engine.extendWindow.getRenderFill().height - 40, Engine.extendWindow.getRenderFill().width, 40);
        if(this.isPlayerActive()) {this.setInfoText("It's your turn...");}
        else {this.setInfoText("Waiting for your opponent...");}
        
        // Refresh (move this into the war tick method later)
        //this.refreshTick = 0;
        //if(!this.isPlayerActive()) {this.refreshTick = 30;}
    }
    
    public Rectangle getAreaWar()
    {
        return this.warArea;
    }
    
    public AccountData getPlayerData()
    {
        return this.playerData;
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
        // NOTE: has the player clicked on an interface?
        
        // War
        if(this.isPlayerActive() && this.warArea.contains(e.getPoint())) {this.warData.inputClickL(e);}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        // War
        if(this.isPlayerActive() && this.warArea.contains(e.getPoint())) {this.warData.inputClickR(e);}
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
        
    // NOTE: consider using a Mouse Wheel event to zoom in/out of the world
    
    private boolean isPlayerActive()
    {
        if(this.warData.getTurnActive() == this.playerData.getID()) {return true;}
        return false;
    }
    
    private void networkRefresh()
    {
        // DEBUG
        Console.print("Network Refresh");
        
        // Request Started
        this.networkRequest = true;
        
        // NOTE: request latest action (and messages?)
        this.warData.networkRefresh();
        
        // Request Done
        this.networkRequest = false;
    }
    
    public void render(Graphics g)
    {
        // TEMP
        //this.renderTemp(g);
        
        this.warData.render(g);
        this.renderInfo(g);
        this.renderPlayer(g);
    }
    
    private void renderInfo(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.infoArea, Color.WHITE);
        
        // Detail
        Text.write(g, this.infoText, this.infoArea.x + 15, this.infoArea.y + 28, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        
        // Position
        String infoGridX = "-";
        String infoGridY = "-";
        if(this.warArea.contains(Engine.getMousePoint()))
        {
            Terrain posTerrain = this.warData.getInputTerrain(Engine.getMousePoint(true));
            if(posTerrain != null)
            {
                infoGridX = "" + posTerrain.getPos().getGridX();
                infoGridY = "" + posTerrain.getPos().getGridY();
            }
        }
        Text.write(g, "x " + infoGridX, this.infoArea.x + this.infoArea.width - 360, this.infoArea.y + 28, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        Text.write(g, "y " + infoGridY, this.infoArea.x + this.infoArea.width - 280, this.infoArea.y + 28, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        
        // Network
        String infoNetwork = "-------";
        if(this.networkRequest) {infoNetwork = "SYNCING";}
        else
        {
            if(!this.isPlayerActive()) {infoNetwork = "" + this.networkTime.compare(new Timestamp());}
        }
        Text.write(g, infoNetwork, this.infoArea.x + this.infoArea.width - 15, this.infoArea.y + 28, "RIGHT", Fonts.getFont("STANDARD"), Color.BLACK);
    }
    
    private void renderPlayer(Graphics g)
    {
        // Rect
        Rectangle rect = new Rectangle(this.warArea.x + 25, this.warArea.y + 15, 400, 100);
        
        // Background
        Drawing.fadeRect(g, rect, Color.WHITE, 0.8f);
        
        // Border
        Drawing.drawRect(g, rect, Color.BLACK);
        
        // Text
        Text.write(g, this.getPlayerData().getUsername(), (int) rect.x + 230, rect.y + 30, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        Text.write(g, "  [stats]", (int) rect.x + 230, rect.y + 60, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        
        // Banner (TEMP)
        new Banner("050-000-050", "1").render(g, rect.x + 10, rect.y + 10);
    }
    
    private void renderTemp(Graphics g)
    {
        // Background
        Drawing.fillRect(g, Engine.extendWindow.getRenderFill(), Color.BLACK);
        
        // Text
        Text.write(g, "AccountID", 50, 100, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        Text.write(g, "" + this.playerData.getID(), 200, 100, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        //
        Text.write(g, "GameID", 50, 150, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        Text.write(g, "" + this.warData.getID(), 200, 150, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        //
        Text.write(g, "Turn Count", 50, 200, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        Text.write(g, "" + this.warData.getTurnCount(), 200, 200, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        //
        String turnMessage = "OPPONENT'S TURN";
        if(this.isPlayerActive()) {turnMessage = "YOUR TURN";}
        Text.write(g, "Turn Active", 50, 250, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        Text.write(g, turnMessage + "(" + this.warData.getTurnActive() + ")", 200, 250, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        
        // TEMP
        if(this.isPlayerActive())
        {
            Drawing.fillRect(g, this.tempButton1, Color.DARK_GRAY);
            Drawing.drawRect(g, this.tempButton1, Color.LIGHT_GRAY);
            Text.write(g, "TURN ACTION", (int) this.tempButton1.getCenterX(), this.tempButton1.y + 35, "CENTER", Fonts.getFont("STANDARD"), Color.LIGHT_GRAY);
        }
    }
    
    public void setInfoText(String text)
    {
        this.infoText = text;
    }

    public void tick()
    {
        // Game Running
        this.tickRunning();
        
        // Player Active
        if(this.isPlayerActive()) {this.tickActive();}
        
        // Player Inactive
        if(!this.isPlayerActive()) {this.tickInactive();}
    }
    
    private void tickActive()
    {
        if(this.warData.getArmyIdle().size() < 1)
        {
            this.setInfoText("Your turn has ended...");
        }
    }
    
    private void tickInactive()
    {
        // New Actions
        if(this.warData.getActionReady() && !this.warData.getActionActive())
        {
            this.warData.actionActivate();
            return;
        }
        
        // Network Request
        if(!this.networkRequest)
        {
            if(this.networkTime.compare(new Timestamp()) >= 3000)
            {
                this.networkTime = new Timestamp();
                this.networkRefresh();
                //return;
                // NOTE: need to uncomment this if anything else happens after
            }
        }
    }
    
    private void tickRunning()
    {
        // World Tick
        this.warData.tick();
        
        // Message Request
        // NOTE: it might be wise to place message checks with the other calls when the player is waiting
    }
    
}