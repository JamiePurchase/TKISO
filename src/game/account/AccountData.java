package game.account;

import game.account.banner.Banner;
import time.Timestamp;

public class AccountData
{
    private int accountID;
    private String username;
    private Banner banner;
    private int experience;
    private Timestamp online;
    
    public AccountData(int id, String username, Banner banner, int exp, Timestamp online)
    {
        this.accountID = id;
        this.username = username;
        this.banner = banner;
        this.experience = exp;
        this.online = online;
    }
    
    public Banner getBanner()
    {
        return this.banner;
    }
    
    public int getExperience()
    {
        return this.experience;
    }
    
    public int getID()
    {
        return this.accountID;
    }
    
    public Timestamp getOnline()
    {
        return this.online;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
}