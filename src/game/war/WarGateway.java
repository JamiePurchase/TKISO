package game.war;

import debug.Console;
import java.util.ArrayList;
import network.NetworkService;
import time.Timestamp;

public class WarGateway
{
    
    public static WarData getWarData(int id)
    {
        ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/warGateway.php?request=GET&id=" + id);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else
        {
            // War Details
            String[] details = response.get(0).split("\\|");
            int host = Integer.parseInt(details[1]);
            int guest = Integer.parseInt(details[2]);
            int turnActive = Integer.parseInt(details[3]);
            int turnCount = Integer.parseInt(details[4]);
            //Timestamp turnBegin = new Timestamp(Integer.parseInt(details[5]));
            //int world = Integer.parseInt(response.get(0).split("\\|")[6]);
            
            // NOTE: include new lines of data for entities, constructs, extras and config
            
            // TEMP
            Timestamp turnBegin = new Timestamp();
            int world = 1;
            
            // Create the WarData object
            return new WarData(id, host, guest, world, turnActive, turnCount, turnBegin);
        }
        return null;
    }
    
}