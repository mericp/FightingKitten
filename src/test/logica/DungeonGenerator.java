package test.logica;

public class DungeonGenerator {
    public final Cell[][] map;

    public DungeonGenerator(int width, int height)
    {
        map = new Cell[width][height];

        for(int x = 0; x < map.length; x++)
        {
            for(int y = 0; y < map.length; y++)
            {
                map[x][y] = new Cell();
            }
        }
    }

    public void generateRoomAtCell(int x, int y)
    {
        map[x][y].generateRoomAtRandomPosition();
    }
}