package SteerableBehavior.Interfaces;

import com.badlogic.gdx.math.Rectangle;

public interface ICollisionable {
    Rectangle get();
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void onCollide();
}
