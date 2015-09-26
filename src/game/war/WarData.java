package game.war;

import app.Engine;
import debug.Console;
import game.world.WorldData;
import game.world.WorldGateway;
import gfx.Colour;
import gfx.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
import network.NetworkService;
import time.Timestamp;

public class WarData
{
    // War
    private int warGameID, warHostID, warGuestID;
    private WorldData warWorld;
    
    // Config
    //
    
    // Turn
    private int turnActive, turnCount;
    private Timestamp turnBegin;
    
    // Account
    //private AccountData accountPlayer, accountEnemy;
    
    // Armies
    //private ArrayList<ArmyData> armyPlayer, armyEnemy;
    
    // Constructs
    //private ArrayList<BuildData> constructPlayer, constructEnemy;
    
    // Environment? Weather? Treasure? Mutual Enemies? Effects?
    
    public WarData(int id, int host, int guest, int world, int turnActive, int turnCount, Timestamp turnBegin)
    {
        // War
        this.warGameID = id;
        this.warHostID = host;
        this.warGuestID = guest;
        this.warWorld = WorldGateway.getWorld(world);
        
        // Config
        //this.configMatchType = ?
        // NOTE: config determines the match type, victory conditions, environmental options
        // and generally and game features that are enabled/disabled
        
        // Turn
        this.turnActive = turnActive;
        this.turnCount = turnCount;
        this.turnBegin = turnBegin;
        
        // Account
        //this.accountPlayer = AccountGateway.getAccountData(id);
        //this.accountEnemy = AccountGateway.getAccountData();
        
        // Armies
        //this.armyPlayer = new ArrayList();
        //this.armyEnemy = new ArrayList();
        
        // Constructs
        //this.constructPlayer = new ArrayList();
        //this.constructEnemy = new ArrayList();
    }
    
    public void getDataRefresh()
    {
        ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/warGateway.php?request=GET&id=" + this.warGameID);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else
        {
            this.turnActive = Integer.parseInt(response.get(0).split("\\|")[3]);
            this.turnCount = Integer.parseInt(response.get(0).split("\\|")[4]);
        }
    }
    
    public int getID()
    {
        return this.warGameID;
    }
    
    public int getTurnActive()
    {
        return this.turnActive;
    }
    
    public int getTurnCount()
    {
        return this.turnCount;
    }
    
    public void render(Graphics g)
    {
        // NOTE: do not render each tile of the terrain (the WorldData should compile into one large image
        // from which the necessary subimage for the current board scroll location can be drawn)
        //this.warWorld.render(g);
        
        // TEMP
        Drawing.fillRect(g, Engine.extendWindow.getRenderFill(), Colour.getColourRGB(240, 228, 184));
        
        this.renderArmy(g);
        // NOTE: armies, constructs and scenery need to be drawn from the NORTH downwards
        // (just rendering them ordered by id may cause layering issues)
    }
    
    private void renderArmy(Graphics g)
    {
        //
    }
    
    public void setTurnNext()
    {
        NetworkService.request("http://tk-game-network-db.co.nf/warGateway.php?request=TURN&id=" + this.warGameID);
        this.getDataRefresh();
    }
    
    public void tick()
    {
        // NOTE: iterate through animation frames for armies, constructs, scenery, effects, hud, etc...
    }
    
}