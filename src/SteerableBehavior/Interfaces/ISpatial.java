package SteerableBehavior.Interfaces;

import com.badlogic.gdx.math.Vector2;

public interface ISpatial {
    Vector2 get();
    float getX();
    float getY();
    void set(float x, float y);
}
