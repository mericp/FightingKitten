package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.ICollisionable;
import com.badlogic.gdx.math.Rectangle;

public class Collisionable implements ICollisionable {
    protected Rectangle hitbox;

    public Collisionable()
    {
        hitbox = new Rectangle();
    }

    @Override
    public Rectangle get() {
        return hitbox;
    }

    @Override
    public int getWidth() {
        return (int)hitbox.getWidth();
    }

    @Override
    public int getHeight() {
        return (int)hitbox.getHeight();
    }

    @Override
    public void setWidth(int width) {
        hitbox.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        hitbox.setHeight(height);
    }
}
