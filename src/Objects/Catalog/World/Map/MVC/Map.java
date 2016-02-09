package Objects.Catalog.World.Map.MVC;

import DB.StringRes.MySettings;
import Objects.Catalog.World.Map.Tiles.TileController;
import Objects.Catalog.World.Map.Tiles.TileFactory;
import com.badlogic.gdx.math.Vector2;

public class Map {
    private static Map instance;
    private  TileController[][] grid;

    public MapModel graph;
    public MapView view;

    private Map()
    {
        reset();
    }

    public static Map get()
    {
        if(instance == null)
        {
            instance = new Map();
        }

        return instance;
    }

    public void reset()
    {
        grid = paintDefaults();

        graph = new MapModel(grid);
        view = new MapView(grid);
    }

    private TileController[][] paintDefaults()
    {
        int width = MySettings.TILES_COUNT_WIDTH;
        int height = MySettings.TILES_COUNT_HEIGHT;

        TileController[][] tc = new TileController[width][height];

        for(int x = 0; x <= width - 1; x++)
        {
            for(int y = 0; y <= height - 1; y++)
            {
                tc[x][y] = TileFactory.COLLECTION.grass(new Vector2(x, y));
            }
        }

        for(int y = 5; y < 10; y++)
        {
            tc[10][y] = null;
            tc[10][y] = TileFactory.COLLECTION.wall(new Vector2(10, y));
        }

        for (int x = 10; x <= 15; x++)
        {
            tc[x][5] = null;
            tc[x][5] = TileFactory.COLLECTION.wall(new Vector2(x, 5));
        }

        for (int x = 5; x <= 10; x++)
        {
            tc[x][10] = null;
            tc[x][10] = TileFactory.COLLECTION.wall(new Vector2(x, 10));
        }

        return tc;
    }

    public TileController getTile(int x, int y)
    {
        return grid[x][y];
    }

    public void dispose()
    {
        view.dispose();
    }
}
