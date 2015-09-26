package game.account;

public class AccountData
{
    private int accountID;
    private String username;
    
    public AccountData(int id, String username)
    {
        this.accountID = id;
        this.username = username;
    }
    
    public int getID()
    {
        return this.accountID;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
}