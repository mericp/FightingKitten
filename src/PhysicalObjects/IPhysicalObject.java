package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface IPhysicalObject
{
    int getBottomLeftCornerX();
    int getBottomLeftCornerY();

    void setPosition(float x, float y);

    void setWidth(float width);
    float getWidth();

    void setHeight(float height);
    float getHeight();

    World getWorld();

    void setBody(Body body);
    Body getBody();
}