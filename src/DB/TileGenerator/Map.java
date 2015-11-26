package DB.TileGenerator;

import DB.StringRes.MySettings;
import DB.TileGenerator.MVC.TileController;
import com.badlogic.gdx.math.Vector2;

public class Map{
    private static TileController[][] MAP;

    private Map(){}

    private static void create()
    {
        if(MAP == null)
        {
            int width = MySettings.SCREEN_WIDTH / MySettings.TILE_WIDTH;
            int height = MySettings.SCREEN_HEIGHT / MySettings.TILE_HEIGHT;

            MAP = new TileController[width][height];

            for(int x = 0; x <= width - 1; x++)
            {
                for(int y = 0; y <= height - 1; y++)
                {
                    MAP[x][y] = TileFactory.COLLECTION.grass(new Vector2(x, y));
                }
            }

            for(int y = 5; y < 10; y++)
            {
                MAP[10][y] = TileFactory.COLLECTION.wall(new Vector2(10, y));
            }

            for (int x = 10; x <= 15; x++)
            {
                MAP[x][5] = TileFactory.COLLECTION.wall(new Vector2(x, 5));
            }

            for (int x = 5; x <= 10; x++)
            {
                MAP[x][10] = TileFactory.COLLECTION.wall(new Vector2(x, 10));
            }
        }
    }

    public static TileController getTile(int x, int y)
    {
        //Todo refactor
        create();

        try
        {
            return MAP[x][y];
        }
        catch (IndexOutOfBoundsException ex)
        {
            return null;
        }
    }

    public static TileController[][] get()
    {
        create();
        return MAP;
    }
}
