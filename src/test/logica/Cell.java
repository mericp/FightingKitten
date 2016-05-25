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
        Room newRoom = new Room(randomWidth(), randomHeight(), randomPosition());
        rooms.add(newRoom); //model
        drawRoom(newRoom); //view
    }

    private int randomWidth()
    {
        Random rnd = new Random();
        return rnd.nextInt(content.length);
    }

    private int randomHeight()
    {
        Random rnd = new Random();
        return rnd.nextInt(content[0].length);
    }

    private Vector2 randomPosition()
    {
        return new Vector2();
    }

    public void drawRoom(Room room)
    {
        content[(int)room.position.x][(int)room.position.y] =  wall;
    }
}
