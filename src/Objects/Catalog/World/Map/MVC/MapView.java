package Objects.Catalog.World.Map.MVC;

import DB.StringRes.MySettings;
import Objects.Catalog.World.Map.Tiles.TileController;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapView extends TiledMap {
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMapTileLayer mapTileLayer;

    private final TileController[][] defaults;

    public MapView(TileController[][] grid)
    {
        defaults = new TileController[grid.length][grid[0].length];

        System.arraycopy(grid, 0, defaults, 0, grid.length);
        reset();
    }

    public void reset()
    {
        mapTileLayer = new TiledMapTileLayer (MySettings.TILES_COUNT_WIDTH, MySettings.TILES_COUNT_HEIGHT, MySettings.TILE_SIZE, MySettings.TILE_SIZE);

        int width = defaults.length;
        int height = defaults[0].length;

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                resetTextureAt(x, y);
            }
        }

        getLayers().add(mapTileLayer);

        mapRenderer = new OrthogonalTiledMapRenderer(this);
    }

    public void updateTextureAt(int x, int y, TextureRegion texture)
    {
        mapTileLayer.getCell(x, y).getTile().setTextureRegion(texture);
    }

    private void resetTextureAt(int x, int y)
    {
        mapTileLayer.setCell(x, y, defaults[x][y].getView());
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
