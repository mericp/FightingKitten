package Objects.Environment;

import DB.StringRes.MySettings;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class Grass extends TiledMap
{
    private final OrthogonalTiledMapRenderer mapRenderer;

    public Grass()
    {
        mapRenderer = new OrthogonalTiledMapRenderer(this);
        createTileMap();
    }

    private void createTileMap()
    {
        TextureRegion texture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.GROUND);
        Cell cell;
        StaticTiledMapTile tile;

        TiledMapTileLayer ground = new TiledMapTileLayer (200, 200, 32, 32);

        //Repeat it 200 times.
        for (int x = 0; x < 200; x++)
        {
            for (int y = 0; y < 200; y++)
            {
                tile = new StaticTiledMapTile(texture);
                cell = new Cell();
                cell.setTile(tile);
                ground.setCell(x, y, cell);
            }
        }

        getLayers().add(ground);
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
