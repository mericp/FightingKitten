package DB.TileGenerator.MVC;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TileController{
    private TileModel model;
    private TileView view;

    public TileController(Vector2 position, boolean collidable, TextureRegion texture)
    {
        model = new TileModel(position, collidable);
        view = new TileView(texture, model);
    }

    public boolean isCollidable()
    {
        return model.isCollidable();
    }

    public TileView getView()
    {
        return view;
    }
}
