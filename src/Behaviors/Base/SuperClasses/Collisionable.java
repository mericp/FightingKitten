package Behaviors.Base.SuperClasses;

import Behaviors.Base.Interfaces.ICollisionable;
import com.badlogic.gdx.math.Rectangle;

public class Collisionable implements ICollisionable {
    private final Rectangle hitbox;

    public Collisionable()
    {
        hitbox = new Rectangle();
        hitbox.width = 5;
        hitbox.height = 5;
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
