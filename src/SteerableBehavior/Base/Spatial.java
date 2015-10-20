package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.ISpatial;
import com.badlogic.gdx.math.Vector2;

public class Spatial implements ISpatial{
    protected Vector2 position = new Vector2();

    @Override
    public Vector2 get() {
        return position;
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public void set(float x, float y) {
        position.set(x, y);
    }
}
