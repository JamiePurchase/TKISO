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
    
    // TEMP
    private final Rectangle tempButton1 = new Rectangle(350, 50, 200, 75);
    private int refreshTick;
    
    public StateWar(AccountData player, WarData war)
    {
        // Player
        this.playerData = player;
        
        // War
        this.warData = war;
        
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
        // TEMP
        if(this.tempButton1.contains(e.getPoint()) && this.isPlayerActive()) {this.warData.setTurnNext();}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
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
    
    /*private void serverAction()
    {
        this.serverBusy = true;
        this.serverStatus = "UPLOADING YOUR ACTIONS";
        this.serverActionRequest();
    }
    
    private void serverActionRequest()
    {
        ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/action.php?accountID=" + this.warAccountID + "&battleID=" + this.warGameID);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else
        {
            this.serverBusy = false;
            this.serverStatus = "SERVER HAS BEEN UPDATED";
            this.serverTime = 60;
        }
    }*/
    
    /*private void serverUpdate()
    {
        this.serverBusy = true;
        this.serverStatus = "CHECKING FOR OPPONENT ACTIONS";
        this.serverUpdateRequest();
    }
    
    private void serverUpdateRequest()
    {
        ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/update.php?accountID=" + this.warAccountID + "&battleID=" + this.warGameID);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else
        {
            int turnCount = Integer.parseInt(response.get(0).split("\\|")[0]);
            int turnActive = Integer.parseInt(response.get(0).split("\\|")[1]);
            if(turnCount > this.turnCount)
            {
                this.turnCount = turnCount;
                this.turnActive = turnActive;
                this.serverStatus = "OPPONENT HAS ACTED - IT'S YOUR TURN";
            }
            else {this.serverStatus = "WAITING FOR OPPONENT";}
            this.serverBusy = false;
            this.serverTime = 60;
        }
    }*/

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
        // Loading (TEMP - should fetch the battle data to build an object before getting here)
        //if(!this.warLoad && !this.serverBusy) {this.serverLoad();}
        
        // NETWORK SETUP
        // we need to have two alternating states: active and idle
        // while the player is active, we don't request anything (for now) until we click something
        // as the other player is active, we periodically check for any updates (to become active again)
        
        //if(!this.serverBusy) {this.tickServer();}
    }
    
    /*private void tickServer()
    {
        if(this.serverTime > 0)
        {
            this.serverTime -= 1;
            if(this.serverTime <= 0)
            {
                this.serverUpdate();
            }
        }
    }*/
    
}