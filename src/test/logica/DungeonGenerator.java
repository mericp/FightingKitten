package test.logica;

public class DungeonGenerator {
    public char[][] map;
    private char wall = '*';

    public DungeonGenerator(int width, int height)
    {
        map = new char[width][height];
    }

    public void setWallAt(int x, int y)
    {
        map[x][y] =  wall;
    }
}
