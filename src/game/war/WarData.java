package game.war;

import debug.Console;
import game.account.AccountData;
import java.util.ArrayList;
import network.NetworkService;

public class WarData
{
    // War
    private int warGameID, warHostID, warGuestID;
    
    // Account
    //private AccountData accountPlayer, accountOpponent;
    
    // Armies
    //private ArrayList<ArmyData> armyPlayer, armyOpponent;
    
    // Buildings
    //private ArrayList<BuildData> buildPlayer, buildOpponent;
    
    // Turn
    private int turnActive, turnCount;
    
    public WarData(int id, int host, int guest, int turnActive, int turnCount)
    {
        // War
        this.warGameID = id;
        this.warHostID = host;
        this.warGuestID = guest;
        
        // Account
        //this.accountPlayer = AccountGateway.getAccountData(id)
        
        // Armies
        //
        
        // Buildings
        //
        
        // Turn
        this.turnActive = turnActive;
        this.turnCount = turnCount;
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
    
    public void setTurnNext()
    {
        NetworkService.request("http://tk-game-network-db.co.nf/warGateway.php?request=TURN&id=" + this.warGameID);
        this.getDataRefresh();
    }
    
}