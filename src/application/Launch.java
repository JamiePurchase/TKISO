package application;

import app.Engine;
import gfx.Drawing;
import java.awt.image.BufferedImage;
import states.StateDev;
import tools.Render;

public class Launch
{
    
    public static void main(String[] args)
    {
        String name = "TKISO";
        String author = "Jamie Purchase";
        String version = "0.1.0";
        String path = "C:/Users/Jamie/Documents/NetBeansProjects/GameEngine/TKISO/src/resources/";
        //int modalW = 1000;
        //int modalH = 500;
        int modalW = 1366;
        int modalH = 768;
        BufferedImage icon = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/GameEngine/TKISO/src/resources/graphics/application/icon2.png");
        Render.load();
        new Engine(name, author, version, path, new StateDev(), modalW, modalH, false, "TKISO", icon, "STANDARD", "").start(false);
    }
    
}