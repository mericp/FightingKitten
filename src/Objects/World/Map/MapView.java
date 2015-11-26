package Objects.World.Map;

import DB.StringRes.MySettings;
import DB.TileGenerator.MVC.TileController;
import DB.TileGenerator.MVC.TileView;
import DB.TileGenerator.Map;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapView extends TiledMap {
    private final OrthogonalTiledMapRenderer mapRenderer;
    private TiledMapTileLayer mapTileLayer;

    public MapView()
    {
        createTileMap();
        mapRenderer = new OrthogonalTiledMapRenderer(this);
    }

    private void createTileMap()
    {
        mapTileLayer = new TiledMapTileLayer (MySettings.SCREEN_WIDTH, MySettings.SCREEN_HEIGHT, MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT);

        TileController[][] map = Map.get();

        for(TileController[] row : map)
        {
            for(TileController cell : row)
            {
                addTile(cell.getView());
            }
        }

        getLayers().add(mapTileLayer);
    }
    private void addTile(TileView cell)
    {
        mapTileLayer.setCell((int) cell.getPosition().x, (int) cell.getPosition().y, cell);
    }

    public void setCamera(OrthographicCamera camera)
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
