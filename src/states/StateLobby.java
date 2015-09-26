package states;

import app.Engine;
import game.account.AccountData;
import game.account.AccountGateway;
import game.war.WarData;
import game.war.WarGateway;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StateLobby extends State
{
    // Player
    private AccountData playerData;
    
    // War
    private int warID;
    private boolean warLoad;
    private WarData warData;
    
    public StateLobby(int playerID)
    {
        // Player
        this.playerData = AccountGateway.getAccountData(playerID);
        
        // War
        this.warID = 1;
        this.warLoad = false;
        this.warData = null;
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
        // Background
        Drawing.fillScreen(g, Color.BLACK);
        
        // Title
        Text.write(g, "LOBBY", Engine.extendWindow.getRenderFillCenterX(), 25, "CENTER", Fonts.getFont("STANDARD"), Color.WHITE);
        
        // Info
        Text.write(g, "Loading battle data...", 25, Engine.extendWindow.getRenderFill().height - 50, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
    }

    public void tick()
    {
        // Load
        if(!this.warLoad)
        {
            this.warLoad = true;
            this.warData = WarGateway.getWarData(this.warID);
        }
        
        // War
        if(this.warData != null)
        {
            Engine.setState(new StateWar(this.playerData, this.warData));
        }
    }
    
}