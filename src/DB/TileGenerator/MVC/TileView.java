package DB.TileGenerator.MVC;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class TileView extends TiledMapTileLayer.Cell{
    private TileModel model;

    public TileView(TextureRegion texture, TileModel model)
    {
        setTexture(texture);
        this.model = model;
    }

    private void setTexture(TextureRegion texture)
    {
        setTile(new StaticTiledMapTile(texture));
    }

    public Vector2 getPosition()
    {
        return model.getPosition();
    }
}
