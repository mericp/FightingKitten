package Background;

import DB.MySettings;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class Ground extends TiledMap
{
    private OrthogonalTiledMapRenderer mapRenderer;

    public Ground()
    {
        this.mapRenderer = new OrthogonalTiledMapRenderer(this);
        this.crearTiledMap();
    }

    private void crearTiledMap()
    {
        TextureRegion grassTexture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture("Grass");
        Cell cell;
        StaticTiledMapTile tile;

        TiledMapTileLayer suelo = new TiledMapTileLayer (200, 200, 32, 32);

        for (int x = 0; x < 200; x++)
        {
            for (int y = 0; y < 200; y++)
            {
                tile = new StaticTiledMapTile(grassTexture);

                cell = new Cell();
                cell.setTile(tile);
                suelo.setCell(x, y, cell);
            }
        }

        getLayers().add(suelo);
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
