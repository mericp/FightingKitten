package Objects.Catalog.World.Map.Tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TileController {
    private final TileModel model;
    private final TileView view;

    public TileController(Vector2 position, boolean collidable, TextureRegion texture)
    {
        model = new TileModel(position, collidable, 0);
        view = new TileView(texture);
    }

    public boolean isCollidable()
    {
        return model.isCollidable();
    }

    public TileView getView()
    {
        return view;
    }
    public TileModel getModel()
    {
        return model;
    }
}
