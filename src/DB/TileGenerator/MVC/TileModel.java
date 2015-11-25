package DB.TileGenerator.MVC;

import com.badlogic.gdx.math.Vector2;

public class TileModel {
    private Vector2 position;
    private boolean collidable;

    public TileModel(Vector2 position, boolean collidable)
    {
        this.position = position;
        this.collidable = collidable;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public boolean isCollidable()
    {
        return collidable;
    }
}
