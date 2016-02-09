package Objects.Catalog.World.Map.Tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class TileView extends TiledMapTileLayer.Cell{
    public TileView(TextureRegion texture)
    {
        setTile(new StaticTiledMapTile(texture));
    }
}
