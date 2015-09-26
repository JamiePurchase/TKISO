package game.world;

public class WorldGateway
{

    public static WorldData getWorld(int id)
    {
        return new WorldData(id);
    }

}