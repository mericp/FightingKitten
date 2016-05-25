package test.logica;

import java.util.ArrayList;
import java.util.List;

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

    public void addRoom()
    {
        rooms.add(new Room());
    }
}