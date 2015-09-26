package application;

import app.Engine;
import states.State;
import states.StateTitle;

public class Launch
{
    
    public static void main(String[] args)
    {
        String name = "TKISO";
        String author = "Jamie Purchase";
        String version = "0.1.0";
        String path = "C:/Users/Jamie/Documents/NetBeansProjects/GameEngine/TKISO/src/resources/";
        //Colours.loadColours();
        //Fonts.loadFonts();
        new Engine(name, author, version, path, new StateTitle(), 1000, 500, false, "TKISO", "STANDARD", "").start(false);
    }
    
}