package test.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonGenerator {
    public final char[][] map;
    public final List<Room> rooms;

    private final char wall = '*';

    public DungeonGenerator(int width, int height)
    {
        map = new char[width][height];
        rooms = new ArrayList<>();
    }

    public void setWallAt(int x, int y)
    {
        map[x][y] =  wall;
    }

    public Room generateRoom()
    {
        Random rnd = new Random();
        return new Room(rnd.nextInt(100), rnd.nextInt(100));
    }

    public void addRoom()
    {
        rooms.add(generateRoom());
    }
}