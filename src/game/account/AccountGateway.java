package game.account;

import debug.Console;
import game.account.banner.Banner;
import java.awt.Color;
import java.util.ArrayList;
import network.NetworkService;
import time.Timestamp;

public class AccountGateway
{
    
    public static AccountData getAccountData(int id)
    {
        /*ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/accountGateway.php?request=GET&id=" + id);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else
        {
            String username = response.get(0).split("\\|")[1];
            Banner banner = new Banner(response.get(0).split("\\|")[2], response.get(0).split("\\|")[3]);
            int exp = Integer.parseInt(response.get(0).split("\\|")[4]);
            Timestamp online = new Timestamp(Integer.parseInt(response.get(0).split("\\|")[5]));
            return new AccountData(id, username, banner, exp, online);
        }
        return null;*/
        
        // TEMP
        return new AccountData(1, "Jamie", new Banner(Color.BLACK, "1"), 0, new Timestamp());
    }
    
}