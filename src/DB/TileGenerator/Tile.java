package DB.TileGenerator;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Tile extends TiledMapTileLayer.Cell{
    private Vector2 position;
    private boolean collidable;

    public Tile(Vector2 position, TextureRegion texture, boolean collidable)
    {
        this.position = position;
        setTexture(texture);
        this.collidable = collidable;
    }

    private void setTexture(TextureRegion texture)
    {
        setTile(new StaticTiledMapTile(texture));
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public boolean getCollidable()
    {
        return collidable;
    }
}
