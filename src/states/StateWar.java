package states;

import app.Engine;
import game.account.AccountData;
import game.war.WarData;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class StateWar extends State
{
    // Player
    private AccountData playerData;
    
    // War
    private WarData warData;
    private Rectangle warArea;
    
    // Info
    private Rectangle infoArea;
    private String infoText;
    
    // TEMP
    private final Rectangle tempButton1 = new Rectangle(350, 50, 200, 75);
    private int refreshTick;
    
    public StateWar(AccountData player, WarData war)
    {
        // Player
        this.playerData = player;
        
        // War
        this.warData = war;
        this.warArea = new Rectangle(0, 0, Engine.extendWindow.getRenderFill().width, Engine.extendWindow.getRenderFill().height - 30);
        
        // Info Bar
        this.infoArea = new Rectangle(0, Engine.extendWindow.getRenderFill().height - 30, Engine.extendWindow.getRenderFill().width, 30);
        this.infoText = "This is the info bar";
        
        // Refresh (move this into the war tick method later)
        this.refreshTick = 0;
        if(!this.isPlayerActive()) {this.refreshTick = 30;}
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
        //if(this.warArea.contains(e.getPoint())) {this.warArea.inputClickL(e);}
        
        // TEMP
        if(this.tempButton1.contains(e.getPoint()) && this.isPlayerActive()) {this.warData.setTurnNext();}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //if(this.warArea.contains(e.getPoint())) {this.warArea.inputClickR(e);}
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    private boolean isPlayerActive()
    {
        if(this.warData.getTurnActive() == this.playerData.getID()) {return true;}
        return false;
    }
    
    public void render(Graphics g)
    {
        // TEMP
        //this.renderTemp(g);
        
        this.warData.render(g);
        this.renderInfo(g);
    }
    
    private void renderInfo(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.infoArea, Color.WHITE);
        
        // Text
        Text.write(g, this.infoText, this.infoArea.x + 15, this.infoArea.y + 15, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
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

    public void tick()
    {
        if(!this.isPlayerActive())
        {
            if(this.refreshTick > 0)
            {
                this.refreshTick -= 1;
                if(this.refreshTick <= 0)
                {
                    this.warData.getDataRefresh();
                    this.refreshTick = 30;
                }
            }
            else {this.refreshTick = 30;}
        }
    }
    
}