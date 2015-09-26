package game.account;

import debug.Console;
import java.util.ArrayList;
import network.NetworkService;

public class AccountGateway
{
    
    public static AccountData getAccountData(int id)
    {
        ArrayList<String> response = NetworkService.request("http://tk-game-network-db.co.nf/accountGateway.php?request=GET&id=" + id);
        if(response.get(0).contains("ERROR"))
        {
            Console.print("REQUEST ERROR");
            Console.print(response.get(0));
        }
        else {return new AccountData(id, response.get(0).split("\\|")[1]);}
        return null;
    }
    
}