package game.war;

import app.Engine;
import debug.Console;
import game.action.ActionData;
import game.action.ActionDataMove;
import game.action.ActionPosition;
import game.action.ActionType;
import game.army.ArmyData;
import game.display.SelectAbstract;
import game.display.SelectArmy;
import game.world.Position;
import game.world.Terrain;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import maths.Maths;
import network.NetworkService;
import time.Timestamp;
import tools.HashMapTools;

public class WarData
{
    // War
    private int warGameID, warHostID, warGuestID;
    //private WorldData warWorld;
    
    // World
    private BufferedImage worldTexture;
    private HashMap<String, Terrain> worldTerrain;
    private Rectangle worldArea;
    private int worldScrollX, worldScrollY;
    
    // Config
    //
    
    // Turn
    private int turnActive, turnCount;
    private Timestamp turnBegin;
    
    // Action
    private int actionComplete;
    private ArrayList<ActionData> actionStack;
    private boolean actionActive;
    
    // Account
    //private AccountData accountPlayer, accountEnemy;
    
    // Armies
    private ArrayList<ArmyData> armyPlayer, armyEnemy;
    
    // Constructs
    //private ArrayList<BuildData> constructPlayer, constructEnemy;
    
    // Environment? Weather? Treasure? Mutual Enemies? Effects?
    
    // Selection
    private boolean selectActive;
    private ArmyData selectArmy;
    //private BuildData selectConstruct;
    private SelectAbstract selectDisplay;
    private ArrayList<ActionPosition> selectPosition;
    
    public WarData(int id, int host, int guest, int world, int turnActive, int turnCount, Timestamp turnBegin)
    {
        // War
        this.warGameID = id;
        this.warHostID = host;
        this.warGuestID = guest;
        //this.warWorld = WorldGateway.getWorld(world);
        
        // World
        this.worldTexture = Drawing.getImage("graphics/terrain/temp.png");
        this.worldArea = new Rectangle(0, 0, Engine.extendWindow.getRenderFill().width, Engine.extendWindow.getRenderFill().height - 40);
        this.worldScrollX = 0;
        this.worldScrollY = 0;
        this.initTerrain();
        
        // Config
        //this.configMatchType = ?
        // NOTE: config determines the match type, victory conditions, environmental options
        // and generally and game features that are enabled/disabled
        
        // Turn
        this.turnActive = turnActive;
        this.turnCount = turnCount;
        this.turnBegin = turnBegin;
        
        // Action
        this.actionComplete = 0;
        // NOTE: this needs to be set by the initial game load, if resuming play
        this.actionStack = new ArrayList();
        this.actionActive = false;
        
        // Account
        //this.accountPlayer = AccountGateway.getAccountData(id);
        //this.accountEnemy = AccountGateway.getAccountData();
        
        // Armies
        this.armyPlayer = new ArrayList();
        this.armyEnemy = new ArrayList();
        
        // TEMP
        this.armyPlayer.add(new ArmyData(1, this, 1, new Position(6, 4), 100, 100, 100, 100));
        
        // DEBUG
        Console.print("Added an army to the player forces (ID = " + this.armyPlayer.get(0).getArmyID() + ")");
        
        // Constructs
        //this.constructPlayer = new ArrayList();
        //this.constructEnemy = new ArrayList();
        
        // Environment
        //
        
        // Selection
        this.setSelection();
    }
    
    public void actionActivate()
    {
        // Action Started
        this.actionActive = true;
        ActionData action = this.actionStack.get(0);
        action.activate();
        
        // Action Complete
        this.actionComplete = action.getID();
        this.actionStack.remove(action);
        this.actionActive = false;
        
        // NOTE: allow the app to tick a few times before activating the next action
        // NOTE: set info text back to 'waiting for opponent' if no more actions?
    }
    
    public boolean getActionActive()
    {
        return this.actionActive;
    }
    
    public boolean getActionReady()
    {
        if(this.actionStack.size() > 0) {return true;}
        return false;
    }
    
    public ArrayList<ActionData> getActionStack()
    {
        return this.actionStack;
    }
    
    public ArmyData getArmyID(int id)
    {
        // Army Player
        for(int x = 0; x < this.armyPlayer.size(); x++)
        {
            if(this.armyPlayer.get(x).getArmyID() == id) {return this.armyPlayer.get(x);}
        }
        
        //Army Enemy
        for(int x = 0; x < this.armyEnemy.size(); x++)
        {
            if(this.armyEnemy.get(x).getArmyID() == id) {return this.armyEnemy.get(x);}
        }
        
        // Nothing
        return null;
    }
    
    public ArrayList<ArmyData> getArmyIdle()
    {
        ArrayList<ArmyData> armyIdle = new ArrayList();
        for(int x = 0; x < this.armyPlayer.size(); x++)
        {
            if(this.armyPlayer.get(x).isIdle()) {armyIdle.add(this.armyPlayer.get(x));}
        }
        return armyIdle;
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
    
    public ArmyData getInputArmy(Point point)
    {
        // Army Player
        if(this.armyPlayer.size() > 0)
        {
            for(int x = 0; x < this.armyPlayer.size(); x++)
            {
                if(this.armyPlayer.get(x).getPosition().getInputArea().contains(point)) {return this.armyPlayer.get(x);}
            }
        }
        
        // Army Enemy
        if(this.armyEnemy.size() > 0)
        {
            for(int x = 0; x < this.armyEnemy.size(); x++)
            {
                if(this.armyEnemy.get(x).getPosition().getInputArea().contains(point)) {return this.armyEnemy.get(x);}
            }
        }
        
        // Nothing
        return null;
    }
    
    public Terrain getInputTerrain(Point point)
    {
        if(this.worldTerrain.size() > 0)
        {
            String[] keys = HashMapTools.getKeys(this.worldTerrain);
            for(int x = 0; x < keys.length; x++)
            {
                if(this.worldTerrain.get(keys[x]).getPos().getInputArea().contains(point))
                {
                    return this.worldTerrain.get(keys[x]);
                }
            }
        }
        return null;
    }
    
    public ActionPosition getPositionAction(Point point)
    {
        for(int x = 0; x < this.selectPosition.size(); x++)
        {
            if(this.selectPosition.get(x).getInputArea().contains(point)) {return this.selectPosition.get(x);}
        }
        return null;
    }
    
    public ArmyData getPositionArmy(Position pos)
    {
        // Army Player
        if(this.armyPlayer.size() > 0)
        {
            for(int x = 0; x < this.armyPlayer.size(); x++)
            {
                if(this.armyPlayer.get(x).getPosition().match(pos)) {return this.armyPlayer.get(x);}
            }
        }
        
        // Army Enemy
        if(this.armyEnemy.size() > 0)
        {
            for(int x = 0; x < this.armyEnemy.size(); x++)
            {
                if(this.armyEnemy.get(x).getPosition().match(pos)) {return this.armyEnemy.get(x);}
            }
        }
        
        // Nothing
        return null;
    }
    
    public ArmyData getSelectArmy()
    {
        return this.selectArmy;
    }
    
    /*public ArmyData getSelectConstruct()
    {
        return this.selectConstruct;
    }*/
    
    public Terrain getTerrain(String key)
    {
        return this.worldTerrain.get(key);
    }
    
    public Terrain getTerrain(int posX, int posY)
    {
        return this.worldTerrain.get(posX + "-" + posY);
    }
    
    public int getTurnActive()
    {
        return this.turnActive;
    }
    
    public int getTurnCount()
    {
        return this.turnCount;
    }
    
    public int getTurnNext()
    {
        if(this.turnActive == this.warGuestID) {return this.warHostID;}
        return this.warGuestID;
    }
    
    private Rectangle getWorldArea()
    {
        return new Rectangle(0, 0, Engine.extendWindow.getRenderFill().width, Engine.extendWindow.getRenderFill().height - 40);
    }
    
    private void initTerrain()
    {
        // TEMP
        int sizeX = 32;
        int sizeY = 12;
        
        // NOTE: the hashmap should be created by the world loader (when the large background image is made)
        this.worldTerrain = new HashMap();
        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(Maths.isEven(x) && Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    this.worldTerrain.put(pos.getGridString(), new Terrain(this, pos, false));
                }
                if(!Maths.isEven(x) && !Maths.isEven(y))
                {
                    Position pos = new Position(x, y);
                    this.worldTerrain.put(pos.getGridString(), new Terrain(this, pos, false));
                }
            }
        }
    }
    
    public void inputClickL(MouseEvent e)
    {
        // Armies
        ArmyData clickArmy = this.getInputArmy(e.getPoint());
        if(clickArmy != null)
        {
            this.setSelection(clickArmy);
            return;
        }

        // Terrain
        /*Terrain clickTerrain = this.getInputTerrain(e.getPoint());
        if(clickTerrain != null)
        {
            //
        }*/
        
        // Nothing
        this.setSelection();
    }
    
    public void inputClickR(MouseEvent e)
    {
        // NOTE: iterate through an array of positions that correspond to potential actions
        if(this.selectActive && this.selectPosition.size() > 0)
        {
            ActionPosition clickAction = this.getPositionAction(e.getPoint());
            if(clickAction != null)
            {
                // Performs the action
                this.selectArmy.action(clickAction);
                
                // Creates an action on the server
                ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/iso/action.php?war=" + this.warGameID + "&account=" + this.getTurnActive() + "&action=MOVE&data=" + this.selectArmy.getArmyID() + "_" + clickAction.getGridString());
                Console.print(response);
                
                // Clear army selection
                this.setSelection();
                
                // Are there any other idle armies able to act?
                if(this.getArmyIdle().size() < 1)
                {
                    // Creates an end of turn action on the server
                    response = NetworkService.request("http://tk-game-network-db.co.nf/iso/action.php?war=" + this.warGameID + "&account=" + this.getTurnActive() + "&action=END&data=" + this.getTurnNext());
                    Console.print(response);
                    
                    // NOTE: there should be a main END TURN method somewhere else
                    this.setTurnNext();
                }
                
                // Done
                return;
            }
        }
        
        // Nothing
        return;
    }
    
    public void networkRefresh()
    {
        // Request new actions (and messages?) from the server
        ArrayList<String> response2 = NetworkService.request("http://tk-game-network-db.co.nf/iso/waiting.php?war=" + this.warGameID + "&complete=" + this.actionComplete);
        String[] response = response2.get(0).split("\\<br>");
        Console.print(response);
        int actionNew = Integer.parseInt(response[0]);
        if(actionNew > 0)
        {
            for(int x = 0; x < actionNew; x++)
            {
                String[] actionData = response[x + 1].split("\\|");
                if(actionData[2].equals("MOVE"))
                {
                    this.actionStack.add(new ActionDataMove(Integer.parseInt(actionData[0]), this, Integer.parseInt(actionData[1]), Integer.parseInt(actionData[3].split("\\_")[0]), new Position(actionData[3].split("\\_")[1])));
                }
            }
        }
        
        
        //Integer.parseInt(response.get(0).split("\\|")[4])
        
        // NOTE: new actions must be added to this.actionStack (lowest ID first, will be activated soonest)
    }
    
    public void render(Graphics g)
    {
        // Terrain
        this.renderTerrain(g);
        // NOTE: do not render each tile of the terrain (the WorldData should compile into one large image
        // from which the necessary subimage for the current board scroll location can be drawn)
        
        // Armies
        this.renderArmy(g);
        // NOTE: armies, constructs and scenery need to be drawn from the NORTH downwards
        // (just rendering them ordered by id may cause layering issues)
        
        // Constructs
        //
        
        // Selection
        if(this.selectActive)
        {
            this.selectDisplay.render(g, this.getWorldArea());
            if(this.selectArmy != null) {this.selectArmy.renderHealth(g);}
        }
        
        // NOTE: other display elements (eg: player data, objectives, messages, resources, etc...)
    }
    
    private void renderArmy(Graphics g)
    {
        // Army Player
        if(this.armyPlayer.size() > 0)
        {
            for(int x = 0; x < this.armyPlayer.size(); x++)
            {
                this.armyPlayer.get(x).render(g);
            }
        }
        
        // Army Enemy
        if(this.armyEnemy.size() > 0)
        {
            for(int x = 0; x < this.armyEnemy.size(); x++)
            {
                this.armyEnemy.get(x).render(g);
            }
        }
    }
    
    private void renderTerrain(Graphics g)
    {
        BufferedImage terrain = this.worldTexture.getSubimage(this.worldScrollX, this.worldScrollY, Engine.getWindowArea().width, Engine.getWindowArea().height);
        Drawing.drawImage(g, terrain, 0, 0);
        
        // NOTE: consider opaque colour filled polygons for selected/interactive positions
        
        // TEMP
        /*if(this.worldTerrain.size() > 0)
        {
            String[] keys = HashMapTools.getKeys(this.worldTerrain);
            for(int x = 0; x < keys.length; x++)
            {
                Drawing.drawPolygon(g, this.worldTerrain.get(keys[x]).getPos().getInputArea(), Color.BLACK);
            }
        }*/
        
        // TEMP (hover)
        Terrain hoverTerrain = this.getInputTerrain(Engine.getMousePoint(true));
        if(hoverTerrain != null)
        {
            if(!hoverTerrain.isSolid()) {Drawing.drawPolygon(g, hoverTerrain.getPos().getInputArea(), Color.BLACK);}
        }
        
        // TEMP (action)
        if(this.selectActive && this.selectPosition.size() > 0)
        {
            for(int x = 0; x < this.selectPosition.size(); x++)
            {
                this.selectPosition.get(x).render(g);
            }
        }
    }
    
    public void setSelection()
    {
        this.selectActive = false;
        this.selectArmy = null;
        //this.selectConstruct = null;
        this.selectDisplay = null;
        this.selectPosition = new ArrayList();
    }
    
    private void setSelection(ArmyData army)
    {
        this.setSelection();
        this.selectActive = true;
        this.selectArmy = army;
        this.selectDisplay = new SelectArmy(this, this.getWorldArea(), army);
        this.selectPosition = army.getActionPosition();
    }
    
    /*private void setSelection(BuildData construct)
    {
        this.setSelection();
        this.selectActive = true;
        this.selectConstruct = construct;
        this.selectDisplay = new SelectConstruct(this, this.getWorldArea(), constuct);
    }*/
    
    public void setTurnNext()
    {
        this.turnActive = this.getTurnNext();
    }
    
    /*public void setTurnNext()
    {
        NetworkService.request("http://tk-game-network-db.co.nf/warGateway.php?request=TURN&id=" + this.warGameID);
        this.getDataRefresh();
    }*/
    
    public void tick()
    {
        this.tickArmies();
        // NOTE: iterate through animation frames for armies, constructs, scenery, effects, hud, etc...
    }
    
    private void tickArmies()
    {
        // Army Player
        if(this.armyPlayer.size() > 0)
        {
            for(int x = 0; x < this.armyPlayer.size(); x++)
            {
                this.armyPlayer.get(x).tick();
            }
        }
        
        // Army Enemy
        if(this.armyEnemy.size() > 0)
        {
            for(int x = 0; x < this.armyEnemy.size(); x++)
            {
                this.armyEnemy.get(x).tick();
            }
        }
    }
    
}