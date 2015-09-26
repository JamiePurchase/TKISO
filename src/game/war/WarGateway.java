package game.war;

import debug.Console;
import java.util.ArrayList;
import network.NetworkService;

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
            int host = Integer.parseInt(response.get(0).split("\\|")[1]);
            int guest = Integer.parseInt(response.get(0).split("\\|")[2]);
            int turnActive = Integer.parseInt(response.get(0).split("\\|")[3]);
            int turnCount = Integer.parseInt(response.get(0).split("\\|")[4]);
            return new WarData(id, host, guest, turnActive, turnCount);
        }
        return null;
    }
    
}