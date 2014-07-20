package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface IPhysicalObject
{
    public int getBottomLeftCornerX();
    public int getBottomLeftCornerY();

    public void setPosition(float x, float y);

    public void setWidth(float width);
    public float getWidth();

    public void setHeight(float height);
    public float getHeight();

    public World getWorld();

    public void setBody(Body body);
    public Body getBody();
}