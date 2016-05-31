package test.logica;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    public final char[][] content;
    private final char wall = '*';
    public final List<Room> rooms;

    public Cell()
    {
        this.content = new char[10][10];
        rooms = new ArrayList<>();
    }

    public void generateRoomAtRandomPosition()
    {
        Room newRoom = new Room(randomPosition(), cellWidth(), cellHeight());
        rooms.add(newRoom); //model
        drawRoom(newRoom); //view
    }

    private Vector2 randomPosition()
    {
        return new Vector2(nextInt(cellWidth()), nextInt(cellHeight()));
    }

    private int nextInt(int bounds)
    {
        Random rnd = new Random();
        return rnd.nextInt(bounds);
    }

    private int cellWidth()
    {
        return content.length;
    }

    private int cellHeight()
    {
        return content[0].length;
    }

    public void drawRoom(Room room)
    {
        for(int x = 0; x < room.width; x++)
        {
            for(int y = 0; y < room.height; y++)
            {
                content[x][y] =  wall;
            }
        }
    }
}