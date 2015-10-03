package game.world;

import debug.Console;
import java.awt.Polygon;
import java.util.ArrayList;
import maths.Maths;

public class Position
{
    private int positionX, positionY;
    
    public Position(String pos)
    {
        this.positionX = Integer.parseInt(pos.split("\\-")[0]);
        this.positionY = Integer.parseInt(pos.split("\\-")[1]);
        
        // TEMP
        if((Maths.isEven(this.positionX) && !Maths.isEven(this.positionY)) || (!Maths.isEven(this.positionX) && Maths.isEven(this.positionY)))
        {
            Console.print("ERROR: " + this.positionX + ", " + this.positionY + " are invalid coordinates!");
        }
    }
    
    public Position(int posX, int posY)
    {
        this.positionX = posX;
        this.positionY = posY;
    }
    
    public String getGridString()
    {
        return this.positionX + "-" + this.positionY;
    }
    
    public int getGridX()
    {
        return this.positionX;
    }
    
    public int getGridY()
    {
        return this.positionY;
    }
    
    public Polygon getInputArea()
    {
        int pointX[] = {this.getPosX() + 50, this.getPosX(), this.getPosX(), this.getPosX() + 50, this.getPosX() + 100, this.getPosX() + 100};
        int pointY[] = {this.getPosY(), this.getPosY() + 25, this.getPosY() + 75, this.getPosY() + 100, this.getPosY() + 75, this.getPosY() + 25};
        return new Polygon(pointX, pointY, 6);
    }
    
    public ArrayList<Position> getPosAdjacent()
    {
        ArrayList<Position> adjacent = new ArrayList();
        adjacent.add(this.getPosAdjacent(-1, -1));
        adjacent.add(this.getPosAdjacent(1, -1));
        adjacent.add(this.getPosAdjacent(-2, 0));
        adjacent.add(this.getPosAdjacent(2, 0));
        adjacent.add(this.getPosAdjacent(-1, 1));
        adjacent.add(this.getPosAdjacent(1, 1));
        return adjacent;
    }
    
    private Position getPosAdjacent(int moveX, int moveY)
    {
        return new Position(this.positionX + moveX, this.positionY + moveY);
    }

    public int getPosX()
    {
        return (this.positionX * 50) - 50;
    }

    public int getPosY()
    {
        return (this.positionY * 75) - 25;
    }
    
    public boolean match(Position pos)
    {
        return this.getGridString().equals(pos.getGridString());
    }

}