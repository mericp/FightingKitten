package Objects.World.Map;

import DB.StringRes.MySettings;
import DB.TileGenerator.Tile;
import DB.TileGenerator.TileFactory;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Map extends TiledMap {
    private final OrthogonalTiledMapRenderer mapRenderer;
    private TiledMapTileLayer tiledMapTileLayer;

    public Map()
    {
        createTileMap();
        mapRenderer = new OrthogonalTiledMapRenderer(this);
    }

    private void createTileMap()
    {
        tiledMapTileLayer = new TiledMapTileLayer (MySettings.SCREEN_WIDTH, MySettings.SCREEN_HEIGHT, MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT);

        for(int x = 0; x <= 39; x++)
        {
            for(int y = 0; y <= 19; y++)
            {
                addCell(TileFactory.COLLECTION.grass(new Vector2(x, y)));
            }
        }

        for(int y = 5; y < 10; y++)
        {
            addCell(TileFactory.COLLECTION.wall(new Vector2(10, y)));
        }

        getLayers().add(tiledMapTileLayer);
    }

    private void addCell(Tile cell)
    {
        tiledMapTileLayer.setCell((int)cell.getPosition().x, (int)cell.getPosition().y, cell);
    }

    public void setView (OrthographicCamera camera)
    {
        mapRenderer.setView(camera);
    }

    public void render()
    {
        mapRenderer.render();
    }

    public void dispose()
    {
        super.dispose();
        mapRenderer.dispose();
    }
}
